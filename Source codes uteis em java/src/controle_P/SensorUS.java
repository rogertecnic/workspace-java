package controle_P;

public class SensorUS {
	
	private volatile double leitura = 0;

	public SensorUS(){
		
	}
	
	public void setON(){
		//ligar sensor;
	}
	public void setOFF(){
		//desligar sensor;
	}
	
	public double getLeitura(){
		return this.leitura;
	}
}
