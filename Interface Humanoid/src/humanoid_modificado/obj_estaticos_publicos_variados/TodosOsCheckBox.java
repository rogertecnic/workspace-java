package humanoid_modificado.obj_estaticos_publicos_variados;

import javax.swing.JCheckBox;

public class TodosOsCheckBox {
	private static JCheckBox enviarDadosArduino = null;
	
	// aplicado padrao singleton (singleton pattern)
	public static JCheckBox enviarDadosArduino(){
		if(enviarDadosArduino == null){
		enviarDadosArduino = new JCheckBox("Comumicacao Serial");
		enviarDadosArduino.setSelected(false);
		}
		return enviarDadosArduino;
	}
}
