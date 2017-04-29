import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * Differential robots: serve para calcular o raio da roda mais precisamente
 * faz uma marcacao onde a roda esta em contato com o chao e executa este codigo
 * depois marca onde o robo parar, mede a distancia, divide 2PI vezes a quantidade de giros
 * pronto, vc tem o raio de uma forma mais precisa do que medir com o paquimetro.
 * Para uma precisao maior, alterar a quantidade de giros, se a velocidade e/ou a aceleracao
 * estiver muito alta, pode alterar
 * @author Rogerio
 *
 */
public class RaioDaRoda {
	public static void main(String[] args){
		EV3LargeRegulatedMotor rodaE;
		EV3LargeRegulatedMotor rodaD;
		rodaE = new EV3LargeRegulatedMotor(MotorPort.A);
		rodaD = new EV3LargeRegulatedMotor(MotorPort.B);
		
		rodaE.setAcceleration(200); // em graus/sec^2
		rodaD.setAcceleration(200);
		rodaE.setSpeed(300);// em graus/sec
		rodaD.setSpeed(300);
		
		int giro = 4;
		rodaE.rotate(360*giro);
		rodaD.rotate(360*giro);
	}
}
