/**
 * GUI>graphic user interface, conceitos basicos
 */

package pacote;
import javax.swing.JOptionPane;//package swing  class JOptionPane
class GUIs {//Class auxiliar*************************************************************************

}


public class UsarGUIs{//Main Class*******************************************************************
	public static void main(String[] args){
		String text = JOptionPane.showInputDialog("digite o texto a ser capturado");
		JOptionPane.showMessageDialog(null,"texto capturado:\n"+text);
		
	}
	
}

