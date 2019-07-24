# -*- coding: utf-8 -*-
import sys, os
import json
import metricas
import organiza_datasets
from sklearn.feature_extraction.text import TfidfVectorizer

from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split

from sklearn.model_selection import cross_val_score
from sklearn.metrics import classification_report, confusion_matrix

lista            = []
lista_negativos  = []
lista_total      = []
labels           = []

with open(os.path.join(sys.argv[0], 'teste.json'), encoding="utf8") as json_file:
    data = json_file.readlines()

    valido = True

    for i, item in enumerate(data):
        data[i] = json.loads(item)

    for registro in data:
        valido = True
        for atributos in registro.items():
            if atributos[1] is None or atributos[1] == '':
                valido = False
        if valido:
            lista.append(registro)

lista = organiza_datasets.organiza(lista)
lista_negativos = organiza_datasets.build_negativos_dificuldade_1(lista)

lista_total = lista + lista_negativos

for registro in lista_total:
    if type(registro['_id']) == str:
        labels.append(0)
    else:
        labels.append(1)


# =============================================================================
# O for abaixo gera a lista de dicionarios contendo as caracteristicas
# de cada registro.
#
# no google, username é o G_Displayname;
# no twitter, username é o T_ScreenName;
#
# no google, fullname é o "G_Firstname"+" "+"G_Lastname";
# no twitter, fullname é o T_Fullname;
# =============================================================================

tfidf_vectorizer = TfidfVectorizer()
caracteristicas  = []

for registro in lista_total:

    g_fullname = registro['g_plus']['G_Firstname'] + " " + registro['g_plus']['G_Lastname']

    vec_username    = [registro['g_plus']['G_Displayname'], registro['twitter']['T_ScreenName']]
    vec_fullname    = [g_fullname, registro['twitter']['T_Fullname']]
    vec_location    = [registro['g_plus']['G_Location'], registro['twitter']['T_Location']]
    vec_description = [tfidf_vectorizer, registro['g_plus']['G_aboutme'], registro['twitter']['T_Description']]

    aux = metricas.get_dict_metricas(
        vec_username,
        vec_fullname,
        vec_location,
        vec_description
    )

    caracteristicas.append(aux)

print(len(caracteristicas))


model = RandomForestClassifier()

# separa o conjunto em 70% para treino e 30% para teste
treino_dados, teste_dados, treino_resultados, teste_resultados = train_test_split(caracteristicas, labels, test_size=0.30)

model.fit (treino_dados, treino_resultados)
pred = model.predict (teste_dados)

rfc_cv_score = cross_val_score(model, caracteristicas, labels, cv=10, scoring='roc_auc')

print("=== Confusion Matrix ===")
print(confusion_matrix(teste_resultados, pred))
print('\n')
print("=== Classification Report ===")
print(classification_report(teste_resultados, pred))
print('\n')
print("=== All AUC Scores ===")
print(rfc_cv_score)
print('\n')
print("=== Mean AUC Score ===")
print("Mean AUC Score - Random Forest: ", rfc_cv_score.mean())