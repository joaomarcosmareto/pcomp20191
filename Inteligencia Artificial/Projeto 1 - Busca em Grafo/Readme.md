## Identificação do projeto
**Autor:** João Marcos Mareto Calado**
**Linguagem:** Java 8 (openjdk version "1.8.0_201")
**Ambiente de Desenvolvimento:** IDE Eclipse

### DESCRIÇÃO DO PROJETO
Os fontes dos programas npuzzle e nqueens estão divididos em 4 pacotes.
- **pacote agente:** É o pacote que contém as classes que manipulam o tabuleiro, mantendo conhecimento dos estados filhos, do estado atual e do objetivo a ser perseguido.

  Este pacote contém os agentes para os programas npuzzle e nqueens.

- **pacote ambiente:** É o pacote que contém os tabuleiros. O tabuleiro do problema das rainhas é mais simples, implementando apenas a função de clonagem e mantém conhecimento de qual a coluna máxima aberta no clone.

  Já o tabuleiro do problema do sliding puzzle é mais complexo, implementa funcionalidades de clone, swap (troca de peças para cada uma das 4 direções possíveis), retorna a posição da peça vazia, armazena o custo do estado atual e implementa funções que retornam o custo de cada uma das 3 heurísticas a serem implementadas e retorna ainda o número de inversões do tabuleiro.

- **pacote entrada:** É o pacote responsável por conter as classes que lidarão com as entradas de dados dos programas.

  A entrada do NPuzzle é diferente da entrada NQueens por conta do número de linhas e parâmetros informados em cada programa, por este motivo foram criadas duas classes diferentes.

- **pacote main:** É o pacote que contém as classes que serão executadas de fato (nqueens e npuzzle).

**Os programas desenvolvidos são:**
* nqueens
* npuzzle

### Forma de Build

Assumindo que se tenha a linguagem de programação java 8 no Sistema Operacional, basta executar os seguintes comandos abaixo para que o build seja feito.
- 1. compilar as classes java
``javac -classpath . ambiente/*.java agente/*.java entrada/*.java main/*.java``

### Forma de Execução

A partir do diretório source:
- 1.executar o seguinte comando para executar o programa NPUZZLE
	 * ``java main/npuzzle``

- 2.executar o seguinte comando para executar o programa NQUEENS
	* ``java main/nqueens``