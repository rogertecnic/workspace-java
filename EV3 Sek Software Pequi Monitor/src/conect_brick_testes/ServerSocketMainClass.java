package conect_brick_testes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketMainClass {
	public static void main(String[] Args){
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("servidor iniciado:\n" +
					serverSocket.getInetAddress().getHostAddress() + ":" +
					serverSocket.getLocalPort());

			Socket clienteSocket = serverSocket.accept();
			System.out.println("cliente conectado:\n"+
					clienteSocket.getInetAddress().getHostAddress() + ":" +
					clienteSocket.getPort());


			Scanner entrada = new Scanner(clienteSocket.getInputStream());
			while(entrada.hasNextLine()){
				System.out.println(entrada.nextLine());
			}

			entrada.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
