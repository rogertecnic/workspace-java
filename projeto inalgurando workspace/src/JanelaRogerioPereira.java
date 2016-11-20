import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.ScrollPane;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class JanelaRogerioPereira {
	
	int x = 0, y = 0;
	private JFrame frmWindowProgramTest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // pesquisar
			public void run() {
				try {
					JanelaRogerioPereira window = new JanelaRogerioPereira(); // por que dentro do try?
					window.frmWindowProgramTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JanelaRogerioPereira() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWindowProgramTest = new JFrame();
		frmWindowProgramTest.setTitle("Window program test");
		
		//frmWindowProgramTest.setBounds((1920-442)/2, (1080-463)/2, 442, 463);
		frmWindowProgramTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0,0,0};
		gridBagLayout.rowHeights = new int[]{0,0,0,0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		frmWindowProgramTest.getContentPane().setLayout(gridBagLayout);
		
//		SpringLayout springLayout = new SpringLayout();
//		frmWindowProgramTest.getContentPane().setLayout(springLayout);
		
		JPanel panel_map = new MapaPanel();
		GridBagConstraints gbc_panel_map = new GridBagConstraints();
		gbc_panel_map.gridwidth = 3;
		gbc_panel_map.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_map.gridy = 0;
		gbc_panel_map.gridx = 0;
		frmWindowProgramTest.getContentPane().add(panel_map, gbc_panel_map);
		//		springLayout.putConstraint(SpringLayout.NORTH, panel_map, 10, SpringLayout.NORTH, frmWindowProgramTest.getContentPane());
		//		springLayout.putConstraint(SpringLayout.WEST, panel_map, 10, SpringLayout.WEST, frmWindowProgramTest.getContentPane());
		//		springLayout.putConstraint(SpringLayout.SOUTH, panel_map, -172, SpringLayout.SOUTH, frmWindowProgramTest.getContentPane());
		//		springLayout.putConstraint(SpringLayout.EAST, panel_map, -145, SpringLayout.EAST, frmWindowProgramTest.getContentPane());
		//		panel_map.setBackground(Color.WHITE);
//		
//		JPanel panel_mouseXY = new JPanel();
//		springLayout.putConstraint(SpringLayout.WEST, panel_mouseXY, -100, SpringLayout.EAST, frmWindowProgramTest.getContentPane());
//		springLayout.putConstraint(SpringLayout.SOUTH, panel_mouseXY, 0, SpringLayout.SOUTH, frmWindowProgramTest.getContentPane());
//		springLayout.putConstraint(SpringLayout.NORTH, panel_map, 0, SpringLayout.NORTH, panel_mouseXY);
//		springLayout.putConstraint(SpringLayout.EAST, panel_map, -6, SpringLayout.WEST, panel_mouseXY);
//		springLayout.putConstraint(SpringLayout.NORTH, panel_mouseXY, 0, SpringLayout.NORTH, frmWindowProgramTest.getContentPane());
//		springLayout.putConstraint(SpringLayout.EAST, panel_mouseXY, 0, SpringLayout.EAST, frmWindowProgramTest.getContentPane());
//		frmWindowProgramTest.getContentPane().add(panel_mouseXY);
//		panel_mouseXY.setLayout(new GridLayout(0, 1, 0, 0));
//		
//		JLabel lblPositionX = new JLabel("position x:" + x);
//		lblPositionX.setToolTipText("posicao x do cursor dentro do mapa");
//		panel_mouseXY.add(lblPositionX);
//		
//		JLabel lblPositionY = new JLabel("position y:" + y);
//		lblPositionY.setToolTipText("posicao y do cursor dentro do mapa");
//		panel_mouseXY.add(lblPositionY);
		frmWindowProgramTest.pack();
	}
}

class MapaPanel extends JPanel{
	
	protected MapaPanel(){
		setBackground(Color.red); // branco
		Dimension dimensao = new Dimension(300, 500);
		setPreferredSize(dimensao);
		setMinimumSize(dimensao);
		setBorder(BorderFactory.createLineBorder(Color.black, 3));
	}
	
}