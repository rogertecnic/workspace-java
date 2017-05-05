package sensores;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;

public class SensorCorChao {
	private EV3ColorSensor sensorCor;

	private float[] rgbSample = new float[3];
	private float r, // divisor do range vermelho
	g, // divisor do range verde
	b;  // divisor do range azul

	public SensorCorChao(EV3ColorSensor sensorCor){
		this.sensorCor = sensorCor;
	}

	public void testaSensorChao(){
		while(true){
			sensorCor.getRGBMode().fetchSample(rgbSample, 0);
			System.out.printf("%.1f;%.1f;%.1f\n",rgbSample[0]*10, rgbSample[1]*10, rgbSample[2]*10);
		}
	}

	public void calibraCor(){
		float[] red = new float[3], blue = new float[3], green = new float[3];
		LCD.clear();
		LCD.drawString("CALIBRAGEM CHAO", 0, 0);
		LCD.drawString("Coloque no verde", 0, 1);
		LCD.drawString("aperte o botao central", 0, 2);
		Button.ENTER.waitForPressAndRelease();
		sensorCor.getRGBMode().fetchSample(rgbSample, 0);
		red[0] = rgbSample[0];
		green[0] = rgbSample[1];
		blue[0] = rgbSample[2];
		LCD.clear();
		LCD.drawString("CALIBRAGEM CHAO", 0, 0);
		LCD.drawString("Coloque no vermelho", 0, 1);
		LCD.drawString("aperte o botao central", 0, 2);
		Button.ENTER.waitForPressAndRelease();
		sensorCor.getRGBMode().fetchSample(rgbSample, 0);
		red[1] = rgbSample[0];
		green[1] = rgbSample[1];
		blue[1] = rgbSample[2];
		LCD.clear();
		LCD.drawString("CALIBRAGEM CHAO", 0, 0);
		LCD.drawString("Coloque no branco", 0, 1);
		LCD.drawString("aperte o botao central", 0, 2);
		Button.ENTER.waitForPressAndRelease();
		sensorCor.getRGBMode().fetchSample(rgbSample, 0);
		red[2] = rgbSample[0];
		green[2] = rgbSample[1];
		blue[2] = rgbSample[2];

		LCD.clear();

		float t = 0;
		// organizando os intervalos do red
		for (int i = 0; i <= 2; i++) {
			for (int j = 2; j > i; j--) {
				if (red[j] <= red[i]) {
					t = red[i];
					red[i] = red[j];
					red[j] = t;
				}
			}
		}

		t = 0;
		// organizando os intervalos do green
		for (int i = 0; i <= 2; i++) {
			for (int j = 2; j > i; j--) {
				if (green[j] <= green[i]) {
					t = green[i];
					green[i] = green[j];
					green[j] = t;
				}
			}
		}

		t = 0;
		// organizando os intervalos do blue
		for (int i = 0; i <= 2; i++) {
			for (int j = 2; j > i; j--) {
				if (blue[j] <= blue[i]) {
					t = blue[i];
					blue[i] = blue[j];
					blue[j] = t;
				}
			}
		}

		r = (red[1] + red[2])/2; 
		g = (green[1] + green[2])/2; 
		b = (blue[1] + blue[2])/2; 
	}

	public void getModuloDetectado(){
		while(true){
			sensorCor.getRGBMode().fetchSample(rgbSample, 0);
			if(rgbSample[0] > r && rgbSample[1] > g && rgbSample[2] >b){
				System.out.println(false);
			} else System.out.println(true);
		}
	}
}
