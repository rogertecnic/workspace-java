package testeJTable;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class Main {
	
	public static void main(String[] args){
		Main main = new Main();
		//main.criaTabela();
		byte b =  (byte) -128;
		System.out.println(b);
	}
	
	
	private void criaTabela(){
		ModeloTabela modeloTabela = new ModeloTabela();
		JTable tabela = new JTable(modeloTabela);
		System.out.println(	tabela.getColumnModel().getColumn(0).getHeaderValue());
		tabela.getColumnModel().getColumn(0).setResizable(false);
		//tabela.getColumnModel().getColumn(0).setMinWidth(1);
		tabela.getColumnModel().getColumn(0).setMaxWidth(7);
		//tabela.getColumnModel().getColumn(1).setMaxWidth(5);
		tabela.getColumnModel().getColumn(2).setMaxWidth(15);
		tabela.setPreferredScrollableViewportSize(new Dimension(300, 20));
		
		tabela.setFillsViewportHeight(true);
		tabela.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("botao: "+ e.getButton()); // botao direito eh o 3 num mouse comum
				System.out.println("linha: " + tabela.getSelectedRow());
				JPopupMenu mouseMenu = new JPopupMenu("menu do mouse");
				mouseMenu.add(new JMenuItem("uma opcao do menu"));
				mouseMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
		JFrame frame = new JFrame("teste de tabela");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JScrollPane scroll = new JScrollPane(tabela);
		frame.add(scroll);
		frame.pack();
	}
	
	
	
	
	
	
	
	class ModeloTabela implements TableModel{

		
		
		private String[] nomeColunas = {"Nº","Estado", "Excluir"};
		private Object[][] data = {{1,"estado 1", "ss"},{2, "estado 2", "ss"},{3,"estado 3", "s"}};
		
		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}

		@Override
		public int getColumnCount() {
			return nomeColunas.length;
		}

		@Override
		public String getColumnName(int columnIndex) {
				return nomeColunas[columnIndex];
		}


		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if(columnIndex == 0 || columnIndex == 2)
			return true;
			else return true;
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			data[rowIndex][columnIndex] = aValue;
		}
		
		
	}
}
