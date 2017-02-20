package humanoid_original;

import javax.swing.JTextField;

public class Config {
	int id;
	JTextField MAX_S;
	JTextField MIN_S;
	JTextField MAX_T;
	JTextField MIN_T;
	JTextField MAX_A;
	JTextField MIN_A;
	
	
	Config(){
	
	}
	Config(int _id){
		id = _id;
	}
	
	public void setId(int _id){
		id = _id;
	}
	
	public int getId(){
		return id;
	}

	public JTextField getMAX_S() {
		return MAX_S;
	}

	public void setMAX_S(JTextField mAX_S) {
		MAX_S = mAX_S;
	}

	public JTextField getMIN_S() {
		return MIN_S;
	}

	public void setMIN_S(JTextField mIN_S) {
		MIN_S = mIN_S;
	}

	public JTextField getMAX_T() {
		return MAX_T;
	}

	public void setMAX_T(JTextField mAX_T) {
		MAX_T = mAX_T;
	}

	public JTextField getMIN_T() {
		return MIN_T;
	}

	public void setMIN_T(JTextField mIN_T) {
		MIN_T = mIN_T;
	}

	public JTextField getMAX_A() {
		return MAX_A;
	}

	public void setMAX_A(JTextField mAX_A) {
		MAX_A = mAX_A;
	}

	public JTextField getMIN_A() {
		return MIN_A;
	}

	public void setMIN_A(JTextField mIN_A) {
		MIN_A = mIN_A;
	}
		
}
