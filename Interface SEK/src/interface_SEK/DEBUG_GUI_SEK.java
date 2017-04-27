package interface_SEK;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

public class DEBUG_GUI_SEK {
	public static JLabel statusconnectlabel;

	public static void main(String[] args) {
		JFrame frame = new JFrame("DEBUG GUI SEK 2017 1.0");
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		final ConsoleFieldReceiver textfieldreceiver = new ConsoleFieldReceiver();
		MenuBar menubar = new MenuBar(textfieldreceiver);
		frame.setJMenuBar(menubar);

		
		JScrollPaneClass scrollpanereceiver = new JScrollPaneClass(textfieldreceiver);
		scrollpanereceiver.setWheelScrollingEnabled(true);
		scrollpanereceiver.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpanereceiver.getVerticalScrollBar().setAutoscrolls(true);
		scrollpanereceiver.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			int scrollheigth = textfieldreceiver.getHeight();
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if(textfieldreceiver.getHeight() != scrollheigth){
					e.getAdjustable().setValue(e.getAdjustable().getMaximum());
					scrollheigth = textfieldreceiver.getHeight();
				}

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, scrollpanereceiver, 5, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollpanereceiver, 5, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollpanereceiver, -5, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(scrollpanereceiver);

		ConsoleFieldSender textfieldsender = new ConsoleFieldSender();
		springLayout.putConstraint(SpringLayout.SOUTH, scrollpanereceiver, -5, SpringLayout.NORTH, textfieldsender);
		springLayout.putConstraint(SpringLayout.EAST, textfieldsender, -5, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(textfieldsender);

		JLabel senderlabel = new JLabel("Send:");
		springLayout.putConstraint(SpringLayout.WEST, textfieldsender, 5, SpringLayout.EAST, senderlabel);
		springLayout.putConstraint(SpringLayout.WEST, senderlabel, 5, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(senderlabel);


		IPBrickField textfieldip =  new IPBrickField();
		springLayout.putConstraint(SpringLayout.SOUTH, textfieldip, -5, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(textfieldip);

		JLabel ipbricklabel = new JLabel("IPBrick:");
		springLayout.putConstraint(SpringLayout.WEST, textfieldip, 5, SpringLayout.EAST, ipbricklabel);
		springLayout.putConstraint(SpringLayout.EAST, textfieldip, 60, SpringLayout.EAST, ipbricklabel);
		springLayout.putConstraint(SpringLayout.WEST, ipbricklabel, 5, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ipbricklabel, -5, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(ipbricklabel);

		ButtonConnectBrick buttonconnect = new ButtonConnectBrick();
		springLayout.putConstraint(SpringLayout.SOUTH, senderlabel, -5, SpringLayout.NORTH, buttonconnect);
		springLayout.putConstraint(SpringLayout.SOUTH, textfieldsender, -5, SpringLayout.NORTH, buttonconnect);
		springLayout.putConstraint(SpringLayout.WEST, buttonconnect, 5, SpringLayout.EAST, textfieldip);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonconnect, -5, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(buttonconnect);

		statusconnectlabel = new JLabel("DESCONECTADO!");
		springLayout.putConstraint(SpringLayout.WEST, statusconnectlabel, 5, SpringLayout.EAST, buttonconnect);
		springLayout.putConstraint(SpringLayout.SOUTH, statusconnectlabel, -5, SpringLayout.SOUTH, frame.getContentPane());
		statusconnectlabel.setForeground(Color.RED);
		frame.getContentPane().add(statusconnectlabel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}

}
