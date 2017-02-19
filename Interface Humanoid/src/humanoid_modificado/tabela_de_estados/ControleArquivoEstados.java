package humanoid_modificado.tabela_de_estados;

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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import humanoid_modificado.obj_estaticos_publicos_variados.ConversaoDeTipo;

public class ControleArquivoEstados extends JPanel{
	private JFileChooser fc = new JFileChooser();
	
	public ControleArquivoEstados(JTable tabelaDeEstados, TablePanel panelEstados){	
		this.setLayout(new FlowLayout());
		this.setBorder(new TitledBorder("Salvar/Carregar Estados"));
		JButton botaoSalvar = new JButton("Salvar");
		JButton botaoCarregar = new JButton("Carregar");
		this.add(botaoSalvar);
		this.add(botaoCarregar);
		
		addBotaoSalvarListener(botaoSalvar, tabelaDeEstados);
		addBotaoCarregarListener(botaoCarregar, tabelaDeEstados, panelEstados);
		
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new FileNameExtensionFilter("arquivo estados", "estados"));
	}

	private void addBotaoSalvarListener(JButton botao, JTable tabelaDeEstados) {
		botao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableModel modeloDaTabelaDeEstados = (TableModel)tabelaDeEstados.getModel();
				
				if(fc.showSaveDialog(botao) == JFileChooser.APPROVE_OPTION){
					String s = listaDeEstadosToString(modeloDaTabelaDeEstados);
					File f;
					if(!fc.getSelectedFile().toString().contains(".estados")){
						f = new File(fc.getSelectedFile().toString() + ".estados");
					} else {
						f = new File(fc.getSelectedFile().toString());
					}
					try {
						BufferedWriter ff = new BufferedWriter(new FileWriter(f));
						ff.write(s);
						ff.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	private void addBotaoCarregarListener(JButton botao, JTable tabelaDeEstados, TablePanel panelEstados) {
		botao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableModel modeloDaTabelaDeEstados = (TableModel)tabelaDeEstados.getModel();
				
				if(fc.showOpenDialog(botao) == JFileChooser.APPROVE_OPTION){
					try {
						String s = "";
						BufferedReader ff = new BufferedReader(new FileReader(fc.getSelectedFile()));
						while(ff.ready()){
						s += ff.readLine() + "\n";
						}
						modeloDaTabelaDeEstados.setListaDeEstados(stringToListaDeEstados(s));
						ff.close();
						tabelaDeEstados.setRowSelectionInterval(0, 0);
						tabelaDeEstados.updateUI();
						panelEstados.updateControlesServos();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	private String listaDeEstadosToString(TableModel modeloDaTabelaDeEstados){
		ArrayList<LinhaDeEstado> listaDeEstados = modeloDaTabelaDeEstados.getListaDeEstados();
		String s = "";
		for(int i = 0;i<listaDeEstados.size(); i++){
			s+=listaDeEstados.get(i).getNumero();
			s+=";";
			s+=listaDeEstados.get(i).getNomeDoEstado();
			s+=";";
			s+=ConversaoDeTipo.intToString(listaDeEstados.get(i).getDados());
			s+="\n";
		}
		return s;
	}
	
	private ArrayList<LinhaDeEstado> stringToListaDeEstados(String s){
		ArrayList<LinhaDeEstado> listaDeEstados = new ArrayList<LinhaDeEstado>();
		String[] tmp = s.split("\n");
		for(int i = 0; i < tmp.length;i++){
			String[] linhaDeEstado = tmp[i].split(";", 3);
			listaDeEstados.add(new LinhaDeEstado(Integer.parseInt(linhaDeEstado[0]), ConversaoDeTipo.stringToInt(linhaDeEstado[2]), linhaDeEstado[1]));
		}
		return listaDeEstados;
	}
}
