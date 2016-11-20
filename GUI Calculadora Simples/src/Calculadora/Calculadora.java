package Calculadora;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Calculadora implements ActionListener  {
	//determinando variaveis e objetos
	JFrame frame = new JFrame();//janela
	JTextField textf1, textf2;//campos de texto
	JButton btSoma, btMult, btSub, btDiv;//botões
	double num1, num2;
	
	public Calculadora(){
		frame.setSize(400, 200);//tamanho da janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ação do botao fechar
		textf1 = new JTextField (15);
		textf2 = new JTextField (15);
		btSoma = new JButton("Soma");
		btSub = new JButton("Sub");
		btDiv = new JButton("Div");
		btMult = new JButton("Mult");
		frame.setLayout(new FlowLayout(FlowLayout.LEFT));//organiza os obj na tela "layout"
		
		frame.add(textf1);//adiciona na tela os objetos
		frame.add(textf2);
		frame.add(btSoma);
		frame.add(btSub);
		frame.add(btDiv);
		frame.add(btMult);
		
		btSoma.addActionListener(this);//adiciona ações nos botões
		btSub.addActionListener(this);
		btDiv.addActionListener(this);
		btMult.addActionListener(this);

	}
	
	//define um metodo da interface implementada, usado para determinar as funções dos botões
	@Override
	public void actionPerformed(ActionEvent e) {
		
		num1 = Double.parseDouble(textf1.getText());
		num2 = Double.parseDouble(textf2.getText());
		
		if(e.getSource() == btSub){
			System.out.println("Sub clicado");
			JOptionPane.showMessageDialog(null, "Sub é: " + (num1 - num2));}
		
		if(e.getSource() == btMult){
			System.out.println("mult clicado");
			JOptionPane.showMessageDialog(null, "Mult é: " + (num1 * num2));}
		
		if(e.getSource() == btDiv){
			System.out.println("Div clicado");
		JOptionPane.showMessageDialog(null, "div é: " + (num1 / num2));}
		
		if(e.getSource() == btSoma){
			System.out.println("Soma clicado");
			JOptionPane.showMessageDialog(null, "Soma é: " + (num1 + num2));}}
	
	public void setVisible(){
		frame.setVisible(true);}
	
	public static void main(String[] args){
		Calculadora c = new Calculadora();
		c.setVisible();
	}



}
