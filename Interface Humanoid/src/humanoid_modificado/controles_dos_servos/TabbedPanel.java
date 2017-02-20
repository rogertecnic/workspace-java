package humanoid_modificado.controles_dos_servos;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import humanoid_modificado.obj_estaticos_publicos_variados.ArduinoPanel;
import humanoid_modificado.tabela_de_estados.TablePanel;

/**
 * Panel que contem as duas tabs de sliders
 * @author Rogerio
 *
 */
public class TabbedPanel extends JTabbedPane {
	//JPanel de cada aba do JTabbedPane
	private JPanel panelCalibragemMotores = new JPanel(new GridBagLayout());
	private JPanel panelIncrementosMotores = new JPanel(new GridBagLayout());
	
	private TablePanel tabelaDeEstados;
	
	//servos de incremento
	private ArrayList<ServoMotorSliderPanel> arrayServosIncremento = new ArrayList<ServoMotorSliderPanel>();

	//servos de calibragem
	private ArrayList<ServoMotorSliderPanel> arrayServosCalibragem = new ArrayList<ServoMotorSliderPanel>();


	public TabbedPanel(){
		addServoSlider(null, this);
		addServoSlider(arrayServosIncremento, this);
		addPaineis();
		panelCalibragemMotores.setBackground(new Color(230, 160, 200));
		this.addTab("Calibragem motores",null, panelCalibragemMotores, "Guia de calibragem dos motores, estes valores serao os zeros dos motores.");
		this.addTab("Incremento motores",null, panelIncrementosMotores, "Guia de comando dos motores, para construir os estados da marcha.");
	}
	
	/**
	 * deve ser instanciado antes da TableModel que eh a tabela de estados
	 * apos instanciada a TableModel se deve chamar o metodo setTabelaDeEstados e passar a TableModel instanciada
	 * @param servosDeIncremento
	 */
	private void addServoSlider(ArrayList<ServoMotorSliderPanel> servosDeIncremento, TabbedPanel controleDosServos){
		for(int id = 0; id <14; id++){ // quantidade total de motores, alterar aqui
			if(servosDeIncremento == null)
				arrayServosIncremento.add(new ServoMotorSliderPanel(id, -90, 90, 0, null, controleDosServos));
			else
				arrayServosCalibragem.add(new ServoMotorSliderPanel(id, 0, 180, 90, servosDeIncremento	.get(id), controleDosServos));
		}
	}

	private void addPaineis(){
		GridBagConstraints c0 = new GridBagConstraints();
		c0.insets = new Insets(0, 0, 4, 2);
		GridBagConstraints c1 = new GridBagConstraints();
		c1.insets = new Insets(0, 0, 2, 1);

		//adicionando controles de calibragem
		c0.gridx = 0;
		c0.gridy = 0;
		panelCalibragemMotores.add(bracoEsquerdo(true, c1), c0);
		c0.gridx = 1;
		c0.gridy = 0;
		panelCalibragemMotores.add(bracoDireito(true, c1), c0);
		c0.gridx = 0;
		c0.gridy = 1;
		panelCalibragemMotores.add(pernaEsquerda(true, c1),c0);
		c0.gridx = 1;
		c0.gridy = 1;
		panelCalibragemMotores.add(pernaDireita(true, c1), c0);


		//adicionando controles de incremento
		c0.gridx = 0;
		c0.gridy = 0;
		panelIncrementosMotores.add(bracoEsquerdo(false, c1), c0);
		c0.gridx = 1;
		c0.gridy = 0;
		panelIncrementosMotores.add(bracoDireito(false, c1), c0);
		c0.gridx = 0;
		c0.gridy = 1;
		panelIncrementosMotores.add(pernaEsquerda(false, c1),c0);
		c0.gridx = 1;
		c0.gridy = 1;
		panelIncrementosMotores.add(pernaDireita(false, c1), c0);
	}

	private JPanel pernaEsquerda(boolean calibragem, GridBagConstraints c){
		c.gridx = 0;
		JPanel pernaEsquerda = new JPanel(new GridBagLayout());
		pernaEsquerda.setBorder(new TitledBorder("Perna Esquerda."));

		if(calibragem){
			pernaEsquerda.setBackground(new Color(230, 160, 200));
			for(int y = 0; y<=3;y++){ // para adicionar mais motores eh so modificar aqui
				c.gridy = y;
				pernaEsquerda.add(arrayServosCalibragem.get(y), c);
			} 
		}else{
			for(int y = 0; y<=3;y++){// para adicionar mais motores eh so modificar aqui
				c.gridy = y;
				pernaEsquerda.add(arrayServosIncremento.get(y), c);
			}
		}
		return pernaEsquerda;
	}

	private JPanel pernaDireita(boolean calibragem, GridBagConstraints c){
		c.gridx = 0;
		JPanel pernaDireita = new JPanel(new GridBagLayout());
		pernaDireita.setBorder(new TitledBorder("Perna Direita"));
		if(calibragem){
			pernaDireita.setBackground(new Color(230, 160, 200));
			for(int y =0; y<=3; y++){// para adicionar mais motores eh so modificar aqui
				c.gridy = y;
				pernaDireita.add(arrayServosCalibragem.get(y+4), c);
			}
		}else{
			for(int y =0; y<=3; y++){// para adicionar mais motores eh so modificar aqui
				c.gridy = y;
				pernaDireita.add(arrayServosIncremento.get(y+4), c);
			}
		}
		return pernaDireita;
	}

	private JPanel bracoEsquerdo(boolean calibragem, GridBagConstraints c){
		JPanel bracoEsquerdo = new JPanel(new GridBagLayout());
		bracoEsquerdo.setBorder(new TitledBorder("Braco Esquerdo"));
		c.gridx = 0;
		c.gridy = 0;
		if(calibragem){
			bracoEsquerdo.setBackground(new Color(230, 160, 200));
			for(int y =0; y<=2; y++){// para adicionar mais motores eh so modificar aqui
				c.gridy = y;
			bracoEsquerdo.add(arrayServosCalibragem.get(y+8), c);
			}
		}else{
			for(int y =0; y<=2; y++){// para adicionar mais motores eh so modificar aqui
				c.gridy = y;
			bracoEsquerdo.add(arrayServosIncremento.get(y+8), c);
			}
		}
		return bracoEsquerdo;
	}
	
	private JPanel bracoDireito(boolean calibragem, GridBagConstraints c){
		JPanel bracoDireito = new JPanel(new GridBagLayout());
		bracoDireito.setBorder(new TitledBorder("Braco Direito"));
		c.gridx = 0;
		c.gridy = 0;
		if(calibragem){
			bracoDireito.setBackground(new Color(230, 160, 200));
			for(int y =0; y<=2; y++){// para adicionar mais motores eh so modificar aqui
				c.gridy = y;
			bracoDireito.add(arrayServosCalibragem.get(y+11), c);
			}
		}else{
			for(int y =0; y<=2; y++){// para adicionar mais motores eh so modificar aqui
				c.gridy = y;
			bracoDireito.add(arrayServosIncremento.get(y+11), c);
			}
		}
		return bracoDireito;
	}

	public void setTabelaDeEstados(TablePanel tabelaDeEstados){
		this.tabelaDeEstados = tabelaDeEstados;
	}
	

	public void enviaAngulosParaArduino(){
		String estadoAtual = "";
		for(int i = 0; i<= arrayServosIncremento.size()-1; i++){
			estadoAtual += (arrayServosCalibragem.get(i).getValue() + arrayServosIncremento.get(i).getValue()) + ";";
		}
		//System.out.println("class TabbedPanel linha 168:" + estadoAtual);
		ArduinoPanel.arduino().write(estadoAtual);
	}
	
	public int[] getDadosDaCalibragem(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i<= arrayServosCalibragem.size()-1; i++){
			list.add(arrayServosCalibragem.get(i).getID());
			list.add(arrayServosCalibragem.get(i).getValue());
			//System.out.println("TabbedPanel" + arrayServosCalibragem.get(i).getValue());
		}
		int[] vetor = new int[list.size()];
		for(int i = 0; i <list.size(); i++) vetor[i] = list.get(i);
		return vetor;
	}
	
	public int[] getDadosDoIncremento(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i<= arrayServosIncremento.size()-1; i++){
			list.add(arrayServosIncremento.get(i).getID());
			list.add(arrayServosIncremento.get(i).getValue());
		}
		int[] vetor = new int[list.size()];
		for(int i = 0; i <list.size(); i++) vetor[i] = list.get(i);
		return vetor;
	}
	
	public void setDadosDaCalibragem(int[] dados){
		for(int i = 0; i <dados.length/2; i ++ ){
			arrayServosCalibragem.get(i).setValue(dados[i*2+1]);
		}
		panelIncrementosMotores.updateUI();
	}
	
	public void setDadosDoIncremento(int[] dados){
		for(int i = 0; i <(dados.length/2); i ++ ){
			arrayServosIncremento.get(i).setValue(dados[i*2+1]);
			arrayServosCalibragem.get(i).atualizaControleServoIncremental();
		}
	}
	
	public void atualizaTabelaDeEstados(){
		tabelaDeEstados.atualizaEstado(getDadosDoIncremento());
	}
	
}
