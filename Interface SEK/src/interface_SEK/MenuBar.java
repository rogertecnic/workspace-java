package interface_SEK;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *Contem toda a barra de menu do topo da janela. 
 * @author Rogerio
 *
 */
public class MenuBar extends javax.swing.JMenuBar{
	private ConsoleFieldReceiver consoleReceiver;
	/**
	 * construtor que cria e gerencia a barra de menu
	 */
	public MenuBar(ConsoleFieldReceiver colsoleReceiver){
		super();
		this.add(createMenuTools());
		this.add(createMenu2());
		consoleReceiver = consoleReceiver;
	}
	
	/**
	 * Cria e adiciona o Menu1
	 */
	private JMenu createMenuTools(){
		JMenu menu1 = new JMenu("Tools");
		menu1.setMnemonic(KeyEvent.VK_1);
		menu1.add(createMenuItemChart());
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
	private JMenuItem createMenuItemChart(){
		JMenuItem menuitemChart = new JMenuItem("Criar um grafico");
		menuitemChart.setToolTipText("Cria um grafico XY com os valores inseridos, devem estar no formato (x,y)");
		menuitemChart.setMnemonic(KeyEvent.VK_1);
		menuitemChart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createChart();
			}
		});
		return menuitemChart;
	}
	
	/**
	 * Cria e adiciona o SubMenu1.2
	 */
	private JMenu createSubMenu1_2(){
		JMenu submenu1_2 = new JMenu("SubMenu 1.2");
		submenu1_2.setMnemonic(KeyEvent.VK_2);
		
		return submenu1_2;
	}
	
	/**
	 * Cria o grafico pegando os dados que estao no console de dados recebidos
	 */
	private void createChart(){
		XYSeries serieDeDados = new XYSeries("Serie de dados 1");
		//String log = consoleReceiver.getLog();
		String log = "(xy)(ttt)(rrr)";
//		while(true){
//			System.out.println("contei");
//		}
		XYSeriesCollection colecaoDeSeries = new XYSeriesCollection(serieDeDados);
		JFreeChart chart = ChartFactory.createXYLineChart("Gráfico", "LAbel x", "Label y", colecaoDeSeries, PlotOrientation.VERTICAL, true, true, true);
		ChartFrame frame = new ChartFrame("titulo do frame", chart);
		frame.pack();
		frame.setVisible(true);
	}
}
