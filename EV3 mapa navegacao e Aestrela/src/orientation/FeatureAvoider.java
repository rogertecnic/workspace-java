package orientation;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.RangeFinder;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;
//classes que detectam obstaculos sao classes feture detector e implementam a interface Feature
//unica classe que implementa a Feature é a RangeFeature

public class FeatureAvoider {
	static boolean acumuloErro = false;
	static final float MAX_DISTANCE = 0.3f;
	static final int DETECTOR_DELAY = 250;
	static float diameter = 5.419f;
	static double offset = 7.518f; // dist entre o centro da roda e o eixo de
									// giro
									// do robo, divide a dist entre as 2 rodas
	private static NXTUltrasonicSensor ir;
	// private static EV3UltrasonicSensor ir;

	public static void main(String[] args) {
		Wheel rodaE = WheeledChassis.modelWheel(Motor.A, diameter).offset(-offset);
		Wheel rodaD = WheeledChassis.modelWheel(Motor.B, diameter).offset(offset);
		// cria o chassis do robo, no nosso caso o robo é diferencial "2 rodas
		// independentes"
		Chassis meuChassis = new WheeledChassis(new Wheel[] { rodaE, rodaD }, WheeledChassis.TYPE_DIFFERENTIAL);
		// cria o pilot para mover o robo por ai
		final MovePilot meuMovePilot = new MovePilot(meuChassis);

		ir = new NXTUltrasonicSensor(SensorPort.S1);
		// ir = new EV3UltrasonicSensor(SensorPort.S1);
		// RangeFinder para o RangeFeatureDetector
		final RangeFinder meuRangeFinder = new RangeFinderAdapter(ir.getDistanceMode());

		// RangeFeatureDetector, extend FeatureDetectorAdapter
		// cria uma thread onde essa thread verifica se tem ou não um obstaculo
		// a cada tempo
		// se o enableDetection for true
		// quando o objeto for detectado o metodo scan() vai retornar um Feature
		// dentro da thread, a thread chama o metodo notifyListeners e passa
		// esse Feature
		// para os listeners que eu adicionei com o addListener
		// chamando o metodo featureDetected de cada listener que foi criado
		// executando assim o codigo que está dentro do featureDetected que
		// criamos abaixo
		RangeFeatureDetector meuRangeFeatureDetector = new RangeFeatureDetector(meuRangeFinder, MAX_DISTANCE,
				DETECTOR_DELAY);

		FeatureListener meuFeatureListener = new FeatureListener() {
			@Override
			public void featureDetected(Feature feature, FeatureDetector detector) {
				System.out.println(feature.getRangeReading().getRange());
				detector.enableDetection(false);
				meuMovePilot.stop();
				meuMovePilot.travel(-10);
				if (acumuloErro) {
					meuMovePilot.rotate(180);
					acumuloErro = false;
				}
				else{
					meuMovePilot.rotate(-180);
					acumuloErro = true;
				}
				detector.enableDetection(true);
				meuMovePilot.forward();
			}
		};

		meuRangeFeatureDetector.addListener(meuFeatureListener);
		meuRangeFeatureDetector.enableDetection(true);

		meuMovePilot.setLinearSpeed(10);
		meuMovePilot.setAngularSpeed(90);
		meuMovePilot.setLinearAcceleration(5);
		meuMovePilot.setAngularAcceleration(45);
		meuMovePilot.forward();

		while (Button.ESCAPE.isUp())
			Thread.yield();
	}
}