package ambiente;

import java.util.HashMap;

import agente.Posicao;

public class Tabuleiro_Puzzle implements Comparable<Tabuleiro_Puzzle>{

	public int tamanhoTabuleiro;
	public int[][] tabuleiro;
	public String representacao;
	public Posicao vazio;
	public int custo;

	public int custo_g = 0; // custo do nó raiz ao nó atual (profundidade)
	public int custo_h; // custo da heurística
	public int f;

	public Tabuleiro_Puzzle pai;


	public Tabuleiro_Puzzle() {

	}

	public Tabuleiro_Puzzle(int tamanhoTabuleiro, String representacaoTabuleiro) {
		this.tamanhoTabuleiro = tamanhoTabuleiro;
		this.representacao = "";
		this.build(representacaoTabuleiro);
		this.pai = null;
		this.f = this.custo_g + custo_h;
	}

	public void build(String representacaoTabuleiro) {
		this.tabuleiro = new int[tamanhoTabuleiro][tamanhoTabuleiro];

		String[] aux = representacaoTabuleiro.split(" ");

		for(int i = 0; i < this.tamanhoTabuleiro; i++) {
			for(int j = 0; j < this.tamanhoTabuleiro; j++) {
				this.tabuleiro[i][j] = Integer.parseInt(aux[i * this.tamanhoTabuleiro + j]);
				this.representacao += this.tabuleiro[i][j]+" ";
			}
		}
		this.representacao = this.representacao.trim();
		this.vazio = this.retornaPosicaoVazio();
	}

	public Tabuleiro_Puzzle makeTab(int[][] estado) {
		Tabuleiro_Puzzle tab = new Tabuleiro_Puzzle();
		tab.representacao = "";
		tab.pai = null;

		tab.tamanhoTabuleiro = this.tamanhoTabuleiro;
		tab.tabuleiro = new int[tamanhoTabuleiro][tamanhoTabuleiro];

		for(int i = 0; i < this.tamanhoTabuleiro; i++) {
			for(int j = 0; j < this.tamanhoTabuleiro; j++) {
				tab.tabuleiro[i][j] = estado[i][j];
				tab.representacao += tab.tabuleiro[i][j]+" ";
			}
		}
		tab.representacao = tab.representacao.trim();
		tab.vazio = tab.retornaPosicaoVazio();
		return tab;
	}

	public Tabuleiro_Puzzle cloneTab2(Tabuleiro_Puzzle estado) {
		Tabuleiro_Puzzle tab = new Tabuleiro_Puzzle();
		tab.representacao = "";
		tab.pai = null;

		tab.tamanhoTabuleiro = estado.tamanhoTabuleiro;
		tab.tabuleiro = new int[tamanhoTabuleiro][tamanhoTabuleiro];

		for(int i = 0; i < estado.tamanhoTabuleiro; i++) {
			for(int j = 0; j < estado.tamanhoTabuleiro; j++) {
				tab.tabuleiro[i][j] = estado.tabuleiro[i][j];
				tab.representacao += tab.tabuleiro[i][j]+" ";
			}
		}
		tab.representacao = tab.representacao.trim();
		tab.vazio = tab.retornaPosicaoVazio();

		tab.pai = estado;
		return tab;
	}

	public void atualizaRepresentacao() {
		this.representacao = "";
		for(int i = 0; i < this.tamanhoTabuleiro; i++) {
			for(int j = 0; j < this.tamanhoTabuleiro; j++) {
				this.representacao += this.tabuleiro[i][j]+" ";
			}
		}
		this.representacao = this.representacao.trim();
	}

	public Posicao retornaPosicaoVazio() {
		for(int i = 0; i < this.tamanhoTabuleiro; i++) {
			for(int j = 0; j < this.tamanhoTabuleiro; j++) {
				if(this.tabuleiro[i][j] == 0)
					return new Posicao(i,j);
			}
		}
		return null;
	}

	public Posicao retornaPosicao(int valor) {
		for(int i = 0; i < this.tamanhoTabuleiro; i++) {
			for(int j = 0; j < this.tamanhoTabuleiro; j++) {
				if(this.tabuleiro[i][j] == valor)
					return new Posicao(i,j);
			}
		}
		return null;
	}

	public void moveEsquerda() {
		int aux = this.tabuleiro[vazio.x][vazio.y + 1];
		this.tabuleiro[vazio.x][vazio.y + 1] = 0;
		this.tabuleiro[vazio.x][vazio.y] = aux;
		this.atualizaRepresentacao();
	}

	public void moveCima() {
		int aux = this.tabuleiro[vazio.x + 1][vazio.y];
		this.tabuleiro[vazio.x + 1][vazio.y] = 0;
		this.tabuleiro[vazio.x][vazio.y] = aux;
		this.atualizaRepresentacao();
	}

	public void moveDireita() {
		int aux = this.tabuleiro[vazio.x][vazio.y - 1];
		this.tabuleiro[vazio.x][vazio.y - 1] = 0;
		this.tabuleiro[vazio.x][vazio.y] = aux;
		this.atualizaRepresentacao();
	}

	public void moveBaixo() {
		int aux = this.tabuleiro[vazio.x - 1][vazio.y];
		this.tabuleiro[vazio.x - 1][vazio.y] = 0;
		this.tabuleiro[vazio.x][vazio.y] = aux;
		this.atualizaRepresentacao();
	}

	// Número de peças fora do lugar
	public int h1(int[][] objetivo) {
		int h = 0;

		for(int i = 0; i < this.tamanhoTabuleiro; i++) {
			for(int j = 0; j < this.tamanhoTabuleiro; j++) {
				if(this.tabuleiro[i][j] != objetivo[i][j])
					h += 1;
			}
		}
		this.custo_h = h;
		return this.custo_h;
	}

	// Distancia de Manhattan
	public int h2(int[][] objetivo, HashMap<Integer, Posicao> posicoesObjetivo) {
		int h = 0;
		for(int i = 0; i < this.tamanhoTabuleiro; i++) {
			for(int j = 0; j < this.tamanhoTabuleiro; j++) {

				// determinar a distancia da peça da vez ao objetivo
				// 1. determinar a coordenada da peça atual
				// 2. determinar a coordenada da peça atual no objetivo
				// 3. calcular a diferença entre o x-objetivo menos o x-atual
				// 4. calcular a diferença entre o y-objetivo menos o y-atual
				// 5. realizar a soma dos resultados dos passos 3 e 4
				Posicao coord_obj = posicoesObjetivo.get(new Integer(this.tabuleiro[i][j]));
				h = h + Math.abs(coord_obj.x - i) + Math.abs(coord_obj.y - j);
			}
		}
		this.custo_h = h;
		return this.custo_h;
	}

	// Gaschnig
	public int h3(int[][] objetivo, HashMap<Integer, Posicao> posicoesObjetivo) {
		int h = 0;
		Tabuleiro_Puzzle tab = this.cloneTab2(this);

		Tabuleiro_Puzzle tab_obj = tab.makeTab(objetivo);

		boolean fez_swap = false;

		while(true) {
			fez_swap = false;
			tab.atualizaRepresentacao();

			if(tab.representacao.equals(tab_obj.representacao))
				break;
			else {
				/*
				Para calcular a heurística do Gaschnig, repita o seguinte procedimento até que o estado
				objetivo seja alcançado:
				seja B o local atual do espaço em branco;
				seja X o valor do bloco da coordenada B no objetivo;
				se B estiver ocupado pelo bloco X (não o branco) no estado objetivo,
					mova X para B;
				caso contrário,
					mova qualquer bloco perdido para B.
				Some um ao custo calculado pela heurística para cadamovimentação
				 */
				Posicao b = tab.retornaPosicaoVazio();
				int x = objetivo[b.x][b.y];
				if(x != 0) {
					Posicao pos_x_atual = tab.retornaPosicao(x);
					int aux = tab.tabuleiro[b.x][b.y];
					tab.tabuleiro[b.x][b.y] = tab.tabuleiro[pos_x_atual.x][pos_x_atual.y];
					tab.tabuleiro[pos_x_atual.x][pos_x_atual.y] = aux;
					h++;
				}
				else {
					for(int i = 0; i < tamanhoTabuleiro; i++) {
						if(!fez_swap) {
							for(int j = 0; j < tamanhoTabuleiro; j++) {

								if(tab.tabuleiro[i][j] == objetivo[i][j])
									continue;
								else{
									int aux = tab.tabuleiro[i][j];
									tab.tabuleiro[i][j] = tab.tabuleiro[b.x][b.y];
									tab.tabuleiro[b.x][b.y] = aux;
									h++;
									fez_swap = true;
									break;
								}
							}
						}
						else break;
					}
				}
			}
		}
		this.custo_h = h;
		return this.custo_h;
	}


	public int getNumeroInversoes() {
		int ref = 0;
		int ref2 = 0;

		int inversoes = 0;

		String[] aux = this.representacao.split(" ");

		for(int i = 0; i < aux.length; i++) {
			ref = Integer.parseInt(aux[i]);
			if(ref != 0) {
				for(int j = i + 1; j < aux.length; j++) {
					ref2 = Integer.parseInt(aux[j]);
					if(ref2 != 0 && ref2 < ref) {
						inversoes++;
					}
				}
			}
		}
		return inversoes;
	}

	public void print() {
		for(int i = 0; i < tamanhoTabuleiro; i++) {
			for(int j = 0; j < tamanhoTabuleiro; j++) {
				System.out.print(tabuleiro[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public void printLine() {
		for(int i = 0; i < tamanhoTabuleiro; i++) {
			for(int j = 0; j < tamanhoTabuleiro; j++) {
				System.out.print(tabuleiro[i][j] + " ");
			}
		}
		System.out.println("");
	}

	@Override
	public int compareTo(Tabuleiro_Puzzle o) {
		Tabuleiro_Puzzle outro = o;
		if (this.f < outro.f) {
			return -1;
		}
		if (this.f > outro.f) {
			return 1;
		}
		return 0;
	}

}
