package humanoid_original;

import java.util.ArrayList;
import java.util.List;

public class ListGroupSlider {
	List<GroupSlider> list = new ArrayList<GroupSlider>();
	
	public void addGroupSlider(GroupSlider arg0){
		list.add(arg0);
	}
	
	public void removeGroupSlider(GroupSlider arg0){
		list.remove(arg0);
	}
	
	public List<GroupSlider> getList(){
		return list;
	}
}
