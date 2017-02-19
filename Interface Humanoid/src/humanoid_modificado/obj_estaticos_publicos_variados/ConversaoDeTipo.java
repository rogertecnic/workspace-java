package humanoid_modificado.obj_estaticos_publicos_variados;

import java.util.ArrayList;

/**
 * Possui metodos estaticos para se trabalhar com conversao de dados entre int[], String e Byte[]
 * para salvar em arquivo e converter para o arduino
 * @author Rogerio
 *
 */
public class ConversaoDeTipo {
	
	/**
	 * <b>METODO USADO PARA SALVAR A CALIBRAGEM EM ARQUIVO</b><br>
	 * converter int[] para String no seguinte formato:<br>
	 * 00xxx01xxx02xxx03xxx04xxx05xxx... ate o final do array int[]<br>
	 * Os numeros indicam a numeracao do motor, os "xxx" indicam o valor
	 * do angulo de 0 a 180 do motor que esta contido na posicao do int[] seguinte a sua
	 * numeracao, por exemplo:<br>
	 * motor 1 com 3 graus, motor 10 com 25 graus e motor 17 com 127 graus<br>
	 * @param a Int[] a = {1, 3, 10, 25, 17, 127}
	 * @return "010031002517127"
	 */
	public static String intToString(int[] dados){
		String s = "";
		for(int i = 0; i<dados.length/2;i ++){
			int a = dados[i*2];
			s += a+";";
			
			a = dados[i*2+1];
			s += a+";";
		}
		return s;
	}
	
	/**
	 * <b>METODO USADO PARA CARREGAR A CALIBRAGEM DO ARQUIVO</b><br>
	 * converter String para int[] no seguinte formato:<br>
	 * 00xxx01xxx02xxx03xxx04xxx05xxx... ate o final da String<br>
	 * Os numeros indicam a numeracao do motor, os "xxx" indicam o valor
	 * do angulo de 0 a 180 do motor que esta contido na posicao do int[] seguinte a sua
	 * numeracao, por exemplo:<br>
	 * motor 1 com 3 graus, motor 10 com 25 graus e motor 17 com 127 graus<br>
	 * @param s "010031002517127"
	 * @return  Int[] a = {1, 3, 10, 25, 17, 127}
	 */
	public static int[] stringToInt(String s){
ArrayList<Integer> ii = new ArrayList<Integer>();
		
		String[] tmp = s.split(";");
		for(int t = 0; t < tmp.length; t  ++){
			ii.add( Integer.parseInt(tmp[t])); // lanca excecao se caso a string nao seja um numero
		}
		
		int[] i = new int[ii.size()];
		for(int t = 0; t < i.length; t  ++){
			i[t] = ii.get(t);
		}
		return i;
	}

	
}
