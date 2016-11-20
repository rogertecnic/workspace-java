package herança_array;

public class Quadrado extends FigPlana{
	private double lado;
	
	/**
	 * 
	 * @param n recebe String nome
	 * @param l recebe double o lado do quadrado
	 * @param cx recebe double centro x
	 * @param cy recebe double centro y
	 */
	public Quadrado(String n, double l, double cx, double cy) {
		super.setNome(n);
		setLado(l);
		super.setCx(cx);
		super.setCy(cy);
	}

	public double getLado() {
		return lado;
	}
	public void setLado(double l) {
		this.lado = l;
		super.setArea(lado*lado);
		super.setPerimetro(lado*4);
	}

}
