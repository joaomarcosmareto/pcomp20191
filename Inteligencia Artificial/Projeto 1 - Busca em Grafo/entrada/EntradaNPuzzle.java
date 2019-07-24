package entrada;

import java.util.ArrayList;
import java.util.HashSet;

public class EntradaNPuzzle extends Entrada{

	public EntradaNPuzzle(int qtdLinhas) {
		super(qtdLinhas);

		String[] argsln1 = linhas.get(0).split(" ");

		if(argsln1.length != 2)
		{
			throw new RuntimeException("Entrada de dados errada, por favor informe dois argumentos.");
		}

		try {
			arg1 = Integer.parseInt(argsln1[0]);
		}
		catch(Exception e) {
			throw new RuntimeException("Por favor, o primeiro argumento deve ser um número inteiro maior ou igual a 3");
		}
		if(arg1 < 3)
		{
			throw new RuntimeException("Por favor, o primeiro argumento deve ser um número inteiro maior ou igual a 3");
		}

		arg2 = argsln1[1];
		if(!arg2.equalsIgnoreCase("h1") && !arg2.equalsIgnoreCase("h2") && !arg2.equalsIgnoreCase("h3"))
		{
			throw new RuntimeException("Por favor, o segundo argumento deve ser a identificação da heurística a ser utilizada (h1 ou h2)");
		}

		// a partir daqui é a linha que representa o tabuleiro
		int qtdN = arg1 * arg1;
		String[] aux = linhas.get(1).split(" ");
		if(aux.length != qtdN)
		{
			throw new RuntimeException("Entrada de dados errada, por favor informe um conjunto de N x N números separados por espaço, onde N é o primeiro argumento da primeira linha.");
		}

		HashSet<String> set = new HashSet<String>();
		for(int i = 0; i < aux.length; i++) {
			set.add(aux[i]);
		}
		if(set.size() < aux.length) {
			throw new RuntimeException("Entrada de dados errada, por favor não informe números repetidos");
		}

		ArrayList<String> listaPadrao = new ArrayList<String>();
		for(int i = 0; i < qtdN; i++) {
			listaPadrao.add(i + "");
		}

		if(!set.containsAll(listaPadrao)) {
			throw new RuntimeException("Entrada de dados errada, por favor informe números entre 0 e (N x N) - 1, onde N é o primeiro argumento da primeira linha.");
		}
		representacaoTabuleiro = "";
		for(int i = 0; i < aux.length; i++) {
			representacaoTabuleiro += aux[i] + " ";
		}
		representacaoTabuleiro = representacaoTabuleiro.trim();
	}

}
