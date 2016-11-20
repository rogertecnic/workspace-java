package pacote;
/**
 * primeira classe determina os calculos
 */

import java.util.Scanner;
class Calculos{
    
    public static double soma(float a, float b){
        return a+b;
    }
    public  static double sub(float a, float b){
        return a-b;
    }
    public  double mult(float a, float b){
        return a*b;
    }
    public  static double div(float a, float b){
        if(b!=0) return a/b;
        else return 0;
    }
    //*****************************************************************************************************
    public static void main(String[] args){
        while(true){
            Scanner in = new Scanner(System.in);
            Calculos c = new Calculos();
            //if(
            System.out.println("digite os numeros:");
            System.out.println();
        }
    }
}