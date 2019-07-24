package core;

import java.util.ArrayList;
import java.util.List;

public class Variavel {
	public String nome;
	public Object valor;
	public List<Object> dominio;
	public List<Restricao> restricoes;

	public Variavel(String nome) {
		this.nome = nome;
		this.valor = null;
		this.dominio = new ArrayList<Object>();
		this.restricoes = new ArrayList<Restricao>();
	}

	public Variavel(String nome, Object valor) {
		this.nome = nome;
		this.valor = valor;
		this.dominio = new ArrayList<Object>();
		this.restricoes = new ArrayList<Restricao>();
	}

	public Variavel(String nome, Object[] dominio) {
		this.nome = nome;
		this.valor = null;
		this.dominio = new ArrayList<Object>();
		for(Object valor : dominio) {
			this.dominio.add(valor);
		}
		this.restricoes = new ArrayList<Restricao>();
	}

	public void atribui(Object valor) {
		this.valor = valor;
		this.dominio.remove(valor);
	}

	public void desatribui() {
		this.dominio.add(this.valor);
		this.valor = null;
	}


	@Override
	public String toString() {
		return this.nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() == getClass())
			return this.nome.equals(((Variavel) obj).nome);
		return false;
	}

	@Override
	public int hashCode() {
		return nome.hashCode();
	}

}
