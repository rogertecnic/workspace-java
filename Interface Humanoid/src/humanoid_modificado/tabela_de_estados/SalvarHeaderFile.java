package humanoid_modificado.tabela_de_estados;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import humanoid_modificado.controles_dos_servos.TabbedPanel;

public class SalvarHeaderFile extends JPanel{

	public SalvarHeaderFile(TableModel modeloDaTabelaDeDados, TabbedPanel controlesServos){
		this.setLayout(new FlowLayout());
		this.setBorder(new TitledBorder("HeaderFile Arduino"));
		JButton botaoExportar = new JButton("Exportar .h");
		addListennerBotaoSalvar(botaoExportar, modeloDaTabelaDeDados, controlesServos);
		this.add(botaoExportar);
		this.setPreferredSize(new Dimension(this.getPreferredSize().width + 10, this.getPreferredSize().height));
	}

	private void addListennerBotaoSalvar(JButton botao, TableModel modeloDaTabelaDeDados, TabbedPanel controlesServos){
		botao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				JFileChooser fc = new JFileChooser();
				fc.setAcceptAllFileFilterUsed(false);
				fc.setFileFilter(new FileNameExtensionFilter("header files do arduino", "h"));

				if(fc.showSaveDialog(botao) == JFileChooser.APPROVE_OPTION){

					File f;
					if(!fc.getSelectedFile().toString().contains(".h")){
						f = new File(fc.getSelectedFile().toString() + ".h");
					} else {
						f = new File(fc.getSelectedFile().toString());
					}
					try {
						BufferedWriter buffW = new BufferedWriter(new FileWriter(f));
						ArrayList<LinhaDeEstado> listaDeEstados =  modeloDaTabelaDeDados.getListaDeEstados();



						buffW.write("#ifndef STATES_H");
						buffW.newLine();
						buffW.write("#define STATES_H");
						buffW.newLine();

						for(int i =0;i <listaDeEstados.size();i++){ // i eh o estado
							int var[] = listaDeEstados.get(i).getDados();

							// corrigindo os incrementos com a calibragem
							int calibragem[] = controlesServos.getDadosDaCalibragem();
							for(int j = 0; j<var.length/2; j++) // j eh o motor
								var[j*2+1]+=calibragem[j*2+1];
							
							// corrigi possiveis espacos no nome
							String nome = listaDeEstados.get(i).getNomeDoEstado();
							for(int c = 0;c< nome.length();c++){
								if(nome.charAt(c) == ' '){
									String tmp = nome.substring(0, c);
									tmp += "_";
									nome = tmp + nome.substring(c+1, nome.length());
									}
							}
							buffW.write("int " +nome+"[] = {");

							for(int j = 0;j<var.length/2;j++){ // j eh o motor
								buffW.write(var[j*2+1]+"");
								if(j<var.length/2 -1)
									buffW.write(",");
							}
							buffW.write("};");
							buffW.newLine();;
						}
						buffW.write("#endif");
						buffW.close ();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
