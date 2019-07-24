package ambiente;

public class Tabuleiro_Queens {
	public int tamanhoTabuleiro;
	public int[][] tabuleiro;
	public int colunaEstado;

	public Tabuleiro_Queens(int tamanhoTabuleiro) {
		this.tamanhoTabuleiro = tamanhoTabuleiro;
		this.build();
	}

	public Tabuleiro_Queens() {	}

	public void build() {
		this.tabuleiro = new int[tamanhoTabuleiro][tamanhoTabuleiro];
		this.colunaEstado = -1;
		for(int i = 0; i < this.tamanhoTabuleiro; i++) {
			for(int j = 0; j < this.tamanhoTabuleiro; j++) {
				this.tabuleiro[i][j] = 0;
			}
		}
	}

	public Tabuleiro_Queens cloneTab(int[][] estado) {
		Tabuleiro_Queens tab = new Tabuleiro_Queens();
		tab.tamanhoTabuleiro = this.tamanhoTabuleiro;
		tab.tabuleiro = new int[tamanhoTabuleiro][tamanhoTabuleiro];
		tab.colunaEstado = this.colunaEstado;

		for(int i = 0; i < this.tamanhoTabuleiro; i++) {
			for(int j = 0; j < this.tamanhoTabuleiro; j++) {
				tab.tabuleiro[i][j] = estado[i][j];
			}
		}
		return tab;
	}




}
