package controle_P;

public class ControleP {

	private double W = 0;
	private double Kp = 10;
	private double ki = 10;
	private double erro = 0;
	private double erroAnt = 0;
	
	public double execute(double thetaAlvo, double thetaAtual){
		
		thetaAlvo = Math.atan2(Math.sin(thetaAlvo), Math.cos(thetaAlvo));
		erro = thetaAlvo - thetaAtual;
		
		System.out.println("Erro: "+erro);
		
		//W = Kp * erro;
		
		W = Kp * erro + ki*(erro-erroAnt);
		
		erroAnt = erro;
		
		
		return W;
	}
}
