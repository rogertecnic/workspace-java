/**
 * classe onde tem conceitos importantes de OO
 */

package pacote;

import java.util.Scanner;//classe de leitura de dados
class Pessoa{//classe que pode ser declarada em outro arquivo com o modificador public******************
    /**
     * variaveis atributos de obj. chamadas campo,
     * podem ser private ou não, tipo private so são acessadas por metodos
     * declarados dentro dessa classe, isso se chama encapsulamento
     *///***************************************************
    public int idade; 
    private String nome;
    //******************************************************
    /**
     * Metodos construtores n sao declarados com nenhum modificador, somente public
     * sobreposição de metodos (overloading), metodos com mesmo nome mas
     * lista de parâmetros diferentes
     *///***************************************************
    public Pessoa(){//construtor
    	idade=0;
    	nome=null;
    }
    public Pessoa(int idade, String nome){//construtor
    	this.idade=idade;//this.var qndo se faz ref a var de instancia e nao a var local
    	this.nome=nome;//this.var qndo se faz ref a var de instancia e nao a var local
    }
    public void setnome(String nome){//set usado por convenção, var nome definido como parametro
          this.nome = nome; //this.var qndo se faz ref a var de instancia e nao a var local
    }
    public void getnome(){//get usado por convenção
        System.out.println("nome atual é: "+nome);
    }
    //*******************************************************
}


public class Revisao{ //classe principal****************************************************************

	public static void main(String[] args){//metodo principal, metodos static nao precisam de um obj
		/**
         * cria uma var tipo Pessoa nome "p1" que recebe o endereço da memoria
         * que contem uma instância da classe Pessoa
         * construtor: Pessoa();
         */
        Pessoa p1 = new Pessoa();
        
        p1.setnome("tiagaoo");//obj:p1 metodo:setnome(recebe a string "tiagaoo")
        p1.getnome();
        System.out.println("digite o novo nome:");
        /**
         * cria uma var tipo Scanner nome "entrada" que recebe o endereço da memoria
         * que contem uma instância da class Scanner
         * construtor: Scanner(class:System campo:in)
         */
        Scanner entrada = new Scanner(System.in);
        
        p1.setnome(entrada.next());//obj:p1 metodo:setnome(recebe a primeira palavra digitada)
        p1.getnome();
        System.out.println("Sua idade é:" + p1.idade + "\nDigite nova idade:");
        p1.idade = entrada.nextInt();
        System.out.println("Sua nova idade é:" + p1.idade + " anos");
    }
}  