package herança_array;

public class Circulo extends FigPlana{
	private double raio;
	private static final double PI = 3.1416;
	/**
	 * 
	 * @param n recebe String nome
	 * @param r recebe double raio do circulo
	 * @param cx recebe double centro x
	 * @param cy recebe double centro y
	 */
	public Circulo(String n, double r, double cx, double cy ) {
		super.setNome(n);
		super.setCx(cx);
		super.setCy(cy);
		this.setRaio(r);
	}

	public double getRaio() {
		return raio;
	}
	public void setRaio(double raio) {
		this.raio = raio;
		super.setArea(2*PI*raio);
		super.setPerimetro(2*PI*raio);
	}


}
