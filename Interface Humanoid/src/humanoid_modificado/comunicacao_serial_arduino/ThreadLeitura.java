package humanoid_modificado.comunicacao_serial_arduino;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;

public class ThreadLeitura implements Runnable{
	private ArduinoUsb arduino_ = null; // necessario para referenciar o proprio arduino dentro das threads
	private String nomeDaPortaCOM = null;
	private InputStream serialIn = null;
	private SerialPort serialPort = null;
	private int count = 0; // contador auxiliar, ajuda na hr de ler a strLength
	private int strLength = 0; // comprimento da string recebida ao final para validacao
	private int lendoString = 0; // indica os pacos da leitura de uma string
	private String strTemp = ""; // var auxiliar para leitura da string
	private ArrayList<String> strings = new ArrayList<String>(); // lista de strings lidas validas

	public ThreadLeitura(ArduinoUsb arduino_, SerialPort serialPort) throws NoSuchPortException, IOException{
		this.arduino_ = arduino_;
		this.serialPort = serialPort;
		nomeDaPortaCOM = CommPortIdentifier.getPortIdentifier(serialPort).getName();
		serialIn = serialPort.getInputStream();
		Thread thread = new Thread(this, "ThreadLeitura");
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void run() {
		
		while(arduino_.arduinoConectado ){
//				&& verificaConexao(nomeDaPortaCOM)){ este metodo atraza a execucao no pc do humanoid devido a um metodo da biblioteca
			try{
				System.out.println("classe ThreadLeitura linha 37");
				realizaLeitura();
			}
			catch(IOException e){
				System.out.println("Classe ThreadLeitura linha 40: problema na thread de leitura, encerrando thread");
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		synchronized(arduino_){
				serialPort.close();
				System.out.println("Classe ThreadLeitura linha 47: porta fechada na ThreadLeitura");
				arduino_.reset();
			arduino_.notify();
		}
		synchronized (strings) {
			strings.notify();
		}
	}

	/**
	 * realiza a sequencia de leitura de uma String
	 * @throws InterruptedException 
	 * @throws IOExceptions
	 */
	private void realizaLeitura() throws IOException, InterruptedException{
		if(serialIn.available()>0){
			char c = (char)serialIn.read();
			switch(lendoString){
			case 0: { // aguardando prineiro caractere de inicio e string '!'
				if(c == '!'){
					lendoString = 1;
				}
				break;
			} case 1: { // verifica se eh mesmo o inicio da string
				if(c == 'i'){
					lendoString = 2;
				}else lendoString = 5; //deu bosta no inicio
				break;
			} case 2: { // le a string ate o final
				if(c != '!')
					strTemp += c;
				else lendoString = 3;
				break;
			} case 3: { // verifica se eh mesmo o final
				if(c == 'e'){
					lendoString = 4;
				}else lendoString = 5; //deu bosta no final
				break;
			} case 4: { // le o tamanho da string para verificacao
				strLength = strLength*10 + Integer.parseInt(Character.toString(c));
				count ++;
				if(count ==2){
					verificaValidade(); // se tudo ocorrer bem a string eh adicionada a arraylist
				}
				break;
			} case 5: { // deu ruim, a comunicacao perdeu cincronia, pulando as strings ate cincronizar
				count = 0;
				strTemp = "";
				strLength = 0;
				lendoString = 0;
				break;
			}
			}
		} else{
			Thread.sleep(200);
		}
	}

	private void verificaValidade(){
		if(strTemp.equals("readOk")){
			synchronized (arduino_) {
				arduino_.notifyAll();
			}
		}else
			if(strTemp.equals("readFail"))
				synchronized (arduino_) {
					System.out.println("Classe ThreadLeitura linha 110: ATENCAO. arduino nao leu ultimo comando!");
					arduino_.notify();
				}
			else
				if(strLength == strTemp.length()){
					synchronized (strings) { // necessario para evitar leitura nequanto escreve
						strings.add(strTemp);
						strings.notify();
					}
				}else {
					System.out.println("Classe ThreadLeitura linha 117: houve uma quebra de string no meio!");
				}

		count = 0;
		strTemp = "";
		strLength = 0;
		lendoString = 0;
	}

	public String ler(){
		synchronized (strings) { // necessario para evitar leitura enquanto escreve
			try {
				strings.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(!strings.isEmpty()){
				String s = strings.get(0);
				strings.remove(0);
				return s;
			} else return "";
		}
	}



	/**
	 * metodo usado para verificar se o arduino ainda esta conectado no pc
	 * 
	 * @param nomeDaPorta
	 * @return true se estiver conectado ou false se nao encontrar a porta
	 */
	private boolean verificaConexao(String nomeDaPorta){
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> listaDePortas = CommPortIdentifier.getPortIdentifiers(); // este metodo deixa o codigo lento
		while(listaDePortas.hasMoreElements()){
			CommPortIdentifier	portID = listaDePortas.nextElement();
			if(nomeDaPorta.equals(portID.getName())){
				return true;
			}
		}
		System.out.println("Classe ThreadLeitura linha 159: porta " + nomeDaPorta +  " desconectada");
		return false;
	}
}
