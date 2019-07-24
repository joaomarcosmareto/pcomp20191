package main;

import core.AllDiff;
import core.Problema;
import core.Variavel;

public class Sudoku extends Problema{

	int boardSize = 9;

	public Sudoku(int size) {
		super();
		boardSize = size;
		int qtd = boardSize * boardSize;

		Object[] dominio = new Object[size];

		for (int i = 0; i < boardSize; i++) {
			dominio[i] = i + 1;
		}

		for (int i = 0; i < qtd; i++) {
			int row = ((i / boardSize) + 1);
			String srow = "" + row;
			if(row < 10)
				srow = "0" + row;

			int col = ((i % boardSize) + 1);
			String scol = "" + col;
			if(col < 10)
				scol = "0" + col;

			variaveis.add(new Variavel("(" + srow + "," + scol + ")", dominio));
		}
		populaRestricoes();
	}


	public void populaRestricoes() {
		if (restricoes.size() == 0) {

			// restricoes de linhas
			for (int row = 0; row < boardSize; row++) {
				AllDiff r = new AllDiff(boardSize);
				for (int col = 0; col < boardSize; col++) {
					r.escopo.add(variaveis.get(row * boardSize + col));
				}
				addRestricao(r);
			}

			// restricoes de colunas
			for (int col = 0; col < boardSize; col++) {
				AllDiff r = new AllDiff(boardSize);
				for (int row = 0; row < boardSize; row++) {
					r.escopo.add(variaveis.get(row * boardSize + col));
				}
				addRestricao(r);
			}

			// restricoes dos "grids"
			int numGrids = (int) Math.round(Math.sqrt(boardSize));
			for (int gridRow = 0; gridRow < numGrids; gridRow++) {
				for (int gridCol = 0; gridCol < numGrids; gridCol++) {
					AllDiff r = new AllDiff(boardSize);

					for (int row = 0; row < numGrids; row++) {
						for (int col = 0; col < numGrids; col++) {
							int piece = (gridRow * numGrids+row) * boardSize + gridCol * numGrids + col;
							r.escopo.add(variaveis.get(piece));
						}
					}
					addRestricao(r);
				}
			}
		}
	}

	public String prettyPrint() {
		StringBuffer result = new StringBuffer("");
		int count = 0;
		for(int row = 0; row < boardSize; row++) {
			for(int col = 0; col < boardSize; col++) {
				result.append(variaveis.get(count).valor + " ");
				count++;
			}
			result.append("\r\n");
		}
		return result.toString();
	}

}
