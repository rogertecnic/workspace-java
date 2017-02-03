package humanoid;
import javax.swing.JButton;

/**
 * @author klauder
 */
public class Arduino {
  private ControlePorta arduino;
  
  
  /**
   * Construtor da classe Arduino
   */
  public Arduino(){
	  
  }
  
  public void conectar(String porta,int taxa){
	  
      arduino = new ControlePorta(porta,taxa);//porta e taxa de transmiss�o
      
  }
  

  /**
   * Envia o comando para a porta serial
   * @param button - Bot�o que � clicado na interface Java
   */
  public void comunicacaoArduino(byte[] dados) {
	  
	  arduino.enviaDados(dados);
	  
  }
  
  public void close() {
	  
	  arduino.close();
	  
  }
  
}