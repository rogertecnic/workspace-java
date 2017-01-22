package motores;

import Seguidor_de_Linha.Navegacao;
import lejos.utility.Delay;

public class motor {	
	Navegacao navegar = new Navegacao(200,200);
	
	public void caixa(){
		navegar.tras();
		Delay.msDelay(300);
		navegar.gira90D();
		Delay.msDelay(1000);
		navegar.frente();
		Delay.msDelay(1000);
		navegar.gira90E();
		Delay.msDelay(1000);
		navegar.frente();
		Delay.msDelay(1000);
		navegar.gira90E();
	}
	public static void main(String[] args) {
		motor m = new motor();
		
		m.caixa();
	}
	
		
		
		
	

}
