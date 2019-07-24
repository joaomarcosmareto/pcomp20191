## Identificação do projeto
**Autor:** João Marcos Mareto Calado**
**Linguagem:** Java 8 (openjdk version "1.8.0_201")
**Ambiente de Desenvolvimento:** IDE Eclipse

### DESCRIÇÃO DO PROJETO
Os fontes do programa CSP de resolução de Sudokus está dividido em 3 pacotes.

- **pacote core:** É o pacote que contém as classes que manipulam problemas do tipo CSP, contém as classes genéricas para resolução deste tipo de problema (Problema, Variavel, Restricao, AllDiff, BackTracking, Par).

- **pacote main:** É o pacote que contém as classes específicas para o problema do exercício (Main e Sudoku). A classe Sudoku extende a classe Problema, possuindo a lista de variaveis, montando o tabuleiro do sudoku e populando as restrições.

  A classe Main é a classe principal, a qual é responsável pela chamada da classe EntradaSudoku do pacote Entrada, e pela instanciação do sudoku.

- **pacote entrada:** É o pacote responsável por conter a classe que lidará com as entradas de dados do programa.

**O programa desenvolvido é:**
* Main

### Forma de Build

Assumindo que se tenha a linguagem de programação java 8 no Sistema Operacional, basta executar os seguintes comandos abaixo para que o build seja feito.
- 1. compilar as classes java
``javac -classpath . core/*.java entrada/*.java main/*.java``

### Forma de Execução

A partir do diretório source:
- 1. executar o seguinte comando para executar o programa Main
	 * ``java main/Main``