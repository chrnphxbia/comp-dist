public class Fraction implements java.io.Serializable {
	int numer, denom;
	private static final long serialVersionUID = 1L;

	public Fraction(int numer, int denom) {
		this.numer = numer;
		this.denom = denom;
	}

	public Fraction() {}

	public Fraction multiply(Fraction other) {
		Fraction answer = new Fraction();
		answer.numer = this.numer * other.numer;
		answer.denom = this.denom * other.denom;

		return answer;
	}

	public Fraction divide(Fraction other) {
		Fraction answer = new Fraction();
		answer.numer = this.numer * other.denom;
		answer.denom = this.denom * other.numer;

		return answer;
	}

	public Fraction max(Fraction other) {
		return (this.numer/this.denom >= other.numer/other.denom) ? this : other;
	}

	public Fraction min(Fraction other) {
		return (this.numer/this.denom < other.numer/other.denom) ? this : other;
	}

	public String print() { return numer + "/" + denom; }
}