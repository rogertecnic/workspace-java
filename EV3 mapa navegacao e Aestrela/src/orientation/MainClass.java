//testada e funcionou

package orientation;
//pilots são classes que implementam moveController e controlam o robo como um todo

//não somente motor por motor, mas sao como a classe navegação
// motor nxt da garra da 3 giros

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class MainClass {
	static float diameter = 5.419f;
	static double offset = 7.518f; // dist entre o centro da roda e o eixo de
									// giro do robo, divide a dist entre as 2
									// rodas

	public static void main(String[] args) {
		// cria as rodas do robo
		Wheel rodaE = WheeledChassis.modelWheel(Motor.A, diameter).offset(-offset);
		Wheel rodaD = WheeledChassis.modelWheel(Motor.B, diameter).offset(offset);

		// cria o chassis do robo, no nosso caso o robo é diferencial "2 rodas
		// independentes"
		Chassis chassis = new WheeledChassis(new Wheel[] { rodaE, rodaD }, WheeledChassis.TYPE_DIFFERENTIAL);

		// cria o pilot para mover o robo por ai
		MovePilot pilot = new MovePilot(chassis);
		// cria um pose para verificar a posição do robo de acordo com o
		// tacometro dos motores
		// toda classse pose implementa a interface PosePRovider
		OdometryPoseProvider odometro = new OdometryPoseProvider(pilot);
		float[] samplePose = new float[odometro.sampleSize()];

		// odometro.fetchSample(samplePose, 0);
		// System.out.println("x:" + samplePose[0]);
		// Delay.msDelay(2000);

		pilot.setLinearAcceleration(5);
		pilot.setLinearSpeed(10);
		pilot.setAngularAcceleration(60);
		pilot.setAngularSpeed(105);
		// pilot.forward();
		// pilot.arc(0, 90);// o robo gira 90 graus para frente/esquerda sobre
		// seu eixo
		// pilot.arcBackward(10);// o robo gira para traz/esquerda num arco de
		// 10 cm durante 1 s
		Delay.msDelay(1000);
		// pilot.stop();
		odometro.fetchSample(samplePose, 0);
		System.out.println("x:" + samplePose[0]);
		System.out.println("y:" + samplePose[1]);
		Delay.msDelay(5000);

		// pilot.travel(100);
		for (int i = 0; i < 4; i++) {
			pilot.arc(0, -90);
			pilot.travel(21.8);
			pilot.arc(0, 90);
			pilot.travel(21.8);

			//Delay.msDelay(3000);
			odometro.fetchSample(samplePose, 0);
			System.out.println("x:" + samplePose[0]);
			System.out.println("y:" + samplePose[1]);
		}
		// pilot.arc(-25, 90);
		// pilot.arc(25, 90);
		// pilot.arc(25, 90);
		// pilot.arc(0, 360*4);
		// pilot.forward();
		Delay.msDelay(1000);
		odometro.fetchSample(samplePose, 0);
		System.out.println("x:" + samplePose[0]);
		System.out.println("y:" + samplePose[1]);
		Button.waitForAnyPress();
		// Delay.msDelay(5000);
		/**
		 * pilot.stop(); odometro.fetchSample(samplePose, 0);
		 * System.out.println("x:" + samplePose[0]); Delay.msDelay(5000);
		 * 
		 * 
		 * pilot.arc(0, 90); pilot.forward(); Delay.msDelay(1000); pilot.stop();
		 * odometro.fetchSample(samplePose, 0); System.out.println("x:" +
		 * samplePose[0]); Delay.msDelay(5000);
		 * 
		 * pilot.arc(0, 90); pilot.forward(); Delay.msDelay(1000); pilot.stop();
		 * odometro.fetchSample(samplePose, 0); System.out.println("x:" +
		 * samplePose[0]); Delay.msDelay(2000);
		 * 
		 * 
		 */
	}
}
