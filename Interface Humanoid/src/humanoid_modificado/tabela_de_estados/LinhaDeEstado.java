package humanoid_modificado.tabela_de_estados;

public class LinhaDeEstado {
	private int numero;
	private int[] dados; // o esquema é assim, cada motor vai ocupar 2 bytes, o primeiro byte eh o id do motor, o segundo eh o valor
	private Boolean excluir = false;
	private String nomeDoEstado;

	public LinhaDeEstado(int numero, int[] dados, String nomeDoEstado){
		this.numero = numero;
		this.dados = dados;
		this.nomeDoEstado = nomeDoEstado;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int[] getDados() {
		return dados;
	}
	

	public void setDados(int[] dados) {
		this.dados = dados;
	}
	
	public Boolean getExcluir(){
		return excluir;
	}
	
	public void setExcluir( Boolean excluir){
		this.excluir = excluir;
	}
	
	public String getNomeDoEstado() {
		return nomeDoEstado;
	}

	public void setNomeDoEstado(String nomeDoEstado) {
		this.nomeDoEstado = nomeDoEstado;
	}
}
