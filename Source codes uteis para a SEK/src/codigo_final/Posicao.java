package codigo_final;

/**
 * 
 * @author Rogério
 *	Classe alterada para dar a posição da celula em si, não a posição
 *	em cm das coordenadas do robo
 */
public class Posicao {
	public static final int NORTE = 0;
	public static final int LESTE = 1;
	public static final int SUL = 2;
	public static final int OESTE = 3;
	// usada somente para orientar o robo
	// celulas não usam esse parametro
	public int orientacao = NORTE; 
	
	public int x; // coordenada x em cm
	public int y; // coordenada y em cm

	public Posicao(int x, int y) {
		this.x = x;
		this.y = y;
		
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