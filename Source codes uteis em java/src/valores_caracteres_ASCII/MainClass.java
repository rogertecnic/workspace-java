package valores_caracteres_ASCII;

public class MainClass {
	public static void main(String[] args){
		int i = -256;
		String a = "";
		System.out.println(a+";"+a.length() + ";" + a.getBytes().length);
		while(i<=256){
			System.out.println(i + ";" + Integer.toBinaryString(i)+ ";"+ (char)i + ";" + Integer.toHexString(i));
			i++;
		}
	}
}
