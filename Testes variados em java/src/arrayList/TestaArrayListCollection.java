package arrayList;

import java.util.ArrayList;

public class TestaArrayListCollection {

	public static void main(String[] args){
		ArrayList<String> lista = new ArrayList<String>();
		
		System.out.println("tamanho do array:" + lista.size());
		//lista.add("adc 1");
		System.out.println("tamanho do array:" + lista.size());
		lista.add("");
		System.out.println("tamanho do array:" + lista.size());
		System.out.println(lista.hashCode());
	}

}
