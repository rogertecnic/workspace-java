package codigo_final;

import java.util.ArrayList;
import java.util.List;

public class Mapeamento {
	private static final Navegacao nav = new Navegacao();
	public Celula[][] mapa = new Celula[5][5];
	private DetectaObstaculo obstaculo = new DetectaObstaculo();
	// listaMapeamento é a lista que define a ordem das celulas
	// de mapeamento, a lista é assim:
	// [0,0] [0,1] [0,2] [0,3] [0,4] [1,4] [1,3] [1,2] [1,1] [1,0]
	// [2,0] [2,1] [2,2] [2,3] [2,4] [3,4] [3,3] [3,2] [3,1] [3,0]
	// [4,0] [4,1] [4,2] [4,3] [4,4] essa ultima estará o boneco
	public static List<Celula> listaMapeamento = new ArrayList<Celula>();
	private static List<Celula> path = null;
	private Astar astar = new Astar(mapa);

	// cria o mapa livre e a listaMapeamento assim:
	// [0,0] [0,1] [0,2] [0,3] [0,4] [0,0] [0,1] [0,2] [0,3] [0,4]
	public Mapeamento() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				mapa[i][j] = new Celula(0, false, new Posicao(i, j), false);

			}
		}
		// cria a lista na ordem do mapeamento
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if ((i % 2) == 0)
					listaMapeamento.add(mapa[i][j]);
				else
					listaMapeamento.add(mapa[i][4 - j]);
			}
		}

	}

	/**
	 * Metodo que mapeia
	 */
	public void mapear() {
		listaMapeamento.remove(24);
		listaMapeamento.remove(0);
		boolean bloqueado = false;
		while (!listaMapeamento.isEmpty()) {
			bloqueado = obstaculo.verificaObstaculo();

			if (bloqueado) {
				listaMapeamento.remove(0);

			} else {
				
				nav.andarCelula(listaMapeamento.get(0));
				listaMapeamento.remove(0);
			}

		}
	}

	
}
