package humanoid_modificado.controles_dos_servos;

import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import humanoid_modificado.obj_estaticos_publicos_variados.ConversaoDeTipo;


/**
 * 
 * @author Rogerio
 *
 */
public class ControleArquivoCalibragem extends JPanel{
	private JFileChooser fc = new JFileChooser();
	private JLabel jaCarregado;

	public ControleArquivoCalibragem(TabbedPanel controlesServos){
		super();
		this.setBackground(new Color(120, 120, 120));
		this.setLayout(new FlowLayout());
		this.setBorder(new TitledBorder("Salvar/Carregar Calibragem"));
		jaCarregado = new JLabel("calibragem nao carregada");
		jaCarregado.setForeground(new Color(255, 128, 0));
		JButton botaoSalvar = new JButton("Salvar");
		JButton botaoCarregar = new JButton("Carregar");
		this.add(botaoSalvar);
		this.add(botaoCarregar);
		this.add(jaCarregado);
		addListennerBotaoSalvar(botaoSalvar, controlesServos);
		addListennerBotaoCarregar(botaoCarregar, controlesServos);
		
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new FileNameExtensionFilter("arquivo calibragem", "humcfg"));
	}

	private void addListennerBotaoSalvar(JButton botao, TabbedPanel controlesServos){
		botao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = ConversaoDeTipo.intToString(controlesServos.getDadosDaCalibragem());			

				if(fc.showSaveDialog(botao) == JFileChooser.APPROVE_OPTION){
					File f;
					if(!fc.getSelectedFile().toString().contains(".humcfg")){
						f = new File(fc.getSelectedFile().toString() + ".humcfg");
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

	private void addListennerBotaoCarregar(JButton botao, TabbedPanel controlesServos){
		botao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fc.showOpenDialog(botao) == JFileChooser.APPROVE_OPTION){
					try {
						BufferedReader ff = new BufferedReader(new FileReader(fc.getSelectedFile()));
						controlesServos.setDadosDaCalibragem(ConversaoDeTipo.stringToInt(ff.readLine()));
						ff.close();
						jaCarregado.setText("Calibragem Carregada");
						jaCarregado.setForeground(new Color(128, 255, 0));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}


				}
			}

		});
	}
}
