package com_serial_arduino_usb;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;

public class ThreadEscrita implements Runnable {
	private ArduinoUsb arduino_ = null; // necessario para referenciar o proprio arduino dentro das threads
	private String nomeDaPortaCOM = null;
	private OutputStream serialOut = null;
	private SerialPort serialPort = null;
	protected String strOut = "";

	public ThreadEscrita(ArduinoUsb arduino_, SerialPort serialPort) throws NoSuchPortException, IOException{
		this.arduino_ = arduino_;
		this.serialPort = serialPort;
		serialOut = serialPort.getOutputStream();
		nomeDaPortaCOM = CommPortIdentifier.getPortIdentifier(serialPort).getName();
		Thread thread = new Thread(this, "ThreadEscrita");
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void run() {
		while(verificaConexao(nomeDaPortaCOM) && arduino_.arduinoConectado){
			try{
				synchronized (arduino_) {
					arduino_.wait();
					if(strOut.equals(""))
						serialOut.write("1".getBytes());
					else {
						serialOut.write(strOut.getBytes());
						strOut = "";
					}
					arduino_.notify();
				}
			} catch(InterruptedException e ){
				System.out.println("Classe ThreadEscrita linha 43: interrompida durante o whait");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Classe ThreadEscrita linha 46: thread escrita deu problema no whrite");
				e.printStackTrace();
			}
		}
		synchronized(arduino_){
			if(arduino_.arduinoConectado){
				serialPort.close();
				System.out.println("Classe ThreadEscrita, linha 53: porta fechada na ThreadEscrita");
				arduino_.reset();
			}
			arduino_.notify();
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
		Enumeration<CommPortIdentifier> listaDePortas = CommPortIdentifier.getPortIdentifiers();
		while(listaDePortas.hasMoreElements()){
			CommPortIdentifier	portID = listaDePortas.nextElement();
			if(nomeDaPorta.equals(portID.getName())){
				return true;
			}
		}
		System.out.println("Classe ThreadEscrita linha 75: porta " + nomeDaPorta +  " desconectada");
		return false;
	}
}
