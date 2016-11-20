
public class Posicao {

	int x;
	int y;

	Posicao() {
		this.x = 0;
		this.y = 0;
	}

	Posicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void setX(int x) {
		this.x = x;
	}

	void setY(int y) {
		this.y = y;
	}

	int getX() {
		return this.x;
	}

	int getY() {
		return this.y;
	}

	int dist(Posicao p) {
		return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
	}

	int dist(int x, int y) {
		Posicao p = new Posicao(x, y);
		return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
	}

	@Override
	public boolean equals(Object p) { // verifica se as posiçoes sao iguais
		if (p instanceof Posicao) {
			Posicao newP = (Posicao) p;
			if (newP.x == this.x && this.y == newP.y) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}
}