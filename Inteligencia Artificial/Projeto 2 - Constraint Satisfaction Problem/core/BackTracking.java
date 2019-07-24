package core;

import java.util.ArrayList;
import java.util.List;

public class BackTracking{

	public boolean solve(Problema p) {
		return recursiveBackTrackingSearch(p);
	}

	private boolean recursiveBackTrackingSearch(Problema p) {
		if (p.isSolucao()) {
			System.out.println("Resposta");
			return true;
		} else {
			Variavel v = selecionaVariavelNaoAtribuida(p);

			for (Object value : ordenaValoresDominio(v)) {

				p.atribui(v, value);

				if (p.consistente() && !p.encontrouDominioVazio()) {
					if (recursiveBackTrackingSearch(p))
						return true;
					else {
						p.desatribui(v);
					}
				}
				else {
					p.desatribui(v);
				}
			}
		}
		return false;
	}

	// usar alguma heurística aqui talvez melhora o desempenho
	// h1 -> variavel nao atribuída com menor domínio.
	protected Variavel selecionaVariavelNaoAtribuida(Problema p) {

		ArrayList<Variavel> candidatas = new ArrayList<Variavel>();
		for (Variavel v : p.variaveis) {
			if (v.valor == null)
				candidatas.add(v);
		}
		int size = Integer.MAX_VALUE;
		Variavel aux = null;
		for(Variavel v : candidatas) {
			if(v.dominio.size() < size) {
				size = v.dominio.size();
				aux = v;
			}
		}
		return aux;
	}

	protected List<Object> ordenaValoresDominio(Variavel v) {
		ArrayList<Object> clone = new ArrayList<Object>(v.dominio.size());
		clone.addAll(v.dominio);
		return clone;
	}
}