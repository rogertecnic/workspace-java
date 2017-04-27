package interface_SEK;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.management.remote.NotificationResult;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * Classe versao 1.0.<br>
 * Deve ser implementada no codigo do ev3 para comunicacao
 * Somente chamar o metodo init() e sempre que quiser enviar um comando para o pc
 * usar o meto whrite(), se quiser ler um comando enviado do pc para o EV3
 * usar o metodo read();
 * @author Rogerio
 *
 */
public class ServerSocket extends Thread{
	private static Socket clientSocket = null;
	private static java.net.ServerSocket serverSocket = null;
	private static PrintStream saida = null;
	private static Scanner entrada = null;
	private static final ServerSocket THREAD = new ServerSocket();
	private static int latencia = 5;
	private static String strParaEnviar = "";
	private static String strParaReceber = "";


	/**
	 * Inicia a comunicacao com o pc, esse metodo deve ser chamado antes de conectar o pc
	 * @param latencia periodo em milisec de pausa entre cada iteracao da comunicacao
	 * padrao 5, favos nao usar um tempo diferente de 20 ate ser implementado no software esse tempo
	 */
	public static void init(int latencia){
		if(serverSocket == null){
			try {
				ServerSocket.latencia = latencia;
				serverSocket = new java.net.ServerSocket(8888);
				THREAD.setDaemon(true);
				THREAD.setName("ServerThread");
				THREAD.start();
			} catch (IOException e) {
				System.out.println("server: erro ao criar o serversocket!");
				e.printStackTrace();
			}


		}else 
			System.out.println("server ja iniciado, chamada multipla do metodo init!");
	}
	
	/**
	 * Enviar string para o pc, detalhe, ela sera enviada de acordo com a comunicacao dos sockets
	 * e nao no momento que este metodo eh chamado, se caso a comunicacao estiver cortada entao
	 * a string nem sera enviada, mas ficara salva para ser enviada assim que a conexao for retomada
	 * nao he um buffer, entao se caso outra string for sobrescrita com esse metodo 
	 * sobre a ultima antes da ultima ser enviada entao a ultima que foi sobrescrita
	 * sera perdida.
	 * @param s string a ser enviada;
	 */
	public static void whrite(String s){
		strParaEnviar = s;
	}
	
	
	public static String read(){
		return strParaReceber;
	}
	
	
	/**
	 * Sequencia de execucao da Thread:
	 *  periodo de latencia;<br>
	 *  verifica se o client foi instanciado:<br>
	 *  se nao entao cria o client;<br>
	 *  se sim entao:<br>
	 *  envia a string;<br>
	 *  verifica se recebeu algum comando:<br>
	 *  serecebeu algum comando zera o contador;<br>
	 *  se nao recebeu comando da ++ no contador, quando o contador chega a 10 entao ele desconecta o client<br>
	 *  e envia uma msg indicando que a conexao quebrou.
	 * 
	 */
	@Override
	public void run(){
		int cont = 0;
		String strTemp = "";
		while(true){
			try {
				sleep(latencia);
			} catch (InterruptedException e) {
				System.out.println("server: serversocket deu pau enquanto o periodo de latencia!!");
				e.printStackTrace();
			}


			if(clientSocket == null){
				try {
					System.out.println("server: Aguardando conexao...");
					clientSocket = serverSocket.accept();
					System.out.println("server: cliente conectado");
					saida = new PrintStream(clientSocket.getOutputStream());
					entrada = new Scanner(clientSocket.getInputStream());
				} catch (IOException e) {
					System.out.println("server: serversocket deu pau ao iniciar o client");
					e.printStackTrace();
				}
			}else{
				saida.println(strParaEnviar);
				strParaEnviar = "";

				if(entrada.hasNextLine()){
					cont = 0;
					strTemp = entrada.nextLine();
					if(!strTemp.equals("")){
						strParaReceber = strTemp;
						strTemp = "";
					}
				}else{
					cont ++;
					if(cont >=10){
						System.out.println("server: cliente nao responde, fechando e reiniciando conexao!");
						entrada.close();
						saida.close();
						try {
							clientSocket.close();
						} catch (IOException e) {
							System.out.println("server: deu pau ao fechar o clientSocket!!");
							e.printStackTrace();
						}
						entrada = null;
						saida = null;
						clientSocket = null;
					}
				}
			}
		}
	}
}