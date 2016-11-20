package thread_bola;

//import java.io.IOException;
//import java.util.concurrent.ExecutionException;
//import java.util.logging.Logger;

 /**
  * @author Apolo Marton
  * 
  */

public class Bola {

	/**
	 * Atributos do Objeto
	 */
	final static int PRESSAO_MAXIMA = 13;//psi
	final static int PRESSAO_MINIMA = 8;//psi
	private volatile int pressao = 0; //psi
	@SuppressWarnings("unused")
	private float massa =  200; //gramas
	private boolean furada = false;
	private boolean explodiu = false;
	private int vidaUtil = 80;
	
	
	/**
	 * Funcoes/metodos que alteram/utilizam os atributos do objeto
	 */
	public Bola(int pressao){
		System.out.println("nova bola criada");
		this.pressao = pressao; 
		vazaoNatural();
	}
	
	/**
	 * Verifica se a bola esta furada
	 */
	public boolean estaFurada(){
		return furada;
	}
	
	/**
	 * Verifica se a bola explodiu
	 * @return
	 */
	public boolean explodiu(){
		return explodiu;
	}
	
	/**
	 * Verifica pressão
	 * @return
	 */
	public int getPressao(){
		return pressao;
	}
	
	/**
	 * Aumenta a pressao da bola
	 * @return
	 */
	public int encher(){
		if(!explodiu){
			try {
				Thread.sleep(1000);
				pressao++;
				massa++;
			} catch (InterruptedException e) {}
			
			if(pressao>PRESSAO_MAXIMA){
				explodiu=true;
				pressao=0;
				vidaUtil=0;
				massa=200;
			}
		}
		return pressao;
	}
	
	/**
	 * Diminui a pressao da bola
	 * 
	 * @return
	 */
	public int esvaziar(){
		if(!explodiu){
			try {
				Thread.sleep(1000);
				if(pressao>0){
					pressao--;
					massa--;
				}
			} catch (InterruptedException e) {}
		}
		
		return pressao;
	}
	
	
	/**
	 * Fura a bola e começa a vazar
	 */
	public void furar(){
		System.out.println("a bola furou");
		furada = true;
		Thread thFurar = new Thread(new Runnable() {
			//Logica a ser executada
			public void run() {
				
				while(!explodiu){
					try {
						Thread.sleep(2000);
						if(pressao>0){
							pressao--;
							massa--;
						}
					} catch (InterruptedException e) {
						System.err.println("O furo rasgou.");
						explodiu = true;
					}
					
				}
				
			}
		});
		
		//Inicia a thread
		thFurar.start();
		System.out.println("A bola furou!");
	}
	
	/**
	 * Vazao naural da bola
	 */
	private void vazaoNatural(){
		Thread thVazao = new Thread(new Runnable() {
			//Logica a ser executada
			public void run() {
				
				while(!explodiu){
					try {
						Thread.sleep(10000);
						if(pressao>0){
							pressao--;
							massa--;
						}
					} catch (InterruptedException trecodeupau) {
						
				
					} catch (OutOfMemoryError qualquercoisa){
						
					}
				}
				
			}
		});
		
		//Inicia a thread
		thVazao.start();
	}

	public void chutar(){
		System.out.println("Chutou a bola:");
		if(vidaUtil>0){
			
			if(pressao<PRESSAO_MINIMA)
				vidaUtil-=5;
			else
				vidaUtil--;
			
			if(vidaUtil<70 && !furada)
				furar();
			
			if(vidaUtil<=0){
				explodiu = true;
				pressao = 0;
				massa=200;
			}
		}
	}
}