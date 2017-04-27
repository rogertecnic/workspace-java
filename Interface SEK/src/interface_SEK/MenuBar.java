package interface_SEK;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *Contem toda a barra de menu do topo da janela. 
 * @author Rogerio
 *
 */
public class MenuBar extends javax.swing.JMenuBar{
	
	/**
	 * construtor que cria e gerencia a barra de menu
	 */
	public MenuBar(){
		super();
		this.add(createMenu1());
		this.add(createMenu2());
	}
	
	/**
	 * Cria e adiciona o Menu1
	 */
	private JMenu createMenu1(){
		JMenu menu1 = new JMenu("Menu 1");
		menu1.setMnemonic(KeyEvent.VK_1);
		menu1.add(createMenuItem1_1());
		menu1.add(createSubMenu1_2());
		
		return menu1;
	}
	
	/**
	 * Cria e adiciona o Menu2
	 */
	private JMenu createMenu2(){
		JMenu menu2 = new JMenu("Menu 2");
		menu2.setMnemonic(KeyEvent.VK_2);
		
		return menu2;
	}
	
	/**
	 * Cria e adiciona o MenuItem 1.1
	 */
	private JMenuItem createMenuItem1_1(){
		JMenuItem menuitem1_1 = new JMenuItem("MenuItem 1.1");
		menuitem1_1.setMnemonic(KeyEvent.VK_1);
		
		return menuitem1_1;
	}
	
	/**
	 * Cria e adiciona o SubMenu1.2
	 */
	private JMenu createSubMenu1_2(){
		JMenu submenu1_2 = new JMenu("SubMenu 1.2");
		submenu1_2.setMnemonic(KeyEvent.VK_2);
		
		return submenu1_2;
	}
}
