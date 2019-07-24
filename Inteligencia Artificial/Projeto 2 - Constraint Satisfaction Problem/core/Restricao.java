package core;

import java.util.List;

public interface Restricao {
	List<Variavel> getEscopo();
	boolean satisfeito();
}