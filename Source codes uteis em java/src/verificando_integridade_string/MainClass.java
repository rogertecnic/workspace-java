package verificando_integridade_string;

public class MainClass {
	public static void main(String[] args){
		String str = "";
		
		str = "�ma string lindona � mesmo!!";
		
		
		int strLengh = str.length();
		for(int i = 0; i<strLengh; i++){
			
		}
		byte[] b = str.getBytes();
		int lengh = b.length;
		System.out.println((int)'�');
		System.out.println('a' == 'b');
	}
}
