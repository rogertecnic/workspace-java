package organizaArray;

public class OrganizaArray {
	private int array[], qntElementos, somaTotal = 0;
	private enum Ordem {MENOR_MAIOR, MAIOR_MENOR}; //const. enum (funciona como uma classe)
	private Ordem ordem; //variavel tipo Ordem, não é uma instancia, pode conter valores definidos dentro do enum
	
	/**
	 * 
	 * @param i (sequencia de inteiros)
	 */
	public OrganizaArray(int ...i) {
		array = new int[i.length];
		ordem = Ordem.MENOR_MAIOR;
		qntElementos = array.length;
		for(int c = 0 ; c<array.length; c++){
			array[c] = i[c];
			somaTotal += array[c];}
		
		for(int c = 0 ; c<array.length ; c++){
			for(int c2 = c ; c2<array.length ; c2++){
				if(array[c] >= array[c2]){
					int t = 0;
					t = array[c];
					array[c] = array[c2];
					array[c2] = t;}}}}
	
	public int getSomaTotal(){
		return somaTotal;
	}
	
	public int getQntElementos(){
		return qntElementos;
	}
	
	public Ordem getOrdem(){
		return ordem;
	}
	public int getArray(int a){
		return array[a];
	}
}
