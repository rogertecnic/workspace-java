
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Astar {
	/**
	 * @mapa grade 2d de Celulas
	 */
	Celula[][] mapa;
	ArrayList<Celula> listaAberta = new ArrayList<Celula>();
	ArrayList<Celula> listaFechada = new ArrayList<Celula>();

	Astar(Celula[][] mapa) {
		this.mapa = mapa;
		listaAberta = new ArrayList<Celula>();
		listaFechada = new ArrayList<Celula>();
	}

	/**
	 * M�todo A* para busca de caminho mais pr�ximo
	 * @param inicio posi��o inicial
	 * @param fim posi��o final
	 * @return lista de posi��es para caminho mais pr�ximo
	 * @throws PathNotFoundException caminho n�o encontrado
	 */
	public List<Celula> search(Posicao inicio, Posicao fim) throws Exception {
		//Apagam os valores calculados do mapa e as listas
		apagarMap(); 
		listaAberta.clear();
		listaFechada.clear();
		//------------------------------------------------
		
		listaAberta.add(mapa[inicio.x][inicio.y]);
		
		while (listaAberta.size() > 0) {
			// Pega o indice da celula de menor custo f
			//para celular de f iguais, a primeira a vir na lista aberta 
			// que � mantida
			int indiceMenor = 0; //inicializa o indice da celula de menor f
			
			for (int i = 0; i < listaAberta.size(); i++) {
				if (listaAberta.get(i).f < listaAberta.get(indiceMenor).f) {
					indiceMenor = i;
				}
			}
			
			Celula celulaAtualfMenor = listaAberta.get(indiceMenor);
			
			//verifica se a celula com o menor f da lista aberta � a celula de fim
			// Fim -- o resultado foi encontrado, retorne o caminho encontrado
			if (celulaAtualfMenor == mapa[fim.x][fim.y]) {
				Celula atual = celulaAtualfMenor;
				Stack<Celula> ret = new Stack<Celula>();
				while (atual.pai != null) {
					ret.push(atual);
					atual = atual.pai;
				}
				System.out.println("FIM");
				return reverse(ret);
			}
			
			// celulaAtualfMenos nao � a celula de fim 
			// mude a celula atual da lista aberta para a fechada
			listaAberta.remove(celulaAtualfMenor);
			listaFechada.add(celulaAtualfMenor);
			
			// chama o metodo vizinhos para criar uma lista de vizinhos
			// da celula atual que foi movida para a lista fechada
			List<Celula> vizinhos = vizinhos(celulaAtualfMenor);
			
			for (int i = 0; i < vizinhos.size(); i++) {
				Celula vizinho = vizinhos.get(i);
				if (vizinho.isOccupied()  || listaFechada.contains(vizinho)) {
					// n�o � um n� v�lido, pule para o proximo vizinho
					continue;
				}
				
				// g � a menor distancia do come�o at� o n� atual,
				// precisamos checar se o caminho que chegamos at� esse vizinho � o menor
				// que vimos at� agora
				int pontoG = celulaAtualfMenor.g + 1; // 1 � a distancia do n� at� o seu vizinho
				boolean pontoGmelhor = false;

				if (!listaAberta.contains(vizinho)) {
					// Essa � a primeira vez que chegamos a esse n�, ele deve ser o melhor
					// Tamb�m devemos somar com h (heuristica) j� que n�o fizemos isso ainda
					pontoGmelhor = true;
					vizinho.h = heuristica(vizinho.getPosicao(), fim);
					listaAberta.add(vizinho);
				} else if (pontoG < vizinho.g) {
					// J� vimos esse n�, mas da ultima vez tinha um pior g
					pontoGmelhor = true;
				}

				if (pontoGmelhor) {
					// Encontrou o caminho at� agora para esse n�. Guarda a informa��o
					// de como chegou aqui e o qu�o bom �
					vizinho.pai = celulaAtualfMenor;
					vizinho.g = pontoG;
					vizinho.f = vizinho.g + vizinho.h;
				}
			}
		}
		throw new Exception();
	}
	
	
	public List<Celula> searchReversePath(Posicao inicio, Posicao fim) throws Exception{
		

		//Apagam os valores calculados do mapa e as listas
		apagarMap(); 
		listaAberta.clear();
		listaFechada.clear();
		//------------------------------------------------
		
		listaAberta.add(mapa[inicio.x][inicio.y]);
		while (listaAberta.size() > 0) {
			
			// Pega o menor f(x) para ir para o proximo
			int indiceMenor = 0;
			for (int i = 0; i < listaAberta.size(); i++) {
				if (listaAberta.get(i).f < listaAberta.get(indiceMenor).f) {
					indiceMenor = i;
				}
			}
			Celula noAtual = listaAberta.get(indiceMenor);


			// Fim -- o resultado foi encontrado, retorne o caminho encontrado
			if (noAtual == mapa[fim.x][fim.y]) {
				Celula atual = noAtual;
				Stack<Celula> ret = new Stack<Celula>();
				while (atual.pai != null) {
					
					ret.push(atual);
					atual = atual.pai;
				}
				System.out.println("FIM");
				return reverse(ret);
			}

			// Caso normal -- mude o CurrentNode da lista aberta para a fechada para 
			// cada um dos vizinhos
			listaAberta.remove(noAtual);
			listaFechada.add(noAtual);
			
			// chama o metodo vizinhos que cria uma
			List<Celula> vizinhos = vizinhos(noAtual);

			for (int i = 0; i < vizinhos.size(); i++) {
				Celula vizinho = vizinhos.get(i);
				if (vizinho.isOccupied()  || listaFechada.contains(vizinho)|| !vizinho.isChecked()) {
					// n�o � um n� v�lido, pule para o proximo vizinho
					continue;
				}

				// g � a menor distancia do come�o at� o n� atual,
				// precisamos checar se o caminho que chegamos at� esse vizinho � o menor
				// que vimos at� agora
				int pontoG = noAtual.g + 1; // 1 � a distancia do n� at� o seu vizinho
				boolean pontoGmelhor = false;

				if (!listaAberta.contains(vizinho)) {
					// Essa � a primeira vez que chegamos a esse n�, ele deve ser o melhor
					// Tamb�m devemos somar com h (heuristica) j� que n�o fizemos isso ainda
					pontoGmelhor = true;
					vizinho.h = heuristica(vizinho.getPosicao(), fim);
					listaAberta.add(vizinho);
				} else if (pontoG < vizinho.g) {
					// J� vimos esse n�, mas da ultima vez tinha um pior g
					pontoGmelhor = true;
				}

				if (pontoGmelhor) {
					// Encontrou o caminho at� agora para esse n�. Guarda a informa��o
					// de como chegou aqui e o qu�o bom �
					vizinho.pai = noAtual;
					vizinho.g = pontoG;
					vizinho.f = vizinho.g + vizinho.h;
				}
			}
		}
		throw new Exception();
	}

	/**
	 * @param stack pilha
	 * @return retorna a pilha em ordem reversa na forma de lista
	 */
	private List<Celula> reverse(Stack<Celula> stack) {
		ArrayList<Celula> listaReversa = new ArrayList<Celula>();
		while(!stack.empty()){
			listaReversa.add(stack.pop());
		}
		return listaReversa;
	}

	/**
	 * 
	 * @param no celula a ser criado os vizinhos
	 * @return retorna uma lista de c�lulas vizinhas a celula passada como parametro
	 */
	private List<Celula> vizinhos(Celula no) {
		ArrayList<Celula> ret = new ArrayList<Celula>();
		Posicao posicao = no.getPosicao();
		if(posicao.x>0){
			ret.add(mapa[posicao.x - 1][posicao.y]);
		}
		if(posicao.x<mapa.length-1){
			ret.add(mapa[posicao.x + 1][posicao.y]);
		}
		if(posicao.y>0){
			ret.add(mapa[posicao.x][posicao.y - 1]);

		}
		if(posicao.y<mapa.length-1){
			ret.add(mapa[posicao.x][posicao.y + 1]);
		}
		return ret;
	}

	/**
	 * @param p0
	 * @param p1
	 * @return retorna a distancia(Manhattan) do ponto p0 ao ponto p1 
	 */
	private int heuristica(Posicao p0, Posicao p1) {
		int d1 = Math.abs(p1.x - p0.x);
		int d2 = Math.abs(p1.y - p0.y);
		return d1 + d2;
	}
	
	/**
	 * apaga os valores calculados do mapa
	 */
	private void apagarMap(){
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa.length; j++) {
				mapa[i][j].apagar();
			}
		}
	}
	


}