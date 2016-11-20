package thread_bola;
/**
  * @author Apolo Marton
  * 
  */

public class Treinamento {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Bola bola;
		bola = new Bola(0);
		
		while(true){
			System.out.println("Pressao da bola nova:" + bola.getPressao());
			
			//Verifica se esvaziou
			if(bola.getPressao()<Bola.PRESSAO_MINIMA){
				System.out.println("Enchendo a bola");
				while(bola.getPressao()<Bola.PRESSAO_MAXIMA){
					bola.encher();
					System.out.println("Pressao da bola:" + bola.getPressao());
				}
			}
			
			//Chuta a bola
			bola.chutar();
			
			//Pega uma nova bola
			if(bola.explodiu()){
				System.out.println("A bola explodiu!");
				System.out.println("Nova bola foi comprada");
				try{
					Thread.sleep(3000);
				}catch(Exception e){}
				
				bola = new Bola(0);
			}
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			
			}
		}
	}
}
