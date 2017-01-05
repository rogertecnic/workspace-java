package jFrame_barra_de_menu;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;

public class JFrameMenuBarClass {
	
	public static void main(String[] args){
		JFrame window = new JFrame("teste menu bar.");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		window.setSize(400, 400);
		
		JMenuBar menubar = new JMenuBar();
		window.setJMenuBar(menubar);
		
		JMenu menu = new JMenu("Menu 1!");
		menubar.add(menu);
	}
}
