package herança_2;

public class Circulo extends Esfera {

	public Circulo(String n, double cx, double cy, double r) {
		super(n, cx, cy,0, r);}
	
	@Override
	public double getArea(){
		return this.getRaio()*this.getRaio()*3;
	}
	
	public double getPerimetro(){
		return 2*3*this.getRaio();
	}
	
}
