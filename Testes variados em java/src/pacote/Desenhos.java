package pacote;

import java.awt.Graphics;//desenhar
import javax.swing.JPanel;//um "painel" de desenhar
import javax.swing.JFrame;//janelas

/**
 * Class Desenhar herda a class JPanel, ou seja, Desenhar é uma subclass e a JPAnel é uma superclass
 * a JPanel tem um metodo paintComponent e as suas subclass também, que é chamado automaticamente
 * sempre que o sistema precisa exibir um JPanel ou sempre que há alteração no mesmo, 
 * esse metodo é declarado dentro da nossa subclass e
 * ele invoca o metodo paintComponent da superclass JPanel
 *
 */
class Desenhar extends JPanel {
	public void paintComponent (Graphics ttt){//declara o paintComponent da nossa subclass
		super.paintComponent (ttt);//o sistema oferece o objeto tipo Graphics
		
		int width = getWidth();//retorna a largura do JPanel
		int height = getHeight();//retorna a altura do JPanel
		
		for(int a=0; a < width; a=a+10){
			ttt.drawLine(0, a, a, height);//desenha uma linha de ponto a ponto(x,y) ate (x,y)
			ttt.drawLine(a, height, width, height-a);
			//ttt.drawString("atirei o pau no gatu", 18,a);
		}
	}
}


public class Desenhos{
	public static void main(String[] args){
		Desenhar painel = new Desenhar();
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//indica que o aplicativo defe terminar quando fechar a janela
		frame.add(painel);//adiciona o obj "painel" ao frame, o desenho fica dentro do jpanel "painel"
		frame.setSize(500, 500);//determina a altura e largura do obj frame
		frame.setVisible(true);//invoca o frame e o sistema invoca implicitamente aqui o metodo paintComponent
	}
	
}