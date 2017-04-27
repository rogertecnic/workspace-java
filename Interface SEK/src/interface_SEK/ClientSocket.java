package interface_SEK;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket extends Thread {
	private static Socket serverSocket = null;
	private static PrintStream saida = null;
	private static Scanner entrada = null;
	private int cont = 0;
	private int latencia = 20;
	private String strTemp = "";

	@Override
	public void run(){
		try{
			sleep(latencia);

			serverSocket = new Socket();
			System.out.println(IPBrickField.getip());
			serverSocket.connect(new InetSocketAddress(IPBrickField.getip(), 8888), 2000);
			saida = new PrintStream(serverSocket.getOutputStream());
			entrada = new Scanner(serverSocket.getInputStream());
			DEBUG_GUI_SEK.statusconnectlabel.setText("CONECTADO!");
			DEBUG_GUI_SEK.statusconnectlabel.setForeground(Color.GREEN);

			cont = 0;
			while(cont <10){
				saida.println(ConsoleFieldSender.strsender);
				ConsoleFieldSender.strsender = "";

				if(entrada.hasNextLine()){
					cont = 0;
					strTemp = entrada.nextLine();
					if(!strTemp.equals("")){
						ConsoleFieldReceiver.receiver = strTemp;
						strTemp = "";
					}
				} else{
					cont ++;
				}
			}
			entrada.close();
			saida.close();
			serverSocket.close();


			System.out.println("debug software: Conexao caiu, server encerrado!");
			DEBUG_GUI_SEK.statusconnectlabel.setText("DESCONECTADO!");
			DEBUG_GUI_SEK.statusconnectlabel.setForeground(Color.RED);

		} catch (InterruptedException e) {
			System.out.println("debug software: serversocket deu pau enquanto o periodo de latencia!!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("debug software: deu pa... detalhe olhe a linh do erro abaixo:");
			e.printStackTrace();
		}
	}
}
