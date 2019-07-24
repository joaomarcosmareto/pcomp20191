package main;

import core.BackTracking;
import entrada.EntradaSudoku;

public class Main {

	public static void main(String[] args) {
		// entrada de dados no seguinte formato
		// #1 n
		// #1 0 a n
		// #2 0 a n
		// ...0 a n
		// #n 0 a n
		EntradaSudoku es = null;
		try {
			es = new EntradaSudoku();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		Sudoku s = new Sudoku(es.n * es.n);
		BackTracking bts = new BackTracking();

		for(int i = 0; i < s.variaveis.size(); i++) {
			if(es.vars[i] != 0) {
				s.atribui(s.variaveis.get(i), es.vars[i]);
			}
		}



		double start = System.currentTimeMillis();
		bts.solve(s);
		double end = System.currentTimeMillis();
		System.out.println(s.prettyPrint());
		// System.out.println("Time to solve = " + (end - start));
	}
}
