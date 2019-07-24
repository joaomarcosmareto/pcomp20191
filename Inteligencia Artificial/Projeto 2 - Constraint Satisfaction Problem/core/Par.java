package core;

public class Par<X, Y> {
	private final X a;

	private final Y b;

	public Par(X a, Y b) {
		this.a = a;
		this.b = b;
	}

	public X getFirst() {
		return a;
	}

	public Y getSecond() {
		return b;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Par<?, ?>) {
			Par<?, ?> p = (Par<?, ?>) o;
			return a.equals(p.a) && b.equals(p.b);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return a.hashCode() + 31 * b.hashCode();
	}

	@Override
	public String toString() {
		return "< " + getFirst().toString() + " , " + getSecond().toString()
				+ " > ";
	}
}