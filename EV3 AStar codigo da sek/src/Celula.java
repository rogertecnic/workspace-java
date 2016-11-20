
public class Celula {

	public static int commonSize = 20; // tamanho padr�o das c�lulas

	private float size; // tamanho da c�lula
	private boolean occupied = true;// est� ocupado?
	private Posicao posicao;// posi��o da c�lula no mapa
	private boolean checked;// est� checado?
	private int typeOfObject;
	private boolean temParede;
	
	/**
	 * @f custo da Celula (f=g+h)
	 */
	public int f; 
	/**
	 * @g dist de andar at� a Celula (cada caminho dar� um g diferente)
	 */
	public int g;
	/**
	 * @h heuristica, dist que falta da celula ate o final usando o metod manhattan
	 */
	public int h;
	/**
	 * @pai Celula anterior a qual se chegou na celula atual (filho)
	 */
	public Celula pai;

	Celula(Posicao posicao) {
		this(commonSize, true, posicao, false);
	}

	Celula(int size, Posicao posicao, boolean checked) {
		this.size = size;
		this.occupied = true;
		this.posicao = posicao;
		this.checked = checked;
	}

	public Celula(float size, boolean occupied, Posicao posicao, boolean checked) {
		this.size = size;
		this.occupied = occupied;
		this.checked = checked;
		this.f = 0;
		this.g = 0;
		this.h = 0;
		this.pai = null;
		this.posicao = posicao;
	}

	/**
	 * apaga os valores de f,g,h e do parent
	 */
	public void apagar() {
		this.f = 0;
		this.g = 0;
		this.h = 0;
		this.pai = null;
	}

	public int getTypeOfObject() {
		return typeOfObject;
	}

	public void setTypeOfObject(int typeOfObject) {
		this.typeOfObject = typeOfObject;
	}

	public float getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	public boolean temParede() {
		return temParede;
	}

	public void setParede(boolean temParede) {
		this.temParede = temParede;
	}
	
	public Posicao getPosicao() {
		return this.posicao;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isChecked() {
		return this.checked;
	}
}