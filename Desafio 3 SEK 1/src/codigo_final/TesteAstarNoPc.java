package codigo_final;

import java.util.ArrayList;
import java.util.List;

public class TesteAstarNoPc {

	public static void main(String[] args) {
/**		Celula[][] mapa = new Celula[3][3];
		List<Celula> path = null;
		Astar astar = new Astar(mapa);
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				mapa[i][j] = new Celula(0, false, new Posicao(i, j), false);
			}
		}
		
		try {
			path = astar.search(new Posicao(0, 0), new Posicao(2, 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Celula proximaCelula = null;
		int indexPath = 0;
		int direcao;
		while(!path.isEmpty()){
			
			proximaCelula = path.get(0);
			path.remove(0);
			indexPath++;
			System.out.println(proximaCelula.posicao.toString());
				
		}
		
		System.out.println("\n\n");
		
	*/	
		
		
		
		
		
		
		Celula[][] mapa = new Celula[5][5];
		// listaMapeamento é a lista que define a ordem das celulas
		// de mapeamento, a lista é assim:
		// [0,0] [0,1] [0,2] [0,3] [0,4] [1,4] [1,3] [1,2] [1,1] [1,0]
		// [2,0] [2,1] [2,2] [2,3] [2,4] [3,4] [3,3] [3,2] [3,1] [3,0]
		// [4,0] [4,1] [4,2] [4,3] [4,4] essa ultima estará o boneco
		List<Celula> listaMapeamento = new ArrayList<Celula>();
		//List<Celula> path = null;
		//Astar astar = new Astar(mapa);
		
		// cria o mapa livre e a listaMapeamento assim:
		//[0,0] [0,1] [0,2] [0,3] [0,4] [0,0] [0,1] [0,2] [0,3] [0,4]  
		//public Mapeamento() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				mapa[i][j] = new Celula(0, false, new Posicao(i, j), false);

			}
		}
		int t = 0;
			 // cria a lista na ordem do mapeamento
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if ((i % 2) == 0)
					listaMapeamento.add(mapa[i][j]);
				else
					listaMapeamento.add(mapa[i][4 - j]);
				System.out.println(listaMapeamento.get(t).posicao.toString());
				t++;
			}
			
		}
			
			
/**			while(!listaMapeamento.isEmpty()){				
				//System.out.println(listaMapeamento.size());
				System.out.println(listaMapeamento.get(i).posicao.toString());
				listaMapeamento.remove(i);
				i++;
			}
*/		}
	//}

}
