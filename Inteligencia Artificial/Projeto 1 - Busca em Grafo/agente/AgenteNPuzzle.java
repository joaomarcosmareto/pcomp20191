package agente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import ambiente.Tabuleiro_Puzzle;

public class AgenteNPuzzle {

	public int[][] objetivo;

	public HashMap<Integer, Posicao> posicoesObjetivo;

	public Tabuleiro_Puzzle estadoAtual;

	public HashMap<String, Tabuleiro_Puzzle> visitados;
	public HashMap<String, Tabuleiro_Puzzle> visitados2;

	public Posicao pos;

	public LinkedList<Tabuleiro_Puzzle> solucao = new LinkedList<Tabuleiro_Puzzle>();

	public AgenteNPuzzle(Tabuleiro_Puzzle tab) {

		// define o objetivo do agente NPuzzle
		this.objetivo = new int[tab.tamanhoTabuleiro][tab.tamanhoTabuleiro];
		posicoesObjetivo = new HashMap<Integer, Posicao>();

		for(int i = 0; i < tab.tamanhoTabuleiro; i++) {
			for(int j = 0; j < tab.tamanhoTabuleiro; j++) {
				this.objetivo[i][j] = (i * tab.tamanhoTabuleiro) + j + 1;
				// define as coordenadas de cada posicao do objetivo para facilitar o cálculo da distancia de manhattan
				posicoesObjetivo.put(this.objetivo[i][j], new Posicao(i,j));
			}
		}
		this.objetivo[tab.tamanhoTabuleiro - 1][tab.tamanhoTabuleiro - 1] = 0;
		posicoesObjetivo.put(this.objetivo[tab.tamanhoTabuleiro - 1][tab.tamanhoTabuleiro - 1], new Posicao(tab.tamanhoTabuleiro - 1,tab.tamanhoTabuleiro - 1));

		// define o estado atual como sendo o estado informado pelo usuário como argumento da segunda linha de entrada de dados.
		this.estadoAtual = tab;

		// define a posicao atual do vazio;
		this.pos = tab.retornaPosicaoVazio();

		visitados = new HashMap<String, Tabuleiro_Puzzle>();
		visitados2 = new HashMap<String, Tabuleiro_Puzzle>();
	}


	public boolean detectarSolubilidade() {

		// se o tamanho do tabuleiro é impar
		if(this.estadoAtual.tamanhoTabuleiro % 2 != 0) {
			// se tem numero de inversões par, é solúvel
			if(this.estadoAtual.getNumeroInversoes() % 2 == 0)
				return true;
			// se tem numero de inversões impar, é insolúvel
			else
				return false;
		}
		// se o tamanho do tabuleiro é par
		else{
			// se número de inversões + pos.x (numero da linha do vazio) for impar, é solúvel
			if((this.estadoAtual.getNumeroInversoes() + this.pos.x) % 2 != 0)
				return true;
			// se número de inversões + pos.x (numero da linha do vazio) for par, é insolúvel
			else
				return false;
		}
	}

	public ArrayList<Tabuleiro_Puzzle> expandir2(Tabuleiro_Puzzle estado) {

		ArrayList<Tabuleiro_Puzzle> estados = new ArrayList<Tabuleiro_Puzzle>();

		// identifica onde está o vazio
		Posicao vazio = this.estadoAtual.retornaPosicaoVazio();

		// se está na primeira linha, as jogadas possíveis são esquerda, cima e direita
		if(vazio.x == 0)
		{
			// se vazio está no canto superior esquerdo, as jogadas possíveis são esquerda e cima
			if(vazio.y == 0)
			{
				// esquerda
				Tabuleiro_Puzzle tab1 = this.estadoAtual.cloneTab2(estado);
				tab1.moveEsquerda();
				tab1.custo_g += 1;
				estados.add(tab1);

				// cima
				Tabuleiro_Puzzle tab2 = this.estadoAtual.cloneTab2(estado);
				tab2.moveCima();
				tab2.custo_g += 1;
				estados.add(tab2);
			}
			// se vazio não está no canto superior esquerdo nem no direito, as jogadas possíveis são esquerda, cima e direita
			else if(vazio.y < this.estadoAtual.tamanhoTabuleiro -1)
			{
				// esquerda
				Tabuleiro_Puzzle tab1 = this.estadoAtual.cloneTab2(estado);
				tab1.moveEsquerda();
				tab1.custo_g += 1;
				estados.add(tab1);

				// cima
				Tabuleiro_Puzzle tab2 = this.estadoAtual.cloneTab2(estado);
				tab2.moveCima();
				tab2.custo_g += 1;
				estados.add(tab2);

				// direita
				Tabuleiro_Puzzle tab3 = this.estadoAtual.cloneTab2(estado);
				tab3.moveDireita();
				tab3.custo_g += 1;
				estados.add(tab3);
			}
			// se vazio está no canto superior direito, as jogadas possíveis são cima e direita
			else if(vazio.y == this.estadoAtual.tamanhoTabuleiro -1)
			{
				// direita
				Tabuleiro_Puzzle tab1 = this.estadoAtual.cloneTab2(estado);
				tab1.moveDireita();
				tab1.custo_g += 1;
				estados.add(tab1);

				// cima
				Tabuleiro_Puzzle tab2 = this.estadoAtual.cloneTab2(estado);
				tab2.moveCima();
				tab2.custo_g += 1;
				estados.add(tab2);
			}
			return estados;
		}
		// se está nas linhas centrais, as jogadas possíveis s�o esquerda, cima, baixo e direita
		else if(vazio.x < this.estadoAtual.tamanhoTabuleiro - 1)
		{
			// se vazio está no canto esquerdo, as jogadas possíveis s�o esquerda, cima e baixo
			if(vazio.y == 0)
			{
				// esquerda
				Tabuleiro_Puzzle tab1 = this.estadoAtual.cloneTab2(estado);
				tab1.moveEsquerda();
				tab1.custo_g += 1;
				estados.add(tab1);

				// cima
				Tabuleiro_Puzzle tab2 = this.estadoAtual.cloneTab2(estado);
				tab2.moveCima();
				tab2.custo_g += 1;
				estados.add(tab2);

				// baixo
				Tabuleiro_Puzzle tab3 = this.estadoAtual.cloneTab2(estado);
				tab3.moveBaixo();
				tab3.custo_g += 1;
				estados.add(tab3);
			}
			// se vazio não está no canto esquerdo nem no direito, as jogadas possíveis são esquerda, cima, baixo e direita
			else if(vazio.y < this.estadoAtual.tamanhoTabuleiro -1)
			{
				// esquerda
				Tabuleiro_Puzzle tab1 = this.estadoAtual.cloneTab2(estado);
				tab1.moveEsquerda();
				tab1.custo_g += 1;
				estados.add(tab1);

				// cima
				Tabuleiro_Puzzle tab2 = this.estadoAtual.cloneTab2(estado);
				tab2.moveCima();
				tab2.custo_g += 1;
				estados.add(tab2);

				// direita
				Tabuleiro_Puzzle tab3 = this.estadoAtual.cloneTab2(estado);
				tab3.moveDireita();
				tab3.custo_g += 1;
				estados.add(tab3);

				// baixo
				Tabuleiro_Puzzle tab4 = this.estadoAtual.cloneTab2(estado);
				tab4.moveBaixo();
				tab4.custo_g += 1;
				estados.add(tab4);
			}
			// se vazio está no canto direito, as jogadas possíveis são cima, baixo e direita
			else if(vazio.y == this.estadoAtual.tamanhoTabuleiro -1)
			{
				// direita
				Tabuleiro_Puzzle tab1 = this.estadoAtual.cloneTab2(estado);
				tab1.moveDireita();
				tab1.custo_g += 1;
				estados.add(tab1);

				// cima
				Tabuleiro_Puzzle tab2 = this.estadoAtual.cloneTab2(estado);
				tab2.moveCima();
				tab2.custo_g += 1;
				estados.add(tab2);

				// baixo
				Tabuleiro_Puzzle tab3 = this.estadoAtual.cloneTab2(estado);
				tab3.moveBaixo();
				tab3.custo_g += 1;
				estados.add(tab3);
			}
			return estados;
		}
		// se está na última linha, as jogadas possíveis são esquerda, baixo e direita
		else if(vazio.x == this.estadoAtual.tamanhoTabuleiro -1)
		{
			// se vazio está no canto inferior esquerdo, as jogadas possíveis são esquerda e baixo
			if(vazio.y == 0)
			{
				// esquerda
				Tabuleiro_Puzzle tab1 = this.estadoAtual.cloneTab2(estado);
				tab1.moveEsquerda();
				tab1.custo_g += 1;
				estados.add(tab1);

				// baixo
				Tabuleiro_Puzzle tab2 = this.estadoAtual.cloneTab2(estado);
				tab2.moveBaixo();
				tab2.custo_g += 1;
				estados.add(tab2);
			}
			// se vazio não está no canto inferior esquerdo nem no direito, as jogadas possíveis são esquerda, baixo e direita
			else if(vazio.y < this.estadoAtual.tamanhoTabuleiro -1)
			{
				// esquerda
				Tabuleiro_Puzzle tab1 = this.estadoAtual.cloneTab2(estado);
				tab1.moveEsquerda();
				tab1.custo_g += 1;
				estados.add(tab1);

				// baixo
				Tabuleiro_Puzzle tab2 = this.estadoAtual.cloneTab2(estado);
				tab2.moveBaixo();
				tab2.custo_g += 1;
				estados.add(tab2);

				// direita
				Tabuleiro_Puzzle tab3 = this.estadoAtual.cloneTab2(estado);
				tab3.moveDireita();
				tab3.custo_g += 1;
				estados.add(tab3);
			}
			// se vazio está no canto inferior direito, as jogadas possíveis são baixo e direita
			else if(vazio.y == this.estadoAtual.tamanhoTabuleiro -1)
			{
				// direita
				Tabuleiro_Puzzle tab1 = this.estadoAtual.cloneTab2(estado);
				tab1.moveDireita();
				tab1.custo_g += 1;
				estados.add(tab1);

				// baixo
				Tabuleiro_Puzzle tab2 = this.estadoAtual.cloneTab2(estado);
				tab2.moveBaixo();
				tab2.custo_g += 1;
				estados.add(tab2);
			}
			return estados;
		}


		return null;
	}

	public void printSolucao(Tabuleiro_Puzzle tab) {
		if(tab.pai != null)
		{
			printSolucao(tab.pai);
		}
		tab.printLine();
	}

	// número de Peças fora do lugar
	public void buscaEmLarguraSemRepetidosH1() {
		this.estadoAtual.h1(this.objetivo);
		LinkedList<Tabuleiro_Puzzle> fila = new LinkedList<Tabuleiro_Puzzle>();
		fila.add(this.estadoAtual);
		visitados2.putIfAbsent(this.estadoAtual.representacao, this.estadoAtual);
		while(fila.size() > 0) {

			//encontre o estado com menor custo total
			Collections.sort(fila);
			this.estadoAtual = fila.poll();

			//se o custo é 0, chegou no objetivo
			if(this.estadoAtual.custo_h <= 0){
				printSolucao(this.estadoAtual);
				break;
			}

			//if o objetivo não foi encontrado, atravessa a lista
			visitados2.putIfAbsent(this.estadoAtual.representacao, this.estadoAtual);

			ArrayList<Tabuleiro_Puzzle> filhos = expandir2(this.estadoAtual);

			for(Tabuleiro_Puzzle filho : filhos){
				// se o filho não está na fila de estados visitados
				if(!visitados2.containsKey(filho.representacao)){
					if(!fila.contains(filho)){
						filho.pai = this.estadoAtual;
						filho.f = filho.custo_g + filho.h1(this.objetivo);
						if(!fila.contains(filho)){
							fila.add(filho);
						}
					}
				}
			}
		}
	}

	// distancia de manhattan
	public void buscaEmLarguraSemRepetidosH2() {
		this.estadoAtual.h2(this.objetivo, this.posicoesObjetivo);
		LinkedList<Tabuleiro_Puzzle> fila = new LinkedList<Tabuleiro_Puzzle>();
		fila.add(this.estadoAtual);
		visitados2.putIfAbsent(this.estadoAtual.representacao, this.estadoAtual);
		while(fila.size() > 0) {

			//encontre o estado com menor custo total
			Collections.sort(fila);
			this.estadoAtual = fila.poll();

			//se o custo é 0, chegou no objetivo
			if(this.estadoAtual.custo_h <= 0){
				printSolucao(this.estadoAtual);
				break;
			}

			//if o objetivo não foi encontrado, atravessa a lista
			visitados2.putIfAbsent(this.estadoAtual.representacao, this.estadoAtual);

			ArrayList<Tabuleiro_Puzzle> filhos = expandir2(this.estadoAtual);

			for(Tabuleiro_Puzzle filho : filhos){
				// se o filho não está na fila de estados visitados
				if(!visitados2.containsKey(filho.representacao)){
					if(!fila.contains(filho)){
						filho.pai = this.estadoAtual;
						filho.f = filho.custo_g + filho.h2(this.objetivo, this.posicoesObjetivo);
						if(!fila.contains(filho)){
							fila.add(filho);
						}
					}
				}
			}
		}
	}

	// Gaschnig
	public void buscaEmLarguraSemRepetidosH3() {
		this.estadoAtual.h3(this.objetivo, this.posicoesObjetivo);
		LinkedList<Tabuleiro_Puzzle> fila = new LinkedList<Tabuleiro_Puzzle>();
		fila.add(this.estadoAtual);
		visitados2.putIfAbsent(this.estadoAtual.representacao, this.estadoAtual);
		while(fila.size() > 0) {

			//encontre o estado com menor custo total
			Collections.sort(fila);
			this.estadoAtual = fila.poll();

			//se o custo é 0, chegou no objetivo
			if(this.estadoAtual.custo_h <= 0){
				printSolucao(this.estadoAtual);
				break;
			}

			//if o objetivo néo foi encontrado, atravessa a lista
			visitados2.putIfAbsent(this.estadoAtual.representacao, this.estadoAtual);

			ArrayList<Tabuleiro_Puzzle> filhos = expandir2(this.estadoAtual);

			for(Tabuleiro_Puzzle filho : filhos){
				// se o filho não está na fila de estados visitados
				if(!visitados2.containsKey(filho.representacao)){
					if(!fila.contains(filho)){
						filho.pai = this.estadoAtual;
						filho.f = filho.custo_g + filho.h3(this.objetivo, this.posicoesObjetivo);
						if(!fila.contains(filho)){
							fila.add(filho);
						}
					}
				}
			}
		}
	}

	public boolean visitou(Tabuleiro_Puzzle estado) {
		return visitados.get(estado.representacao) != null;
	}

}