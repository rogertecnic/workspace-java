import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Motor {
	private static EV3LargeRegulatedMotor mEsquerda = new EV3LargeRegulatedMotor(MotorPort.A);
	private static EV3LargeRegulatedMotor mDireita = new EV3LargeRegulatedMotor(MotorPort.B);
	public void main(String[] args){
		mEsquerda.forward();
		mDireita.forward();
		Delay.msDelay(2000);
	}
}
