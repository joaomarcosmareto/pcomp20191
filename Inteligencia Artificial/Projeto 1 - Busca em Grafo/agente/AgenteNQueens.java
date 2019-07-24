package agente;

import java.util.LinkedList;
import java.util.Stack;

import ambiente.Tabuleiro_Queens;

public class AgenteNQueens {

	public Tabuleiro_Queens estadoAtual;

	public AgenteNQueens(Tabuleiro_Queens tab) {
		this.estadoAtual = tab;
	}


	// checa se a posição é válida para a coluna informada, avaliando a linha pela esquerda e as diagonais à esquerda da coluna informada
	public boolean estadoValido(int tabuleiro[][], int linha, int coluna)
	{
		if(coluna == this.estadoAtual.tamanhoTabuleiro)
			coluna = -1;
		// checa linha na esquerda
		for (int i = 0; i < coluna; i++)
			if (tabuleiro[linha][i] == 1)
				return false;

		// checa diagonal esquerda superior
		for (int i = linha, j = coluna; i >= 0 && j >= 0; i--, j--)
			if (tabuleiro[i][j] == 1)
				return false;

		// checa diagonal esquerda inferior
		for (int i = linha, j = coluna; j >= 0 && i < this.estadoAtual.tamanhoTabuleiro; i++, j--)
			if (tabuleiro[i][j] == 1)
				return false;

		return true;
	}

	public LinkedList<Tabuleiro_Queens> expandir(Tabuleiro_Queens estado) {
		LinkedList<Tabuleiro_Queens> estados = new LinkedList<Tabuleiro_Queens>();
		int linha = 0;
		int coluna = this.estadoAtual.colunaEstado + 1;
		while(linha < this.estadoAtual.tamanhoTabuleiro) {
			if(estadoValido(this.estadoAtual.tabuleiro, linha, coluna)) {
				// se a coordenada for uma posicao válida,
				// clona o tabuleiro e poe uma rainha nessa linha, retornando como um estado novo.
				Tabuleiro_Queens tab = this.estadoAtual.cloneTab(estado.tabuleiro);
				tab.tabuleiro[linha][coluna] = 1;
				tab.colunaEstado = coluna;
				estados.add(tab);
			}
			linha++;
		}
		return estados;
	}


	public void buscaEmLargura() {
		LinkedList<Tabuleiro_Queens> lista = new LinkedList<Tabuleiro_Queens>();
		lista.add(this.estadoAtual);
		while(lista.size() > 0) {
			this.estadoAtual = lista.poll();
			if(terminou()) {
				break;
			}
			else {
				lista.addAll(expandir(this.estadoAtual));
			}
		}
		this.printSolucao();
	}

	public void buscaEmProfundidade() {
		Stack<Tabuleiro_Queens> lista = new Stack<Tabuleiro_Queens>();
		lista.push(this.estadoAtual);
		while(lista.size() > 0) {
			this.estadoAtual = lista.pop();
			if(terminou()) {
				break;
			}
			else {
				lista.addAll(expandir(this.estadoAtual));
			}
		}
		this.printSolucao();
	}

	public void printSolucao() {
		String r = "";
		for(int c = 0 ; c < this.estadoAtual.tamanhoTabuleiro; c ++) {
			int soma = 0;
			for(int l = 0 ; l < this.estadoAtual.tamanhoTabuleiro; l ++) {
				soma += 1;
				if(this.estadoAtual.tabuleiro[l][c] == 1)
					break;
			}
			r += soma + " ";
		}
		System.out.println(r.trim());
	}

	public boolean terminou() {
		int queens = 0;
		for(int i = 0; i < this.estadoAtual.tamanhoTabuleiro; i++) {
			for(int j = 0; j < this.estadoAtual.tamanhoTabuleiro; j++) {
				if(this.estadoAtual.tabuleiro[i][j] == 1)
					queens++;
			}
		}
		return queens == this.estadoAtual.tamanhoTabuleiro;
	}
}
