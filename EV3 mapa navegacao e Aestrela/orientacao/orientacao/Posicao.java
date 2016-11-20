package orientacao;

/**
 * 
 * @author Rogério
 *	Classe alterada para dar a posição da celula em si, não a posição
 *	em cm das coordenadas do robo
 */
public class Posicao {
	public static final int NORTE = 0;
	public static final int OESTE = 1;
	public static final int SUL = 2;
	public static final int LESTE = 3;
	// usada somente para orientar o robo
	// celulas não usam esse parametro
	public int orientacao = NORTE; 
	
	private int x; // coordenada x em cm
	private int y; // coordenada y em cm
	private float celTamanho; // tamanho da celula em cm

	public Posicao(float x, float y, float celTam) {
		celTamanho = celTam;
		this.setX(x);
		this.setY(y);
		
	}

	public void setX(float x) {
		
		this.x = (int)(x/this.celTamanho);
	}

	public void setY(float y) {
		this.y = (int)(y/this.celTamanho);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
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