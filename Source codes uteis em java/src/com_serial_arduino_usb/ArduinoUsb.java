package com_serial_arduino_usb;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

/**
 * Classe que controla a comunicacao serial com o arduino, ela gerencia a thread de envio de dados
 * e a thread de threadLeitura de dados.
 * IMPORTANTE: leia a descricao de como utiliza-la no comentario do construtor
 * @author Rogerio
 *
 */
public class ArduinoUsb implements Runnable {
	private ArduinoUsb arduino_ = null; // necessario para referenciar o proprio arduino dentro das threads
	protected  boolean arduinoConectado = false;
	private ThreadLeitura threadLeitura = null;
	private ThreadEscrita threadEscrita = null;
	private JButton botaoConectar = null;

	/**
	 * ATENCAO: classe feita para ser gerenciada por um objeto JButton; 
	 * Instanciar o jbutton e logo depois instanciar a ArduinoUsb passando para o metodo
	 * construtor como argumento o jbutton instanciado na linha logo acima,
	 *  nao eh necessario criar um listenner para o jbutton, esta classe ja faz isso,
	 *  nao eh necessario criar um local para digitar o endereco da porta, essa classe ja faz isso
	 * @param botaoConectar botao do qual deveva se conectar ao arduino
	 */
	public ArduinoUsb(JButton botaoConectar){
		arduino_ = this;
		this.botaoConectar = botaoConectar;
		this.botaoConectar.setText("Desconectado");
		this.botaoConectar.setBackground(Color.RED);
		this.botaoConectar.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {


			}

			@Override
			public void mousePressed(MouseEvent e) {


			}

			@Override
			public void mouseExited(MouseEvent e) {


			}

			@Override
			public void mouseEntered(MouseEvent e) {


			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(!arduinoConectado){
					String nomeDaPorta = JOptionPane.showInputDialog("digite o nome da porta:", null);
					if(nomeDaPorta !=null){
						conectarArduino(nomeDaPorta);
					} else System.out.println("Classe ArduinoUsb linha 79: conexao cancelada!");
				} else System.out.println( "Classe ArduinoUsb linha 80: Arduino ja conectado!");

			}
		});	
	}

	private void conectarArduino(String nomeDaPorta){
		CommPortIdentifier portID = verificaSePortaExiste(nomeDaPorta);
		if(portID != null){
			SerialPort portSerial = verificaSePortaAberta(portID);
			if(portSerial != null){
				iniciaComunicacao(portSerial);
			}
		}
		
		if(!arduinoConectado) reset();

	}

	/**
	 * Verifica se a porta pedida existe neste pc e retorna seu id, este metodo nao verifica se ela esta
	 * livre ou ocupada.
	 * @param nomeDaPorta porta a ser verificada
	 * @return CommPortIdentifier da porta se a porta existir e null se nao existir
	 */
	private CommPortIdentifier verificaSePortaExiste(String nomeDaPorta){
		CommPortIdentifier portID = null;
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> listaDePortas = CommPortIdentifier.getPortIdentifiers();
		while(listaDePortas.hasMoreElements()){
			portID = listaDePortas.nextElement();
			if(nomeDaPorta.equals(portID.getName())){
				System.out.println("Classe ArduinoUsb linha 112: porta " + nomeDaPorta +  " existente, id da porta retornado");
				return portID;
			}
		}
		System.out.println("Classe ArduinoUsb linha 8116: porta " + nomeDaPorta +  " NAO Existe: nome errado ou arduino deve estar desconectado da USB.");
		return null;
	}

	/**
	 * Verifica se a porta esta aberta e prota para conexao, se sim ocorre a conexao e a porta
	 * eh retornada, se nao retorna null;
	 * Este metodo somente deve ser chamado com uma portID valida, retornada pelo menoto verifcaSePortaExiste();
	 * @param portID CommPortIdentifier para verificar se a porta esta aberta,
	 * @return SerialPort conectada ou null
	 */
	private SerialPort verificaSePortaAberta(CommPortIdentifier portID){
		try {
			SerialPort serialPort = (SerialPort) portID.open("owner da porta: Software humanoid", 2000);
			System.out.println("Classe ArduinoUsb linha 130: porta aberta e retornada");
			return serialPort;
		} catch (PortInUseException e) {
			System.out.println("Classe ArduinoUsb linha 133: falha ao tentar abrir a porta, deve estar sendo usada por outro aplicativo");
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * Metodo interno, NAO UTILIZAR
	 */
	public void run() {
		
	}


	/**
	 * Inicia as Streams I/O e as threads de comunicacao;
	 * Deve-se utilizar os metodos verificaSePortaExiste() e verificaSePortaAberta()para garantir que
	 * a porta passada como argumento esta devidamente aberta e preparada
	 * @param serialPort porta serial ja aberta retornada pelo metodo verificaSePortaAberta()
	 */
	private void iniciaComunicacao(SerialPort serialPort){
		try {
			arduinoConectado = true;
			threadLeitura = new ThreadLeitura(arduino_, serialPort);
			threadEscrita = new ThreadEscrita(arduino_, serialPort);
			botaoConectar.setText("Conectado!");
			botaoConectar.setBackground(Color.GREEN);
		}
		catch (IOException e) {
			System.out.println("Classe ArduinoUsb linha 163: erro ao criar as streams de entrada e saida");
			e.printStackTrace();
		} catch (NoSuchPortException e) {
			System.out.println("Classe ArduinoUsb linha 166: erro ao criar as streams de entrada e saida");
			e.printStackTrace();
		}
	}

	/**
	 * Utilizar este metodo para enviar uma String para o arduino
	 * @param strOut String a ser enviada para o arduino
	 * IMPORTANTE: ela nao eh enviada no momento de execucao deste metodo,
	 * ela eh salva e enviada assim que possivel (em casos normais quase instantaneamente)
	 */
	public void enviar(String strOut){
		if(threadEscrita != null){
			synchronized (arduino_) {
				threadEscrita.strOut = strOut;
				arduino_.notify();
			}
		}
	}

	/**
	 * Metodo que retorna a ultima informacao lida da comunicacao com o arduino
	 * @return ultima informacao lida do arduino
	 * @throws InterruptedException 
	 */
	public String ler() throws InterruptedException{
		if(arduinoConectado){
			return threadLeitura.ler();
		}
		else{ 
			Thread.sleep(10);
			return "";
		}
	}



	public boolean estadoComunicacao(){
		return arduinoConectado;
	}


	protected void reset(){
		arduinoConectado = false;
		threadLeitura = null;
		threadEscrita = null;
		botaoConectar.setText("Desconectado");
		botaoConectar.setBackground(Color.RED);
		System.out.println("Classe ArduinoUsb, linha 214: porta devidamente fechada");
	}
}
