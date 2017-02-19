package humanoid_modificado.tabela_de_estados;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;

public class TableModel implements javax.swing.table.TableModel {
	private String[] nomeDasColunas = {"Numero", "Nome", "Excluir?"};
	private ArrayList<LinhaDeEstado> listaDeEstados = new ArrayList<LinhaDeEstado>();
	
	/**
	 * deve ser instanciado depois do TabbedPanel que controla os servos
	 * @param estadoInicial
	 */
	public TableModel(int[] estadoInicial){
		listaDeEstados.add(new LinhaDeEstado(0, estadoInicial, "Estado 0"));
	}

	@Override
	public int getColumnCount() {
		return nomeDasColunas.length;
	}

	@Override
	public int getRowCount() {
		return listaDeEstados.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return nomeDasColunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0: {
			return listaDeEstados.get(rowIndex).getNumero();
		} case 1 : {
			return listaDeEstados.get(rowIndex).getNomeDoEstado();
		} case 2 : {
			return listaDeEstados.get(rowIndex).getExcluir();
		} default : {
			return null;
		}
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0: {
			listaDeEstados.get(rowIndex).setNumero((int)aValue);
			break;
		} case 1 : {
			listaDeEstados.get(rowIndex).setNomeDoEstado((String) aValue);
			break;
		} case 2 : {
			listaDeEstados.get(rowIndex).setExcluir((Boolean) aValue);
			break;
		}
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 0)
			return false;
		else return true;
	}



	@Override
	public void addTableModelListener(TableModelListener l) {}
	@Override
	public void removeTableModelListener(TableModelListener l) {}

	public void inserirLinhaAbaixo(int index){
		if(index >=0 && index<= listaDeEstados.size()-1){
			LinhaDeEstado l = listaDeEstados.get(index);
			listaDeEstados.add(index+1, new LinhaDeEstado(index+1, l.getDados(),"Estado "+(index+1)));
			if(!l.getNomeDoEstado().equals("Estado " + l.getNumero()))
				listaDeEstados.get(index+1).setNomeDoEstado(l.getNomeDoEstado());
			
			for(int i = index+2; i <listaDeEstados.size(); i++){
				listaDeEstados.get(i).setNumero(i);
				if(listaDeEstados.get(i).getNomeDoEstado().equals("Estado " + (listaDeEstados.get(i).getNumero()-1)))
					listaDeEstados.get(i).setNomeDoEstado("Estado "+i);
			}
		}else System.out.println("classe TableModel linha 90: index fora de limite");
	}

	public void excluirLinhaSelecionada(JTable tabelaDeEstados){
		if(listaDeEstados.size()>1){
			if(listaDeEstados.get(tabelaDeEstados.getSelectedRow()).getExcluir()){
				listaDeEstados.remove(tabelaDeEstados.getSelectedRow());
				for(int i = tabelaDeEstados.getSelectedRow(); i <listaDeEstados.size();i++){
					listaDeEstados.get(i).setNumero(i);
					if(listaDeEstados.get(i).getNomeDoEstado().equals("Estado " + (i+1)))
						listaDeEstados.get(i).setNomeDoEstado("Estado "+(i));
				}
				if(tabelaDeEstados.getSelectedRow() == listaDeEstados.size()){
					
					tabelaDeEstados.setRowSelectionInterval(tabelaDeEstados.getSelectedRow()-1, tabelaDeEstados.getSelectedRow()-1);
				}
				tabelaDeEstados.updateUI();
			}
		}else {
			if(listaDeEstados.get(tabelaDeEstados.getSelectedRow()).getExcluir()){
			System.out.println("nao pode excluir todas as linhas");
			listaDeEstados.get(0).setExcluir(false);
			}
		}
	}
	
	public void atualizaEstado(int numeroDaLinha, int[] dados){
		listaDeEstados.get(numeroDaLinha).setDados(dados);
	}
	
	public int[] getDadosDoEstado(int numeroDaLinha){
		return listaDeEstados.get(numeroDaLinha).getDados();
	}
	
	
	public void setListaDeEstados(ArrayList<LinhaDeEstado> a){
		listaDeEstados = a;
	}
	
	public ArrayList<LinhaDeEstado> getListaDeEstados(){
		return listaDeEstados;
	}
	
}
