package humanoid_original;

import java.util.List;

public class Estado {
	String nome;
	 int[] valores ;
	 
	 public Estado(){
		 valores = new int[14];
	 }
	 
	 public void set(int index,int value){
		 valores[index] = value;
	 }
	 
	 public int[] getEstadoValores(){
		 return valores;
	 }
	 
	 public void setNome(String _nome){
		 nome = _nome;
	 }
	 
	 public String getNome(){
		 return nome;
	 }
	 
	 
		public Estado addEstado(List<Estado> listEstado,List<GroupSlider> list,String nome){
			Estado estado = new Estado();
			for(int i=0;i<list.size();i++){
				estado.set(i, list.get(i).getSlider().getValue());
			}
			estado.setNome(nome);
			listEstado.add(estado);
			
			return estado;
		}
		
		public void removeEstado(List<Estado> listEstado,String nome){
			int index =-1;
			for(int i=0;i<listEstado.size();i++){
				if(listEstado.get(i).nome.equals(nome)){
					index = i;;
					break;
				}
			}
			
			if(index!=-1){
				listEstado.remove(index);
			}
		}
		
		
		
		
		public Estado getEstado(List<Estado> listEstado,String nome){
			int index = -1;
			for(int i=0;i<listEstado.size();i++){
				if(listEstado.get(i).nome.equals(nome)){
					index = i;
				}
			}
			
			return listEstado.get(index);
		}
		
		public void atualizareStado(Estado estado,List<GroupSlider> groupSlider){
			for(int i=0;i<groupSlider.size();i++){
				estado.getEstadoValores()[i] = groupSlider.get(i).getSlider().getValue(); 
			}
		}
	 
}
