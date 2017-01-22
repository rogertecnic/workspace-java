package herança_array;

import javax.swing.JOptionPane;

public abstract class FigPlana {
	private double area, perimetro;
	private double cx, cy;
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getCx() {
		return cx;
	}
	public void setCx(double cx) {
		this.cx = cx;
	}
	
	public double getCy() {
		return cy;
	}
	public void setCy(double cy) {
		this.cy = cy;
	}
	
	public double getArea(){
		return area;
	}
	public void setArea(double area){
		this.area = area;
	}
	
	public double getPerimetro() {
		return perimetro;
	}
	public void setPerimetro(double perimetro) {
		this.perimetro = perimetro;
	}
	
	/**
	 * metodo substitui o JOptionPane.showMessageDiallog para o usuario digitar um inteiro
	 * @param textoDaJanela é o texto que a janela vai mostrar
	 */
	public static int usuarioDigitaInt(String textoDaJanela){
		return Integer.parseInt(JOptionPane.showInputDialog(textoDaJanela));
	}
}