package com_serial_arduino_usb;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;
import javax.xml.crypto.dsig.keyinfo.KeyName;

import org.jfree.data.KeyedValue;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class MainClass {


	public static void main(String[] args){
		JFrame janela = new JFrame("testando comunicacao com o arduino");
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLayout(new BorderLayout());
		JButton b = new JButton();
		ArduinoUsb arduino = new ArduinoUsb(b);
		janela.getContentPane().add(b, BorderLayout.CENTER);
		JTextField t = new JTextField("digite aqui e de enter, ao finao do loop do arduino ele ira ler");
		t.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					arduino.enviar(t.getText());
					t.setText("");
				}
			}
		});
		janela.getContentPane().add(t, BorderLayout.SOUTH);
		
		janela.pack();
		
		while(true){
			try {
				Thread.sleep(100);
				String a = arduino.ler();
				if(!a.equals(""))
				System.out.println(a);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
