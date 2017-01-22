package organizaArray;

public class Testa {

	public static void main(String[] args){
		OrganizaArray a1 = new OrganizaArray(9,8,7,3,4,5,1,2,6);
		 int[][] a = new int[6][8];
		for(int c = 0 ; c < a1.getQntElementos() ; c++){
			System.out.println(a1.getArray(c));
		}
		System.out.println(a[1].length);
		
	}

}
