package sensores;


import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;

public class SensorCorBoneco {
	private EV3ColorSensor sensorCor;
	private float[] rgbSample = new float[3];
	private float[] r = new float[3], // ordem preto, verde, vermelho, branco
			g = new float[3], // ordem vermelho, preto, verde, branco
			b = new float[3]; // ordem preto, vermelho, verde, branco

	public SensorCorBoneco(EV3ColorSensor sensorCor){
		this.sensorCor = sensorCor;
	}

	public void testaSensorBoneco(){
		while(true){
			float[] corSample = new float[3];
			sensorCor.getRGBMode().fetchSample(corSample, 0);
			System.out.printf("%.1f;%.1f;%.1f\n",corSample[0]*10, corSample[1]*10, corSample[2]*10);
		}
	}

	public void calibraCor(){
		float[] red = new float[4], blue = new float[4], green = new float[4];
		LCD.clear();
		LCD.drawString("CALIBRAGEM", 0, 0);
		LCD.drawString("Coloque o preto", 0, 1);
		LCD.drawString("aperte o botao central", 0, 2);
		Button.ENTER.waitForPressAndRelease();
		sensorCor.getRGBMode().fetchSample(rgbSample, 0);
		red[0] = rgbSample[0];
		green[0] = rgbSample[1];
		blue[0] = rgbSample[2];
		LCD.clear();
		LCD.drawString("CALIBRAGEM", 0, 0);
		LCD.drawString("Coloque o verde", 0, 1);
		LCD.drawString("aperte o botao central", 0, 2);
		Button.ENTER.waitForPressAndRelease();
		sensorCor.getRGBMode().fetchSample(rgbSample, 0);
		red[1] = rgbSample[0];
		green[1] = rgbSample[1];
		blue[1] = rgbSample[2];
		LCD.clear();
		LCD.drawString("CALIBRAGEM", 0, 0);
		LCD.drawString("Coloque o vermelho", 0, 1);
		LCD.drawString("aperte o botao central", 0, 2);
		Button.ENTER.waitForPressAndRelease();
		sensorCor.getRGBMode().fetchSample(rgbSample, 0);
		red[2] = rgbSample[0];
		green[2] = rgbSample[1];
		blue[2] = rgbSample[2];
		LCD.clear();
		LCD.drawString("CALIBRAGEM", 0, 0);
		LCD.drawString("Coloque o branco", 0, 1);
		LCD.drawString("aperte o botao central", 0, 2);
		Button.ENTER.waitForPressAndRelease();
		sensorCor.getRGBMode().fetchSample(rgbSample, 0);
		red[3] = rgbSample[0];
		green[3] = rgbSample[1];
		blue[3] = rgbSample[2];
		
		
		LCD.clear();
//		System.out.printf("%.3f;%.3f;%.3f;%.3f\n",red[0]*10, red[1]*10, red[2]*10, red[3]*10);
//		Button.ENTER.waitForPressAndRelease();
//		System.out.println("\n\n\n\n\n\n\n");
//		System.out.printf("%.3f;%.3f;%.3f;%.3f\n",green[0]*10, green[1]*10, green[2]*10, green[3]*10);
//		Button.ENTER.waitForPressAndRelease();
//		System.out.println("\n\n\n\n\n\n\n");
//		System.out.printf("%.3f;%.3f;%.3f;%.3f\n",blue[0]*10, blue[1]*10, blue[2]*10, blue[3]*10);
//		Button.ENTER.waitForPressAndRelease();
//		System.out.println("\n\n\n\n\n\n\n");
		
		float t = 0;
		// organizando os intervalos do red
		for (int i = 0; i <= 3; i++) {
			for (int j = 3; j > i; j--) {
				if (red[j] <= red[i]) {
					t = red[i];
					red[i] = red[j];
					red[j] = t;
				}
			}
		}

		t = 0;
		// organizando os intervalos do green
		for (int i = 0; i <= 3; i++) {
			for (int j = 3; j > i; j--) {
				if (green[j] <= green[i]) {
					t = green[i];
					green[i] = green[j];
					green[j] = t;
				}
			}
		}

		t = 0;
		// organizando os intervalos do blue
		for (int i = 0; i <= 3; i++) {
			for (int j = 3; j > i; j--) {
				if (blue[j] <= blue[i]) {
					t = blue[i];
					blue[i] = blue[j];
					blue[j] = t;
				}
			}
		}
		
		for(int i = 0; i<=2;i++)r[i] = (red[i] + red[i+1])/2; 
		for(int i = 0; i<=2;i++)g[i] = (green[i] + green[i+1])/2; 
		for(int i = 0; i<=2;i++)b[i] = (blue[i] + blue[i+1])/2; 
		
		
		//ranges do red
//		r[0] = (red[0] + red[1])/2; // menor  preto
//		r[1] = (red[1] + red[2])/2; // menor  verde
//		r[2] = (red[2] + red[3])/2; // menor  vermelho, maior branco
//
//		// ranges do green
//		g[0] = (green[2] + green[0])/2;// menor vermelho
//		g[1] = (green[0] + green[1])/2;// menor preto
//		g[2] = (green[1] + green[3])/2;// menor verde, maior branco
//
//		//ranges do blue
//		b[0] = (blue[0]+blue[2])/2;// menor preto
//		b[1] = (blue[2]+blue[1])/2;// menor vermelho
//		b[2] = (blue[1]+blue[3])/2;// menor verde, maior branco

		LCD.clear();
		//System.out.printf("%.3f;%.3f;%.3f\n",r[0]*1000, r[1]*1000, r[2]*1000);
		//System.out.printf("%.3f;%.3f;%.3f\n",g[0]*1000, g[1]*1000, g[2]*1000);
		//System.out.printf("%.3f;%.3f;%.3f\n",b[0]*1000, b[1]*1000, b[2]*1000);
		//Button.DOWN.waitForPressAndRelease();
	}

	public void verificaCorBoneco(){//TODO retornar o valo certo do metodo verificaCorBoneco
		while(true){
		sensorCor.getRGBMode().fetchSample(rgbSample, 0);
		int preto = 0, verde = 0, vermelho = 0, branco = 0;
		
//		if(rgbSample[0] < r[0]) preto ++;// verifica os ranges vermelhos
//		else if(rgbSample[0] < r[1]) verde ++;
//		else if(rgbSample[0] < r[2]) vermelho ++;
//		else branco ++;
//		
//		if(rgbSample[1] < g[0]) vermelho ++;// verifica os ranges verdes
//		else if(rgbSample[1] < g[1]) preto ++;
//		else if(rgbSample[1] < g[2]) verde ++;
//		else branco ++;
//		
//		if(rgbSample[0] < r[0]) preto ++;// verifica os ranges azuis
//		else if(rgbSample[0] < r[1]) vermelho ++;
//		else if(rgbSample[0] < r[2]) verde ++;
//		else branco ++;
		
		if(rgbSample[0] < r[1] && rgbSample[1] < g[1] && rgbSample[2] < b[1])
			System.out.println("preto");
		else if(rgbSample[0] > r[1] && rgbSample[1] < g[1] && rgbSample[2] < b[2])// && rgbSample[1] < g[1] && rgbSample[2] < b[1])
			System.out.println("vermelho");
		else if(rgbSample[0] < r[2] && rgbSample[1] > g[1] && rgbSample[2] < b[2])// && rgbSample[1] > g[1] && rgbSample[2] < b[1])
			System.out.println("verde");
		else if(rgbSample[0] > r[1] && rgbSample[1] > g[1] && rgbSample[2] > b[1])
			System.out.println("branco");
		else System.out.println("deu ruim");
		
		
		
//		if(preto >=2) System.out.println(preto);
//		else if(verde >=2) System.out.println(verde);
//		else if(vermelho >=2) System.out.println(vermelho);
//		else if(branco >=2) System.out.println(branco );
//		else System.out.println("tomate cru");
//		
//		System.out.println(preto);
//		System.out.println(verde);
//		System.out.println(vermelho);
//		System.out.println(branco );
		
		Button.ENTER.waitForPressAndRelease();
		}
	}

}
