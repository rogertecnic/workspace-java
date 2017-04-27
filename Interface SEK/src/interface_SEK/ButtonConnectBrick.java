package interface_SEK;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;

/**
 * Botao que, ao clicar tentara fazer a conexao com o brick.
 * @author Rogerio
 *
 */
public class ButtonConnectBrick extends JButton {
		ClientSocket  threadClientSocket;
	public ButtonConnectBrick(){
		super("CONECTAR!");
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent mouse) {}

			@Override
			public void mousePressed(MouseEvent mouse) {}

			@Override
			public void mouseExited(MouseEvent mouse) {}

			@Override
			public void mouseEntered(MouseEvent mouse) {}

			@Override
			public void mouseClicked(MouseEvent mouse) {
					if(DEBUG_GUI_SEK.statusconnectlabel.getText() == "DESCONECTADO!"){
						threadClientSocket = new ClientSocket();
						threadClientSocket.start();
						
					}else{
						System.out.println("debug software: Brick ja esta conectado!");
					}

			}
		});
	}
	

}
