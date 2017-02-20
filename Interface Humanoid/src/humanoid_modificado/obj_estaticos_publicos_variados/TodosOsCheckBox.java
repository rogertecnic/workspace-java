package humanoid_modificado.obj_estaticos_publicos_variados;

import javax.swing.JCheckBox;

public class TodosOsCheckBox {
	private static JCheckBox enviarDadosArduino = null;
	private static JCheckBox logArduino = null;
	
	// aplicado padrao singleton (singleton pattern)
	public static JCheckBox enviarDadosArduino(){
		if(enviarDadosArduino == null){
		enviarDadosArduino = new JCheckBox("Comumicacao Serial");
		enviarDadosArduino.setSelected(false);
		}
		return enviarDadosArduino;
	}
	
	public static JCheckBox printarDadosRecebidosNoLog(){
		if(logArduino == null){
			logArduino = new JCheckBox("Atualizar log");
			logArduino.setSelected(true);
		}
		return logArduino;
	}
}
