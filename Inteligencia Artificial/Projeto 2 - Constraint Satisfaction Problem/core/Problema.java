package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Problema {
	public List<Variavel> variaveis;
	public List<Restricao> restricoes;
	HashMap<Variavel, List<Par<Variavel, Object>>> podas;


	public Problema() {
		variaveis = new ArrayList<Variavel>();
		restricoes = new ArrayList<Restricao>();
		podas = new HashMap<Variavel, List<Par<Variavel, Object>>>();
	}

	public void addRestricao(Restricao r) {
		restricoes.add(r);
		for (Variavel v : r.getEscopo())
			v.restricoes.add(r);
	}

	public boolean consistente() {
		for (Restricao r : this.restricoes)
			if (!r.satisfeito())
				return false;
		return true;
	}

	public boolean completo() {
		for (Variavel var : variaveis) {
			if (var.valor == null)
				return false;
		}
		return true;
	}

	public boolean isSolucao() {
		return consistente() && completo();
	}

	public String printEstado(){
		boolean comma = false;
		StringBuffer result = new StringBuffer("{");
		for (Variavel var : variaveis) {
			if (comma)
				result.append(", ");
			result.append(var + "=" + var.valor);
			comma = true;
		}
		result.append("}");
		return result.toString();
	}

	public Problema copy() {
		Problema copy = new Problema();
		copy.variaveis.addAll(this.variaveis);
		copy.restricoes.addAll(this.restricoes);
		return copy;
	}

	public void propagaAtribuicao(Variavel v) {
		ArrayList<Par<Variavel, Object>> pares = new ArrayList<Par<Variavel, Object>>();
		for(Restricao r : v.restricoes) {
			List<Variavel> variaveis = r.getEscopo();
			for(Variavel var : variaveis) {
				if(var.valor == null) {
					if(var.dominio.contains(v.valor)) {
						var.dominio.remove(v.valor);
						pares.add(new Par<Variavel, Object>(var, v.valor));
					}
				}
			}

		}
		podas.put(v, pares);
	}

	public void propagaDesatribuicao(Variavel v) {
		for(Par<Variavel, Object> par : podas.get(v)) {
			par.getFirst().dominio.add(par.getSecond());
		}
		podas.remove(v);
	}

	public void atribui(Variavel variavel, Object valor) {
		variavel.atribui(valor);
		propagaAtribuicao(variavel);
	}

	public void desatribui(Variavel variavel) {
		propagaDesatribuicao(variavel);
		variavel.desatribui();
	}

	public boolean encontrouDominioVazio() {
		for(Variavel v : variaveis) {
			if(v.valor == null && v.dominio.size() == 0)
				return true;
		}
		return false;
	}

}
