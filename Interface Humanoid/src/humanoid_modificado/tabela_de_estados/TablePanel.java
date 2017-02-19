package humanoid_modificado.tabela_de_estados;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import humanoid_modificado.controles_dos_servos.TabbedPanel;

public class TablePanel extends JPanel{
	private TableModel modeloDaTabelaDeEstados;
	private JTable tabelaDeEstados;
	private JScrollPane scrollDaTabela;
	private TabbedPanel controlesServos;
	public TablePanel(TabbedPanel controlesServos){
		this.controlesServos = controlesServos;
		controlesServos.setTabelaDeEstados(this);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		modeloDaTabelaDeEstados = new TableModel(controlesServos.getDadosDoIncremento());
		tabelaDeEstados = new JTable(modeloDaTabelaDeEstados);
		
		//configurandoo tamanho da tabela e o mouseListenner
		tabelaDeEstados.getColumnModel().getColumn(0).setResizable(false);
		tabelaDeEstados.getColumnModel().getColumn(2).setResizable(false);
		tabelaDeEstados.getColumnModel().getColumn(0).setMaxWidth(20);
		tabelaDeEstados.getColumnModel().getColumn(2).setMaxWidth(50);
		tabelaDeEstados.setPreferredScrollableViewportSize(new Dimension(300, 300));
		tabelaDeEstados.addMouseListener(tabelaMouseListener());
		tabelaDeEstados.getSelectionModel().addListSelectionListener(selecaoTecladoListener());
		tabelaDeEstados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaDeEstados.setRowSelectionInterval(0, 0);
		
		scrollDaTabela = new JScrollPane(tabelaDeEstados);
		scrollDaTabela.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.setBorder(new TitledBorder("Tabela de Estados:"));
		c.gridwidth = 2;
		this.add(scrollDaTabela,c);
		c.gridwidth = 1;
		c.gridy = 1;
		this.add(new ControleArquivoEstados(tabelaDeEstados, this),c);
		c.gridx = 1;
		this.add(new SalvarHeaderFile(modeloDaTabelaDeEstados, controlesServos),c);
		
	}

	private MouseListener tabelaMouseListener(){
		JPopupMenu mouseMenu = new JPopupMenu("menu mouse");
		JMenuItem criarEstadoAbaixo = new JMenuItem("adc abaixo.");
		criarEstadoAbaixo.addActionListener(popupMenuListener());
		mouseMenu.add(criarEstadoAbaixo);

		return new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == 3){
					mouseMenu.show(e.getComponent(), e.getX(), e.getY());
				} else if(e.getButton() == 1){
					modeloDaTabelaDeEstados.excluirLinhaSelecionada(tabelaDeEstados);
					controlesServos.setDadosDoIncremento(modeloDaTabelaDeEstados.getDadosDoEstado(tabelaDeEstados.getSelectedRow()));
					controlesServos.setDadosDoIncremento(modeloDaTabelaDeEstados.getDadosDoEstado(tabelaDeEstados.getSelectedRow())); // precisa chamar 2 vezes o metodo
					int[] i =modeloDaTabelaDeEstados.getDadosDoEstado(tabelaDeEstados.getSelectedRow());
					//DEBUG
					for(int a = 0;a<i.length; a++ ) System.out.print(i[a]);
					System.out.println("debug TablePanel linha 72");
				}
			}
		};
	}

	private ActionListener popupMenuListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloDaTabelaDeEstados.inserirLinhaAbaixo(tabelaDeEstados.getSelectedRow());
				tabelaDeEstados.updateUI();
			}
		};
	}
	
	private ListSelectionListener selecaoTecladoListener(){
		return new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				controlesServos.setDadosDoIncremento(modeloDaTabelaDeEstados.getDadosDoEstado(tabelaDeEstados.getSelectedRow()));
				controlesServos.setDadosDoIncremento(modeloDaTabelaDeEstados.getDadosDoEstado(tabelaDeEstados.getSelectedRow())); // precisa chamar 2 vezes o metodo
			}
		};
	}

	public void atualizaEstado(int[] dados){
		modeloDaTabelaDeEstados.atualizaEstado(tabelaDeEstados.getSelectedRow(), dados);
	}
	
	protected void updateControlesServos(){
		controlesServos.setDadosDoIncremento(modeloDaTabelaDeEstados.getDadosDoEstado(tabelaDeEstados.getSelectedRow()));
		controlesServos.setDadosDoIncremento(modeloDaTabelaDeEstados.getDadosDoEstado(tabelaDeEstados.getSelectedRow()));
	}
}
