# -*- coding: utf-8 -*-
import metricas
import organiza_datasets
import numpy

from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split

from sklearn.model_selection import cross_val_score
from sklearn.metrics import classification_report, confusion_matrix
from sklearn.model_selection import RandomizedSearchCV

input_file = 'teste.json'
input_encoding = 'utf8'

dataset         = []
labels          = []
caracteristicas = []

# cria o dataset, lendo o json (input_file), removendo os registros com dados nulos ou string vazia;
# cria instancias negativas e concatena num dataset único;
# cria o array de rótulos na mesma ordem que as instancias pareadas e negativas.
dataset, labels = organiza_datasets.build_dificuldade_1(input_file, input_encoding)

# extrai o conjunto de características a partir do dataset
# as características são:
#   Exact Match;
#   Longest Commom Substring;
#   Longest Commom Subsequence;
#   Levenshtein Distance;
#   Jaccard Similarity e;
#   Cosine Similarity with TF-IDF Weights.
caracteristicas = metricas.extrair_caracteristicas(dataset)

# separa o conjunto de características em 70% para treino e 30% para teste
#X_train,     X_test,      y_train,           y_test =           train_test_split(X,               y,)
treino_dados, teste_dados, treino_resultados, teste_resultados = train_test_split(caracteristicas, labels, test_size=0.30)

# BEGIN - TRECHO TESTADO EM UM COMPUTADOR COM 16 CORES, E 8 GIGAS DE RAM
# Testa para descobrir o melhor conjunto de hiper-parâmetros para o classificador
# número de árvores na floresta aleatória
n_estimators = [int(x) for x in numpy.linspace(start = 200, stop = 2000, num = 10)]
# número de características em cada split
max_features = ['auto', 'sqrt']

# profundidade máxima
max_depth = [int(x) for x in numpy.linspace(100, 500, num = 11)]
max_depth.append(None)
# create random grid
random_grid = {
    'n_estimators' : n_estimators,
    'max_features' : max_features,
    'max_depth'    : max_depth
}
# Random search of parameters
modelo = RandomForestClassifier()
rfc_random = RandomizedSearchCV(estimator = modelo, param_distributions = random_grid, n_iter = 100, cv = 3, verbose=2, random_state=42, n_jobs = -1)
# Fit the model
rfc_random.fit(treino_dados, treino_resultados)
# END - TRECHO TESTADO EM UM COMPUTADOR COM 16 CORES, E 8 GIGAS DE RAM

# Constrói o classificador com os dados do teste de tunning de hiper-parâmetros
modelo = RandomForestClassifier(
        n_estimators = rfc_random.best_params_['n_estimators'],
        max_depth    = rfc_random.best_params_['max_depth'],
        max_features = rfc_random.best_params_['max_features'],
)

# Preenche o modelo com dados e labels de treino
modelo.fit(treino_dados, treino_resultados)

# Usa o conjunto de dados de teste para tentar prever os resultados
pred = modelo.predict(teste_dados)

# Resultado de Cross Validation
cv_score = cross_val_score(modelo, caracteristicas, labels, cv=10, scoring='roc_auc')

print("=== Confusion Matrix ===")
print(confusion_matrix(teste_resultados, pred))
print('\n')
print("=== Classification Report ===")
print(classification_report(teste_resultados, pred))
print('\n')
print("=== All AUC Scores ===")
print(cv_score)
print('\n')
print("=== Mean AUC Score ===")
print("Mean AUC Score - Random Forest: ", cv_score.mean())