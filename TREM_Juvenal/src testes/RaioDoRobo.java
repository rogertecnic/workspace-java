import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * Differential robots: calcular a largura do robo mais precisamente, deve ser feito
 * apos saber exatamente o raio da roda, pelo outro teste.
 * a formula utilizada é: Rr = Tw*Rw/Tr onde:
 * Tw: angulo que a roda girou em radianos;
 * Rw: raio da roda;
 * Tr: angulo que o robo girou sobre seu proprio eixo em radianos;
 * Rr: raio do robo, que vai ser a largura dividio por 2;
 * para fazer este teste o procedimento é o seguinte:
 * 
 * riscar uma linha no chao que seja maior que a largura do robo, colocar o robo
 * de forma que as duas rodas fiquem exatamente encima da linha, marcar na linha rente a
 * lateral de cada roda, tirar o robo da linha e encontrar o centro entre as marcacoes, depois
 * colocar o robo de volta na mesma posicao, essa marcacao sera mais ou menos o centro de giro
 * do robo. Depois e so rodar o codigo, o robo vai girar no sentido anti-horario, quando ele parar
 * marque no chao onde esta as duas rodas, tire o robo e trace uma linha reta ligando as duas
 * marcacoes, depois so medir o quanto o robo girou medindo a inclinacao entre a linha que ele iniciou
 * e a linha que ele parou, nao esquecendo de somar as voltas completas;
 * 
 * Para calcular o raio do robo "Rr" é so usar a formula acima, com o angulo total girado pelas
 * rodas e o angulo total girado pelo robo;
 * @author Rogerio
 *
 */
public class RaioDoRobo {
	public static void main(String[] args){
		EV3LargeRegulatedMotor rodaE;
		EV3LargeRegulatedMotor rodaD;
		rodaE = new EV3LargeRegulatedMotor(MotorPort.A);
		rodaD = new EV3LargeRegulatedMotor(MotorPort.B);
		
		rodaE.setAcceleration(200);
		rodaD.setAcceleration(200);
		rodaE.setSpeed(300);
		rodaD.setSpeed(300);
		
		int girosDaRoda = 7; // raio do robo 6,6655cm
		
		rodaE.rotate(-360*girosDaRoda, true);
		rodaD.rotate(360*girosDaRoda);
	}
}
