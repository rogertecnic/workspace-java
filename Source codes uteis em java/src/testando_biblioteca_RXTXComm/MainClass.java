package testando_biblioteca_RXTXComm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.swing.JOptionPane;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

public class MainClass {
	private static boolean portaAberta = false;
	private static SerialPort porta = null;

	public static void main(String[] args){
		for(int i = 1; i< 11; i++){
			System.out.println("iteracao numero:" + i);
			porta = verificaSePortaDisponivel(verificaSePortaExiste("COM3"));

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			porta.close();
			porta = null;
			portaAberta = false;
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	/**
	 * Verifica se a porta pedida existe neste pc e retorna seu id, este metodo nao verifica se ela esta
	 * livre ou ocupada.
	 * @param nomePorta porta a ser verificada
	 * @return CommPortIdentifier da porta se a porta existir e null se nao existir
	 */
	private static CommPortIdentifier verificaSePortaExiste(String nomePorta){
		CommPortIdentifier portID = null;
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> listaDePortas = CommPortIdentifier.getPortIdentifiers();
		while(listaDePortas.hasMoreElements()){
			portID = listaDePortas.nextElement();
			if(nomePorta.equals(portID.getName())){
				System.out.println("porta " + nomePorta +  " existente, id da porta retornado");
				return portID;
			}
		}
		System.out.println("porta " + nomePorta +  " NAO Existe: arduino deve estar desconectado da USB.");
		return null;
	}

	private static SerialPort verificaSePortaDisponivel(CommPortIdentifier portID){
		try {
			if(!portaAberta){
				if(portID != null){
					SerialPort serialPort = (SerialPort) portID.open("owner da porta: Software humanoid", 2000);
					portaAberta = true;
					System.out.println(CommPortIdentifier.getPortIdentifier(serialPort).getName());
					System.out.println("porta aberta e retornada");
					return serialPort;
				}else{
					return null;
				}
			} else {
				if(portID != null){
					porta.close();
				porta = null;
				return null;
				}
				return porta;
			}
		} catch (PortInUseException | NoSuchPortException e) {
			System.out.println("falha ao tentar abrir a porta, deve estar sendo usada por outro aplicativo");
			e.printStackTrace();
			return null;
		}
	}


	private static void testaComunicacao(){
		CommPortIdentifier portID = null;
		CommPortIdentifier portID2 = null;
		SerialPort serialPort = null;
		InputStream serialIn = null;

		while(true){
			try {

				if(serialPort == null){
					Thread.sleep(2000);
					portID = CommPortIdentifier.getPortIdentifier("COM3");
					portID2 = CommPortIdentifier.getPortIdentifier("COM3");
					System.out.println("line 23");
					serialPort = (SerialPort) portID.open("owner da porta", 100);
					System.out.println("line 25");
					serialIn = serialPort.getInputStream();
				}

				Thread.sleep(1000);
				serialIn.read();
				System.out.println("leitura feita");

			} catch (NoSuchPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PortInUseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {

				try {
					serialIn.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				serialPort.close();
				serialPort = null;
				e.printStackTrace();
				System.out.println("porta fechada");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
