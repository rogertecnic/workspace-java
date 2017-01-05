package conect_brick_testes;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketMainClass {

	public static void main(String[] args){
		try {
			Socket clienteSocket = new Socket("10.0.1.1",8888);
			System.out.println("Conectado, IP:port:\n" +
			clienteSocket.getInetAddress().getHostAddress() + ":" +
					clienteSocket.getPort());
			
			PrintStream saida = new PrintStream(clienteSocket.getOutputStream());
			Scanner teclado = new Scanner(System.in);

			while(teclado.hasNextLine()){
				String line = teclado.nextLine();
				System.out.println(line);
				saida.println(line);
			}
			
			teclado.close();
			clienteSocket.close();
		} catch (IOException e) {
			System.out.println(" nao conectado");
			e.printStackTrace();
		}

	}
}
