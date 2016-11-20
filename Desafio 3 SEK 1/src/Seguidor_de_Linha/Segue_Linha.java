package Seguidor_de_Linha;


import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Segue_Linha {
	private static EV3ColorSensor sensorLeft = new EV3ColorSensor(SensorPort.S1);
	private static EV3ColorSensor sensorRight = new EV3ColorSensor(SensorPort.S2);
	private static EV3ColorSensor frontSensor = new EV3ColorSensor(SensorPort.S4);
	private static float corE;
	private static float corD;
	private static float corF;
	private static float cor = Color.BLACK;
	private Navegacao navegar = new Navegacao(500, 500);
	private Ultrassonic distance = new Ultrassonic();
	
	public float getCorE(){
		return corE;
	}
	
	public float getCorD(){
		return corD;
	}
	public float getCor(){
		return cor;
	}
	
	public void segueLinha(){
		//Laço para que o robo siga linha até a caixa de leite
		
		amostraCor();
		System.out.println("Distancia sensor "+distance.getDistancia());
		if(corE != cor && corD != cor ){ //Andar pra frente
			navegar.velocidade(200,200);
			navegar.frente();
		}
		else if(corE == cor && corD != cor){
			navegar.velocidade(150,150);
			navegar.esquerda();
		}
		else if(corE != cor && corD == cor){
			navegar.velocidade(150,150);
			navegar.direita();
		}
	}
	
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
	
	public void boneco() {
		navegar.FrenteCaixa();
		while (corF != cor) {
			if (distance.getDistancia() < 0.7) {
				while (distance.getDistancia() > 0.06) {
					navegar.frente();
				}
				amostraCor();
				if (corF == cor) {
					Sound.twoBeeps();
				} 
				else{
					while (distance.getDistancia() < 0.1) {
						navegar.tras();
						navegar.GiraCampo();
					}
				}
			} 
			else
				navegar.GiraCampo();			
		}
	}
	
		private static void amostraCor() {
			SampleProvider corEsquerda = sensorLeft.getColorIDMode();
			SampleProvider corDireita = sensorRight.getColorIDMode();
			SampleProvider corFrente = frontSensor.getColorIDMode(); //////
			float amostraCorE[] = new float[sensorLeft.sampleSize()];
			float amostraCorD[] = new float[sensorRight.sampleSize()];
			float amostraCorF[] = new float[frontSensor.sampleSize()];
			corEsquerda.fetchSample(amostraCorE, 0);
			corDireita.fetchSample(amostraCorD, 0);
			corFrente.fetchSample(amostraCorF, 0);
			corE = amostraCorE[0];
			corD = amostraCorD[0];
			corF = amostraCorF[0];
		}
}
