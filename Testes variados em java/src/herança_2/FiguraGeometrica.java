package herança_2;

public abstract class FiguraGeometrica {
	private String nome;
	private double cx,cy, cz;

	public FiguraGeometrica(String n, double cx, double cy, double cz){
		nome = n;
		this.cx = cx;
		this.cy = cy;
		this.cz = cz;}


	public String getNome(){
		return nome;}
	
	public double getCentrox(){
		return cx;}
	
	public double getCentroy(){
		return cy;}
	
	public double getCentroz() {
		return cz;
	}
	
	public void setNome(String n){
		nome = n;}
	
	public void setCentrox(double cx){
		this.cx = cx;}
	
	public void setCentroy(double cy){
		this.cy = cy;}
	
	public void setCentroz(double cz) {
		this.cz = cz;
	}
	
	public abstract double getArea();
	public abstract double getVolume();
}
