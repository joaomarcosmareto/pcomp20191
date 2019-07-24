package entrada;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Entrada {

	public Scanner stdin;

	ArrayList<String> linhas;

	public int arg1;
	public String arg2;
	public String representacaoTabuleiro;

	public Entrada(int qtdLinhas) {
		stdin = new Scanner(System.in);
		linhas = new ArrayList<String>();
		for(int i = 0; i < qtdLinhas; i++) {
			linhas.add(stdin.nextLine());
		}
		stdin.close();
	}

	public void printLines() {
		for(int i = 0; i < linhas.size(); i++) {
			System.out.println(linhas.get(i));
		}
	}

}
