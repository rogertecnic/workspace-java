package humanoid_modificado;


import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import humanoid_modificado.controles_dos_servos.ControleArquivoCalibragem;
import humanoid_modificado.controles_dos_servos.TabbedPanel;
import humanoid_modificado.obj_estaticos_publicos_variados.ArduinoPanel;
import humanoid_modificado.tabela_de_estados.TablePanel;


public class Janela {

	public Janela(Container pane){	
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 9;
		c.insets = new Insets(10,10,0,0);
		TabbedPanel controlesServos = new TabbedPanel();
		TablePanel	tabelaDeEstados = new TablePanel(controlesServos);
		pane.add(controlesServos,c);

		c.gridx = 2;
		c.gridheight = 4;
		pane.add(tabelaDeEstados, c);

		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(new ControleArquivoCalibragem(controlesServos), c);

		c.gridx = 1;
		pane.add(ArduinoPanel.getArduinoPanel(), c);
	}
}