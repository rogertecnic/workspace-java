// testada e funcionando

package navegation;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.MoveController;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.utility.Delay;

public class NavegationClass {
	static float diameter = 5.419f;
	static double offset = 8.241f; 
	public static void main(String[] args)
	{
		
		Wheel rodaE =  WheeledChassis.modelWheel(Motor.A, diameter).offset(- offset);//cria um Modeler da roda esquerda
		Wheel rodaD =  WheeledChassis.modelWheel(Motor.B, diameter).offset(offset);;//cria um Modeler da roda direita
		Wheel[] rodas = {rodaE,rodaD};// cria um vetor das rodas
		Chassis chassis = new WheeledChassis(rodas, WheeledChassis.TYPE_DIFFERENTIAL);// cria um chassis
		MoveController pilot = new MovePilot(chassis);// cria um pilot para robo andar
		OdometryPoseProvider odometro = new OdometryPoseProvider(pilot);
		Navigator navegacao = new Navigator(pilot, odometro); // cria um navegador para indicar Path's
		
		//adiciona 4 pontos ao path atual
		Waypoint aWaypoint = new Waypoint(20, 0);
		navegacao.addWaypoint(aWaypoint);
		aWaypoint = new Waypoint(20, 20);
		navegacao.addWaypoint(aWaypoint);
		aWaypoint = new Waypoint(0, 20);
		navegacao.addWaypoint(aWaypoint);
		aWaypoint = new Waypoint(0, 0);
		navegacao.addWaypoint(aWaypoint);
		
		//o robo inicia o caminho
		navegacao.followPath(); // metodo is non-blocking ele nao espera terminar o path
	}
}
