package objetos;

public class Robo {

	public final static double RAIO = 0.025; //metros
	public final static double DISTANCIA_ENTRE_RODAS = 0.10; //metros
	public final static double PI = 3.141593;
	private double[] posXY = {0, 0}; //(X, Y)
	/**
	 * angulo absoluto do robo
	 */
	private double angulo = 0;
	
	/**
	 * Tacho anterior direito
	 */
	private double tachoAntDir = 0;
	/**
	 * tacho anterior esquerdo
	 */
	private double tachoAntEsq = 0;
	
	public Motor motorDireita;
	public Motor motorEsquerda;
	
	private SensorUS sensorUS;
	
	public Robo(){
		initComponents();
	}
	
	private void initComponents(){
		motorDireita = new Motor();
		motorEsquerda = new Motor();
		sensorUS = new SensorUS();
		
		motorDireita.resetTachoCount();
		motorEsquerda.resetTachoCount();
	}
	
	public void updateState(){
		double tachoDir = motorDireita.getTachoCount();
		double tachoEsq = motorEsquerda.getTachoCount();
		
		double deltaSDir = 2*PI*RAIO*(tachoDir - tachoAntDir)/360;
		double deltaSEsq = 2*PI*RAIO*(tachoEsq - tachoAntEsq)/360;
		
		this.tachoAntDir = tachoDir;
		this.tachoAntEsq = tachoEsq;
		
		angulo = angulo + ((deltaSDir - deltaSEsq)/DISTANCIA_ENTRE_RODAS);
		angulo = Math.atan2(Math.sin(angulo), Math.cos(angulo));
		
		posXY[0] = posXY[0] + ((deltaSDir + deltaSEsq)*Math.cos(angulo)/2);
		posXY[1] = posXY[1] + ((deltaSDir + deltaSEsq)*Math.sin(angulo)/2);
	}
	public double[] getState(){
		return new double[] {this.posXY[0], this.posXY[1], this.angulo};
	}
}
