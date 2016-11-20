package objeto_de_um_objeto;

public class Classe2 {
	Classe1 objeto1;
	public Classe2(int p)
	{
		objeto1 = new Classe1(p);
	}
	
	public void metodo2()
	{
		System.out.println("metodo da classe 2");
		objeto1.metodo1();
	}
}
