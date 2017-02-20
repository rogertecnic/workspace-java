package humanoid_original;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import javax.swing.JOptionPane;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class ControlePorta implements Runnable, SerialPortEventListener{
  private OutputStream serialOut;
  private InputStream serialIn;
  private int taxa;
  private String portaCOM;
  private boolean connect = false;
  
  SerialPort port;
  Thread readThread;
  
  static final  int BUFFER_SIZE = 18;
  private int size = 0;
  private char[] buffer = new char[BUFFER_SIZE];
  private StringBuffer sb = new StringBuffer();
  
  /**
   * Construtor da classe ControlePorta
   * @param portaCOM - Porta COM que será utilizada para enviar os dados para o arduino
   * @param taxa - Taxa de transferência da porta serial geralmente é 9600
   */
  public ControlePorta(String portaCOM, int taxa) {
    this.portaCOM = portaCOM;
    this.taxa = taxa;
    this.initialize();
  }     
 
  /**
   * Médoto que verifica se a comunicação com a porta serial está ok
   */
  private void initialize() {
    try {
      //Define uma variável portId do tipo CommPortIdentifier para realizar a comunicação serial
      CommPortIdentifier portId = null;
      try {
        //Tenta verificar se a porta COM informada existe
        portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);
      }catch (NoSuchPortException npe) {
        //Caso a porta COM não exista será exibido um erro 
        JOptionPane.showMessageDialog(null, "Porta COM não encontrada.",
                  "Porta COM", JOptionPane.PLAIN_MESSAGE);
      }
      //Abre a porta COM 
       port = (SerialPort) portId.open("Comunicação serial", this.taxa);
      serialOut = port.getOutputStream();
      port.setSerialPortParams(this.taxa, //taxa de transferência da porta serial 
                               SerialPort.DATABITS_8, //taxa de 10 bits 8 (envio)
                               SerialPort.STOPBITS_1, //taxa de 10 bits 1 (recebimento)
                               SerialPort.PARITY_NONE); //receber e enviar dados
    iniciaLeitura();
      
    return;
    }catch (Exception e) {
      e.printStackTrace();
    }
}

  /**
   * Método que fecha a comunicação com a porta serial
   */
  public void close() {
    port.close();
  }

  /**
   * @param opcao - Valor a ser enviado pela porta serial
   */
  public void enviaDados(byte[] opcao){
    try {
      serialOut.write(opcao);//escreve o valor na porta serial para ser enviado
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Não foi possível enviar o dado. ",
                "Enviar dados", JOptionPane.PLAIN_MESSAGE);
    }
  }
  
  public void iniciaLeitura() throws TooManyListenersException{
	  
	    try {
	      serialIn = port.getInputStream();
	      port.addEventListener(this);
	      port.notifyOnDataAvailable(true);
	    } catch (IOException e) {
	    }
	    
	    readThread = new Thread(this);
	    readThread.start();
	}
  
  
  public void serialEvent(SerialPortEvent event) {
	    switch (event.getEventType()) {
	    case SerialPortEvent.BI:
	    case SerialPortEvent.OE:
	    case SerialPortEvent.FE:
	    case SerialPortEvent.PE:
	    case SerialPortEvent.CD:
	    case SerialPortEvent.CTS:
	    case SerialPortEvent.DSR:
	    case SerialPortEvent.RI:
	    case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
	      break;
	    case SerialPortEvent.DATA_AVAILABLE:
	      byte[] readBuffer = new byte[20];

	      try {
	        while (serialIn.available() > 0) {	 
	        	int numBytes = serialIn.read(readBuffer);
	        }
	        
	        sb.append((new String(readBuffer)).trim());
	        Flags.LOG.append("R: "+(new String(readBuffer))+"\n");
	        
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	      break;
	    }
	  }

  
  public void processString(){
	  
	  char[] c = sb.toString().toCharArray();

	  if(c.length <BUFFER_SIZE)
		  return;
	  
	 System.out.println("[S1: "+sb.toString().trim()+"]"+"L:"+"["+sb.length()+"]");  
	 	  
	  
	  for(int i=0;i < BUFFER_SIZE;i++){
		  buffer[i] = c[i];
	  }
	  
	  
	  Protocolo protocolo = new Protocolo();
	  if(protocolo.verificarMsg(buffer)){
		 // Flags.LOG.append("Aqui!!");
      }
	  
	  sb = new StringBuffer(); 

	  for(int i =BUFFER_SIZE;i<c.length;i++){
		  	sb.append(c[i]+"");
	 }
	 //System.out.println("SB2:"+sb.toString().trim()+"]"+"L:"+"["+sb.length()+"] ");
	  
  }
  
  
@Override
public void run() {
	// TODO Auto-generated method stub
	while(true){
	processString();
	
	
		try {
			Thread.sleep(1000);
	    } catch (InterruptedException e) {
	    }
	}
	
}
	
  

}