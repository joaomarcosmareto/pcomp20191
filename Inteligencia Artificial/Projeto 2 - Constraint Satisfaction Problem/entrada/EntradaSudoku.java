package entrada;

import java.util.ArrayList;
import java.util.Scanner;

public class EntradaSudoku{

	public int n;
	public ArrayList<String> linhas;
	public int[] vars = null;

	public EntradaSudoku() {

		Scanner input = new Scanner(System.in);

		n = Integer.parseInt(input.nextLine());

		if(n < 2) {
			if(input != null)
				input.close();
			throw new RuntimeException("Por favor, o primeiro argumento deve ser um número inteiro maior ou igual a 2");
		}

		// a partir daqui é o tabuleiro

		linhas = new ArrayList<String>();

		int qtdN = n * n;
		for(int i = 0; i < qtdN; i++) {
			linhas.add(input.nextLine());
		}
		input.close();

		for(int i = 0; i < qtdN; i++) {
			String[] aux = linhas.get(i).split(" ");
			if(aux.length != qtdN)
			{
				throw new RuntimeException("Entrada de dados errada. Por favor informe um conjunto de n² números separados por espaço, onde n é o primeiro argumento da primeira linha.");
			}
		}

		vars = new int[qtdN * qtdN];
		for(int i = 0; i < qtdN; i++) {
			String[] aux = linhas.get(i).split(" ");
			for(int j = 0; j < aux.length; j++) {
				vars[(i * qtdN) + j] = Integer.parseInt(aux[j]);
			}
		}
	}

}
