package humanoid;

import java.util.List;

import javax.swing.JSlider;

public class GroupSlider {
	private JSlider slider;
	private ID id;
	
	public void setID(ID _id){
		id = _id;
	}
	
	public ID getID(){
		return id ;
	}
	
	public void setSlider(JSlider _slider){
		slider = _slider;
	}
	
	public JSlider getSlider(){
		return slider;
	}
	
	public void addGroupSlider(List<GroupSlider> list,GroupSlider arg0){
		list.add(arg0);
	}
	
}
