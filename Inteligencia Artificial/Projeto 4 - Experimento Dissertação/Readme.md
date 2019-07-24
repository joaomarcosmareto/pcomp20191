## Identificação do projeto
**Autor:** João Marcos Mareto Calado**
**Linguagem:** Python 3 em sua última versão

### DESCRIÇÃO DO PROJETO
Este projeto realiza a leitura de dados do arquivo teste.json que contém perfis de usuários do Google+ associados a perfis do Twitter. Após realizar a leitura deste dataset, os dados são saneados, removendo-se os registros com atributos com valor null ou com valor string vazia.

Após esta etapa de saneamento, um conjunto de instâncias negativas, isto é, perfis que não são pertencentes à mesma pessoa real, são geradas para que seja possível a implementação de um classificador.

Em seguida, o seguinte conjunto de métricas são aplicadas no conjunto de dados:

- 1. Exact Match (EM): Verifica se duas strings são iguais. Retorna 0 ou 1;
- 2. Longest Common Substring (LCS): Medida que verifica o quanto duas strings são iguais. Esta medida é normalizada dividindo o tamanho da maior substring pela média do comprimento das duas strings originais, de modo que o valor desta medida varie entre 0 e 1. Retorna um valor entre 0 e 1;
- 3. Longest Common Sub-Sequence (LCSS): Medida parecida com a LCS, porém de forma que a sequência não precise ser contígua. Novamente o valor de retorno é normalizado pela média do tamanho das duas strings orignais, de modo que o valor desta medida varie entre 0 e 1. Retorna um valor entre 0 e 1;
- 4. Levenshtein Distance (LD): Também conhecido como Edit-Distance, esta medida calcula o menor número de alterações para que uma string fique igual à outra. Retorna um valor inteiro;
- 5. Jaccard Similarity (JS): Medida utilizada para computar sobreposições, definida como o tamanho da interseção divida pelo tamanho da união das amostras;
- 6. Cosine Similarity with TF-IDF Weights: A similaridade de coseno com pesos tf-idf mede a similaridade de textos em termos de ângulo entre as representações desses textos no Modelo de Espaço Vetorial.

Assim, as medidas EM, LCS, LCSS, LD e JS são empregadas para comparar os campos baseados em nome (Full Name e Screen Name), e as medidas EM, LCS, e JS são utilizadas para computar a similaridade do campo localização. Para o campo descrição, é utilizada a medida de similaridade de coseno com pesos tf-idf.

Os campos baseados em nome são Fullname e Username.
- 1. Fullname existe no dataset original como campo pertencente ao twitter, no entanto, para este trabalho, foi necessário criar o campo Fullname nos campos do Google+ concatenando o campo firstname com o campo lastname.
- 2. Username existe no dataset original como Displayname no Google+ e Screenname no Twitter

O campo localização existe no dataset com o mesmo nome tanto no twitter como no Google+ e o campo descrição existe no dataset como aboutme no Google+ e description no Twitter.

Após esta etapa, a lista de dados contendo as características são separados em dois conjuntos, sendo um para treino e outro para testes. O conjunto de treino é utilizado para treinar um classificador do tipo Floresta Aleatória. O modelo então é utilizado para predizer os dados contidos no conjunto de testes.


**O programa desenvolvido é:**
* teste.py

### Dependências

Este projeto depende das bibliotecas sklearn e nltk e difflib

### Forma de Build

Não é necessário que se faça build do projeto, por ser feito em Python.

- 1. Executar o arquivo teste.py
``python teste.py``