package Autovetores_matriz;
import java.lang.reflect.Array;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Autovalores {
	public static float[][] A = new float[3][3];
	public static float[][] Q = new float[3][3];
	public static float[][] R = new float[3][3];
	public static float e=0.001f;

	public static void main(String[] args){
		JOptionPane.showMessageDialog(null,"Bem vindo ao programa de iteracao de Francis");
		A[0][0]=5;	A[0][1]=2;	A[0][2]=1; 
		A[1][0]=2;	A[1][1]=3;	A[1][2]=1;
		A[2][0]=1;	A[2][1]=1;	A[2][2]=1;

		/*A[0][0]=2;	A[0][1]=1;	A[0][2]=1; // results: 3,1,-1
		A[1][0]=2;	A[1][1]=3;	A[1][2]=4;
		A[2][0]=-1;	A[2][1]=-1;	A[2][2]=-2;*/
		
		int it = 0;
		while(A[1][0]>e || A[2][0]>e || A[2][1]>e){
			it++;
			// A = QR, vamos achar R e Q, pra isso temos que encontrar as matrizes de rotacao U[i,j]
			// for para encontrar Q e R
			Q = new float[][] {{1,0,0},{0,1,0},{0,0,1}};
			R = A;
			for(int i=2;i<=3;i++){
				for (int j=1;j<i;j++){
					float[][] U = acharUqp(i, j);
					R = produtoMatriz(U, R);
					Q = produtoMatriz(Q, TransportadorU(U));
				}
			}
			A = produtoMatriz(R, Q);
		}
		System.out.println("Resultado:");
		printMatriz(A);
		System.out.println("precisou de "+it+" iteracoes para esse resultado.");
	}

	/**
	 * Calcula a matriz rotacional U referente a zerar o elemento de indice [q][p] da matriz A (a matriz pela qual se vai multiplicar
	 * a matriz U, U*A);
	 * ATENCAO, usar os indices reais, que iniciam com 1, nao os indices do java que iniciam com 0;
	 * @param q indice da linha do elemento a zerar (inicia em 1);
	 * @param p indice da culuna do elemento a zerar (inicia em 1);
	 * @return a matriz U inteira float[][];
	 */
	public static float[][] acharUqp(int q, int p){
		float[][] U = {{1,0,0},{0,1,0},{0,0,1}};
		float app = R[p-1][p-1];
		float aqp = R[q-1][p-1];
		U[q-1][q-1] = app/(float)Math.sqrt((app*app+aqp*aqp));
		U[p-1][p-1] = U[q-1][q-1];

		U[p-1][q-1] = aqp/(float)Math.sqrt((app*app+aqp*aqp));
		U[q-1][p-1] = -U[p-1][q-1];

		return U;
	}

	public static float[][] produtoMatriz(float A[][],float B[][]){
		float[][] C= new float[3][3];
		int i,j,k;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				C[i][j] = 0.0f;
			}
		}
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				for (k=0;k<3;k++){
					C[i][j]+=(A[i][k]*B[k][j]);

				}

			}
		}
		return C;
	}

	public static float[][] TransportadorU(float[][] U){
		float[][] C = new float[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				C[i][j] = U[i][j];
			}
		}

		for(int i=1;i<3;i++){
			for(int j=0;j<i;j++){
				if(i!=j){
					float c = C[i][j];
					C[i][j]=C[j][i];
					C[j][i] = c;
				}
			} 
		} 
		return C;
	}

	public static void printMatriz(float[][] A){
		System.out.println("|"+ A[0][0] +"\t"+ A[0][1] +"\t"+ A[0][2] +"|\t\n" +
				"|"+ A[1][0] +"\t"+ A[1][1] +"\t"+ A[1][2] +"|\t\n" +
				"|"+ A[2][0] +"\t"+ A[2][1] +"\t"+ A[2][2] +"|\t\n");
	}
}
