package objeto_de_um_objeto;

public class Main {

	public static void main(String[] args) {
		Classe2 objeto2 = new Classe2(5);
		Classe2 objeto22 = new Classe2(17);
		objeto2.metodo2();
		objeto22.metodo2();
		int a = (6&7);
		String b = Integer.toBinaryString(a);
		System.out.println(b);

	}

}
