package main;

import agente.AgenteNQueens;
import ambiente.Tabuleiro_Queens;
import entrada.EntradaNQueens;

public class nqueens {

	public static void main(String[] args) {

		try {
			EntradaNQueens input = new EntradaNQueens(1);

			Tabuleiro_Queens tab = new Tabuleiro_Queens(input.arg1);

			AgenteNQueens agente = new AgenteNQueens(tab);

			while(!agente.terminou()) {
				if(input.arg2.equals("dfs"))
					agente.buscaEmProfundidade();
				else if(input.arg2.equals("bfs"))
					agente.buscaEmLargura();
			}

		}
		catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
