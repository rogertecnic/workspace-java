package testandoCoisas;

public class Lampada {
	String estadoDaLampada = "apagada";
	
	public void acende(){
		estadoDaLampada = "acesa";
	}
	
	public void apaga(){
		estadoDaLampada = "apagada";
	}
	
	public void mostraEstado(){
		if(estadoDaLampada == "acesa") System.out.println("a lampada esta acesa");
		else System.out.println("a lampada esta apagada");
	}
}
