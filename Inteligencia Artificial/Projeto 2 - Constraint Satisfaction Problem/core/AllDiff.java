package core;

import java.util.ArrayList;
import java.util.List;

public class AllDiff implements Restricao {
	public List<Variavel> escopo;

	public AllDiff(int size) {
		this.escopo = new ArrayList<Variavel>(size);
	}

	@Override
	public boolean satisfeito() {
		boolean[] seen = new boolean[escopo.size() + 1];
		for (Variavel v : escopo) {
			Integer val = (Integer) v.valor;
			if (val != null) {
				if(seen[val])  {
					return false;
				}
				else
					seen[val] = true;
			}
		}
		return true;
	}

	@Override
	public List<Variavel> getEscopo() {
		return escopo;
	}
}
