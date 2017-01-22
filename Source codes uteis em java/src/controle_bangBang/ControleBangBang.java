package controle_bangBang;

public class ControleBangBang {
	
	private double W = 0;
	private double WPadrao = 1;
	private double erro = 0;
	
	public double execute(double thetaAlvo, double thetaAtual){
		
		thetaAlvo = Math.atan2(Math.sin(thetaAlvo), Math.cos(thetaAlvo));
		erro = thetaAlvo - thetaAtual;
		System.out.println("Erro: "+erro);
		if(erro > 0){
			W = WPadrao;
		} else if(erro < 0){
			W = -WPadrao;
		} else {
			W = 0;
		}
		
		return W;
	}

	
}
