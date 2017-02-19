package teste_JFileChoose;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.jfree.ui.FilesystemFilter;

public class Main {
	public static void main(String[] args){
		File file;
		JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new FilesystemFilter("humcfg", "arquivo de configuracao"));
		JTextArea escrever = new JTextArea();
		escrever.setColumns(30);
		escrever.setRows(3);
		file = new File(fc.getCurrentDirectory().toString());
		fc.setSelectedFile(file);

		JButton abrir = new JButton("open");
		abrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fc.showOpenDialog((JButton)e.getSource()) == JFileChooser.APPROVE_OPTION){
					try {
						BufferedReader ff = new BufferedReader(new FileReader(fc.getSelectedFile()));
						if(ff.ready()) escrever.setText("");
						while(ff.ready()){
							escrever.setText(escrever.getText()  + ff.readLine()+ (ff.ready()?"\n":""));
						}
						ff.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		JButton salvar = new JButton("save");
		salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fc.showSaveDialog((JButton)e.getSource()) == JFileChooser.APPROVE_OPTION){
					File f;
					if(!fc.getSelectedFile().toString().contains(".humcfg")){
						f = new File(fc.getSelectedFile().toString() + ".humcfg");
					} else {
						f = new File(fc.getSelectedFile().toString());
					}
					try {
						BufferedWriter ff = new BufferedWriter(new FileWriter(f));
						ff.write(escrever.getText());
						ff.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});


		JFrame janela = new JFrame("janela");
		janela.setLayout(new FlowLayout());
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(salvar);
		janela.add(abrir);
		janela.add(escrever);







		janela.setVisible(true);
		janela.pack();
	}
}
