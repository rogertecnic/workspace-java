package Seguidor_de_Linha;

public class TesteLinha {
	
	public static void main(String[] args) {
		
		Ultrassonic distance = new Ultrassonic();
		Thread tDistance = new Thread(distance);
		tDistance.start();		
		Segue_Linha seguindo = new Segue_Linha();
		
		while(seguindo.getCorD() != seguindo.getCor() && seguindo.getCorE() != seguindo.getCor()){
			if(distance.getDistancia() > 0.1){
				seguindo.segueLinha();
			}
			else if(distance.getDistancia() <= 0.1){
				seguindo.caixa();
			}
		}
		seguindo.boneco();
	}

}
