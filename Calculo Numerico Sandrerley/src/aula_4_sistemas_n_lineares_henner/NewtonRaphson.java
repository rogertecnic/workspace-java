package aula_4_sistemas_n_lineares_henner;

//Henner Soares Campos Filho
import javax.swing.JOptionPane;
//sou muito fã dessa biblioteca

public class NewtonRaphson{
	//-----------METODOS USADOS-----------------------------------------------
	public static double f(double[] x){
		return x[1]*x[1]+x[0]*x[0]-4;// a fx[1]*x[1]+x[0]*x[0]-4;
	}

	public static double g(double[] x){
		return x[0]*x[1]-1; //  a gx[0]*x[1]-1
	}

	public static double dfdx1(double[] x){
		double deriv11;
		double h = Math.pow(10, -7); // -7 é o ideal devido as limitações de conta da maquina
		double xx[]=new double[2];
		xx[0]=x[0]+h;  xx[1]=x[1];
		deriv11= (f(xx) - f(x))/h;
		return deriv11;
	}

	public static double dfdx2(double[] x){
		double deriv12;
		double xx[]=new double[2];
		double h = Math.pow(10, -7); // -7 é o ideal devido as limitações de conta da maquina
		xx[0]=x[0];  xx[1]=x[1]+h;
		deriv12= (f(xx) - f(x))/h;
		return deriv12;
	}

	public static double dgdx1(double[] x){
		double deriv21;
		double h = Math.pow(10, -7); // -7 é o ideal devido as limitações de conta da maquina
		double xx[]=new double[2];
		xx[0]=x[0]+h;   xx[1]=x[1];
		deriv21 = (g(xx) - g(x))/h;
		return deriv21;
	}

	public static double dgdx2(double[] x){
		double deriv22;
		double h = Math.pow(10, -7); // -7 é o ideal devido as limitações de conta da maquina
		double xx[]=new double[2];
		xx[0]=x[0]; xx[1]=x[1] +h;
		deriv22 = (g(xx) - g(x))/h;
		return deriv22;
	}
	//------------------------------------------------------------------------

	//---------AQUI COMECA O PROGRAMA---------------------------------------
	public static void main(String[] args){
		JOptionPane.showMessageDialog(null,"Bem vindo ao programa do metodo de Newton-Raphson");


		double x[] = new double[2];
		double dx[]=new double[2];
		x[0]=1.8;
		x[1]=0.5;//onde x1=x[0] e x2=x[1]
		dx[0] = 10;
		dx[1]= 10; // deltas, valores somente para inicializacao para indicar que é um array de 2 posicoes,precisa ser maior q o epsilon
		int contador=1;





		while(dx[0]>0.00001||dx[1]>0.00001){
			System.out.println("Iteracao:" + contador);
			//-------jacobiana----------------------
			double der[][]=new double[2][2];
			der[0][0]=dfdx1(x);
			der[0][1]=dfdx2(x);
			der[1][0]=dgdx1(x);
			der[1][1]=dgdx2(x);
			//--------------------------------------
			System.out.printf("o jacobiano: \n");
			for(int i=0;i<2;i++){
				System.out.printf("\t|");
				for(int j=0;j<2;j++){System.out.printf("%.3f\t|",der[i][j]);}
				System.out.printf("\n");
			}
			contador=contador+1;
			//------jacob inv pronto do enunciado------
			double con;
			for (int k=0; k<2; k++) {
				con = der[k][k];
				der[k][k] = 1;
				for (int j=0; j<2; j++)
					der[k][j] = der[k][j] / con;
				for (int i=0; i<2; i++) {
					if (i!=k) {
						con = der[i][k];
						der[i][k] = 0.0;
						for (int j=0; j<2; j++)
							der[i][j] = der[i][j] - der[k][j]*con;
					}
				}
			}       
			System.out.printf("a inversa do jacobiano: \n");
			for(int i=0;i<2;i++){
				System.out.printf("\t|");
				for(int j=0;j<2;j++){
					System.out.printf("%.3f\t|",der[i][j]);
				}
				System.out.println();
			}

			dx[0]=der[0][0]*f(x)+der[0][1]*g(x);
			dx[1]=der[1][0]*f(x)+der[1][1]*g(x);
			x[0]=x[0]-dx[0];
			x[1]=x[1]-dx[1];
			if(dx[0]<0){dx[0]=-dx[0];}
			if(dx[1]<0){dx[1]=-dx[1];}

			System.out.printf("\nOs dx's:\ndx1=%e\tdx2=%e\n\n",dx[0],dx[1]);
		}
		System.out.printf("\nf(%e ,%e) = %e\n",x[0],x[1],f(x));
		System.out.printf("\nforam precisas %d iteraçoes \n",contador);

		JOptionPane.showMessageDialog(null,"Obrigado pela paciencia!!! eh noix!!!");
	}
}