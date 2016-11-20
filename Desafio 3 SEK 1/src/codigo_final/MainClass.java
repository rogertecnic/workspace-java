package codigo_final;

import java.util.List;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class MainClass {
	public static void main(String[] args) {
		// navegador
		Navegacao nav = new Navegacao();
		// mapa
		Celula[][] mapa = new Celula[5][5];
		// path, caminho
		List<Celula> path = null;
		// Astar
		Astar astar = new Astar(mapa);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				mapa[i][j] = new Celula(0, false, new Posicao(i, j), false);
			}
		}
		
		Mapeamento mapeador = new Mapeamento();
		try {
			path = mapeador.listaMapeamento;
			// path = astar.search(new Posicao(0, 0), new Posicao(4, 4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Celula proximaCelula = null;
		int direcao;
		
		
		mapeador.mapear();
/**		while (!path.isEmpty()) {
			
			proximaCelula = path.get(0);
			path.remove(0);
			if ((proximaCelula.posicao.x != nav.posicaoAtual.x) || (proximaCelula.posicao.y != nav.posicaoAtual.y)) {
				direcao = nav.posicaoAtual.orientacao;
				// orientar o robo na direção certa da prox celula
				if (nav.posicaoAtual.x < proximaCelula.posicao.x)
					direcao = Posicao.LESTE;
				else if (nav.posicaoAtual.x > proximaCelula.posicao.x)
					direcao = Posicao.OESTE;
				else if (nav.posicaoAtual.y < proximaCelula.posicao.y)
					direcao = Posicao.NORTE;
				else if (nav.posicaoAtual.y > proximaCelula.posicao.y)
					direcao = Posicao.SUL;

				nav.girar(direcao);
				nav.andar();
				if ((nav.giroAposAlinhar > 4) && (nav.movAposAlinhar > 3))
					nav.alinhar();
			}

		}
*/
		Button.waitForAnyPress();
	}

}
