package aula_3_bissecao;

public class MainClass {
	// funcao do exercicio 1
	private static double f1(double x){
		return x*Math.log(x)-1;
	}

	// funcao do exercicio 2
	private static double f2(double x){
		return (x*x*x)-x-1;
	}

	//exercicio 1
	private static void exercicio1(){
		double
		a = 1, // a raiz esta antes do 2 que é o valor do exericcio (dominio sao os reais maiores que 0)
		b =  3,
		tol = 0.002,
		x;
		if(f1(a)*f1(b)<0){
			do{// loop do metodo da bissecao
				x=(a+b)/2;
				if(f1(b)*f1(x)>0){
					b=x;
				}else a=x;
			}while((((b-a)/2)>tol)	||	(Math.abs(f1(x))>tol));
			
			System.out.println("\tf1(" + x + ")=" + f1(x));
		}else System.out.println("nao ha raiz no intervalo a b ou ha um numero par de raizes.");
	}
	
	private static void exercicio2(){
		double
		a = -1,
		b =  5,
		tol = 0.0001,
		x;
		if(f2(a)*f2(b)<0){
			do{// loop do metodo da bissecao
				x=(a+b)/2;
				if(f2(b)*f2(x)>0){
					b=x;
				}else a=x;
			}while((((b-a)/2)>tol)	||	(Math.abs(f2(x))>tol));
			System.out.println("\tf1(" + x + ")=" + f2(x));
		}else System.out.println("nao ha raiz no intervalo a b ou ha um numero par de raizes.");
	}
	
	
	public static void main(String[] args) {
		System.out.println("Exercicio 1:");
		exercicio1();
		System.out.println("Exercicio 2:");
		exercicio2();
	}

}
