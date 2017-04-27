package conect_brick_testes;

import interface_SEK.ServerSocket;

public class MainClass {
	public static void main(String[] args){
		ServerSocket.init(20);
		String s = "";
		String ss = "";
		int i = 0;
		while(true){
			try {
				Thread.sleep(1);
				s = ServerSocket.read();
				if(!s.equals(ss)){
					System.out.println(s);
					ss = s;
				}
				
				i++;
				ServerSocket.whrite(Integer.toString(i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
