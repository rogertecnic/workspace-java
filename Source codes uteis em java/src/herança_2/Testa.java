package herança_2;

import javax.swing.JOptionPane;

public class Testa {
	public static void main(String[] args){
		FiguraGeometrica figura1;
		Esfera esfera1 = new Esfera("esfera1", 0, 0, 0, 10);
		Circulo circulo1 = new Circulo("circulo1", 0, 3, 5);
		figura1 = circulo1;


			
		System.out.println(circulo1.getArea());
		System.out.println(figura1.getArea());
		System.out.println(figura1.getArea());
		System.out.println(figura1.getArea());
		System.out.println(figura1.getArea());
	}
}
