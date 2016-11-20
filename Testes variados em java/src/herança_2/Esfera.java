package herança_2;

public class Esfera extends FiguraGeometrica{
	private double raio;

	public Esfera(String n, double cx, double cy, double cz, double r){
		super(n, cx, cy, cz);
		setRaio(r);}

	@Override
	public double getArea() {
		return 4*3*(raio)*(raio);
	}

	@Override
	public double getVolume() {
		return 4/3*3*raio*raio*raio;
	}
	
	public double getRaio() {
		return raio;
	}
	
	public void setRaio(double raio) {
		this.raio = raio;
	}

}
