package aula_3_newton_rapshon;

public class MainClass {
	// funcao do exercicio 1
	private static double f1(double x){
		return x*Math.log(x)-1;
	}

	// primeira derivada do exercicio 1
	private static double fl1(double x){
		return (x*x*x)-x-1;
	}

	//exercicio 1
	private static void exercicio1(){
		boolean deuruim = false;
		double
		x0 = 2, // ponto de teste atual (primeira iteracao)
		x1, // ponto de teste da ultima iteracao
		x2, // ponto de teste da penultima iteracao
		tol = 0.002;
		
		do{
			x1 = x0-f1(x0)/fl1(x0);
			x2 = x1-f1(x1)/fl1(x1);
			if((x0-x1)/(x1-x2) > 1){
				x0 = x2;
			}else{
				deuruim = true;
				System.out.println("a funcao divergiu (afastou da raiz");
				break;
			}
		}while((Math.abs(f1(x1))>tol) || Math.abs((x2-x1)/x2)>tol);
		if(!deuruim) System.out.println("f1(" + x1 + ")=" + f1(x1));
	}

	public static void main(String[] args) {
		System.out.println("Exercicio 1:");
		exercicio1();
	}

}
