package controle_P;

public class Motor {
	
	private volatile int velocidade = 0; //�ps
	private double tachoCount = 0;

	public Motor(){
		
	}
	
	public void setVelocidade(int velocidade){
		this.velocidade = velocidade;
	}
	
	public double getTachoCount(){
		return this.tachoCount;
	}
	public void resetTachoCount(){
		this.tachoCount = 0;
	}
	
	public void updateTachoTime(double time){
		tachoCount = (tachoCount + time *  velocidade);
	}
}
