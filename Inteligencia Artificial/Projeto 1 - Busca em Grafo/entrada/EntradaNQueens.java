package entrada;

public class EntradaNQueens extends Entrada {

	public EntradaNQueens(int qtdLinhas) {
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
			throw new RuntimeException("Por favor, o primeiro argumento deve ser um número inteiro maior ou igual a 4");
		}
		if(arg1 < 4)
		{
			throw new RuntimeException("Por favor, o primeiro argumento deve ser um número inteiro maior ou igual a 4");
		}

		arg2 = argsln1[1];
		if(!arg2.equalsIgnoreCase("dfs") && !arg2.equalsIgnoreCase("bfs"))
		{
			throw new RuntimeException("Por favor, o segundo argumento deve ser a identificação do algoritmo de busca a ser utilizado (dfs ou bfs)");
		}
	}
}
