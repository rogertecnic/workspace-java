package herança_array;

public class Testa {

	public static void main(String[] args){
		final int qntCirculos, qntQuadrados;
		
		qntCirculos = FigPlana.usuarioDigitaInt("digite quantos circulos deseja criar:");
		qntQuadrados = FigPlana.usuarioDigitaInt("digite quantos quadrados deseja criar:");
		
		//utilizando arrays de tamanho definido pelo usuario
		Circulo[] c = new Circulo[qntCirculos];
		for(int i =0 ; i < c.length; i++)
			c[i] = new Circulo("circulo " + i, FigPlana.usuarioDigitaInt("digite o raio do circulo " + i + ":"), 0, 0);
		
		Quadrado[] q = new Quadrado[qntQuadrados];
		for(int i =0 ; i < c.length; i++)
			q[i] = new Quadrado("quadrado " + i, FigPlana.usuarioDigitaInt("digite o lado do quadrado " + i + ":"), 0, 0);
	}

}
