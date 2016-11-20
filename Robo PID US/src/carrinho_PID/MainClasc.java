package carrinho_PID;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.MotorRegulator;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class MainClasc {
	// parametros para o piloto do robo
	static final float diameter = 5.419f;
	static final double offset = 7.518f; // distancia do centro da roda ao
											// centro do robo

	// parametro para o robo nao dar cavalo de pau e escorregar a roda
	
	
	//
	//
	// -----------------------------------------------------CRIANDO PILOTO
	static EV3LargeRegulatedMotor motorE = new EV3LargeRegulatedMotor(MotorPort.A);
	static EV3LargeRegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.B);
	
	// -----------------------------------------------------PILOTO CRIADO
	//
	//
	
	//
	//
	//-----------------------------------------------------SENSOR US
	static NXTUltrasonicSensor sensorUS = new NXTUltrasonicSensor(SensorPort.S1);
	static SampleProvider sampleUS = sensorUS.getDistanceMode();
	/**
	 * le o sensor ultrassom
	 * @return distancia em metros do obj
	 */
	static float getSensorUS(){
		float[] array = new float[1];
		sampleUS.fetchSample(array, 0);
		return array[0]*100;}
	//-----------------------------------------------------SENSOR US
	//
	//
	
	public static void main(String[] args) {
		float PID = 0,
				e = 0,
				eAnt = 0,
				P = 0,
				I = 0,
				D = 0,
				Kp = 1f,
				Ki = 0.01f,
				Kd = 0.8f,
				velo = 0,
				veloAnt = 0,
				distEsperada = 25f,
				distReal = 0f;
		boolean frente = false;
		
		while(Button.ESCAPE.isUp()){
			distReal = getSensorUS();
			System.out.println(distReal + "    " + velo);
			if(distReal < 80)
			e = distReal - distEsperada;
			P = Kp*e;
			I += e*Ki;
			D = (eAnt-e)*Kd;
			eAnt = e;
			PID = P+I+D;
			velo =  PID;
			
			Delay.msDelay(10);
			
			
			if(velo == 0){
			motorE.stop(true);
			motorD.stop();
			}
			else if(velo > 0){
				motorE.setSpeed((int)(3.1415*diameter * velo*3));
				motorD.setSpeed((int)(3.1415*diameter * velo*3));
			//	if(!frente){
				motorE.forward();
				motorD.forward();
				frente = true;
			//	}
			} else if (velo < 0){
				motorE.setSpeed((int)(3.1415*diameter * (-velo*3)));
				motorD.setSpeed((int)(3.1415*diameter * (-velo*3)));
			//	if(frente){
					motorE.backward();
					motorD.backward();
					frente = false;
			//		}
			}
		}

	}

}

