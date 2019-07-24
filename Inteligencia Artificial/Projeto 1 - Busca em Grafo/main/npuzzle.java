package main;

import agente.AgenteNPuzzle;
import ambiente.Tabuleiro_Puzzle;
import entrada.EntradaNPuzzle;

public class npuzzle {

	public static void main(String[] args) {

		try {
			EntradaNPuzzle input = new EntradaNPuzzle(2);
			Tabuleiro_Puzzle tab = new Tabuleiro_Puzzle(input.arg1, input.representacaoTabuleiro);
			AgenteNPuzzle agente = new AgenteNPuzzle(tab);

			if(agente.detectarSolubilidade()) {

				System.out.println("----- INIT -----");

				if(input.arg2.equalsIgnoreCase("h1"))
					agente.buscaEmLarguraSemRepetidosH1();
				else if(input.arg2.equalsIgnoreCase("h2"))
					agente.buscaEmLarguraSemRepetidosH2();
				else if(input.arg2.equalsIgnoreCase("h3"))
					agente.buscaEmLarguraSemRepetidosH3();

				System.out.println("----- GOAL -----");
			}
			else {
				System.out.println("UNSOLVABLE");
			}

		}
		catch(RuntimeException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

}
