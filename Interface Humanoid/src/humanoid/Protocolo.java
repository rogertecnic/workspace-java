package humanoid;

import java.util.List;

import javax.swing.JTextArea;

public class Protocolo {
	
	public byte[] addProtocolo(String source, int id){
	  StringBuffer sb = new StringBuffer();
  	  sb.append("I");
  	  sb.append("[M]");
  	  sb.append("[00]");
  	  
  	  sb.append("[");
  	  if(id <10)
  		  sb.append("0");	  
  	  
  	  sb.append(id+"");
  	  sb.append("]");
  	  sb.append("[");
  	  
  	  if(Integer.valueOf(source).intValue()<10)
  		sb.append("00");
  	  else if(Integer.valueOf(source).intValue()<100)
  		sb.append("0");
  	  
  	  sb.append(source);
  	  
  	  sb.append("]");
  	  sb.append("F");
  	  
  	if(Flags.CHECKBOX_LOG.isSelected()){
		//Flags.LOG.append("-----------------------------"+"\n");
		 Flags.LOG.append("E: "+sb.toString()+"\n");
	 }
  	  
   	  return sb.toString().getBytes();
	}
	
	
	public boolean verificarMsg(char [] buffer) {

		 	  if (buffer[0] != 'I')
			    return false;
			  if (buffer[1] != '[')
			    return false;
			  if (buffer[3] != ']')
			    return false;
			  if (buffer[4] != '[')
			    return false;
			  if (buffer[7] != ']')
			    return false;
			  if (buffer[8] != '[')
			    return false;
			  if (buffer[11] != ']')
			    return false;
			  if (buffer[12] != '[')
			    return false;
			  if (buffer[16] != ']')
			    return false;
			  if (buffer[17] != 'F')
			    return false;

			  return true;
		}
	
	
	public byte[] addProtocoloConfig(int id,String maxS,String minS,String maxT,String minT,String maxA,String minA){
		  StringBuffer sb = new StringBuffer();
	  	  sb.append("C");
	  	  sb.append("[");
	  	  
	  	  if(id <10)
	  		  sb.append("0");	  
	  	  
	  	  sb.append(id+"");
	  	  sb.append("]");
	  	  sb.append("[");
	  	  
	  	  if(Integer.valueOf(maxS).intValue()<10)
	  		sb.append("00");
	  	  else if(Integer.valueOf(maxS).intValue()<100)
	  		sb.append("0");
	  	  
	  	  sb.append(maxS);	  	  
	  	  sb.append("]");
	  	  
	  	 if(Integer.valueOf(minS).intValue()<10)
		  		sb.append("00");
		  	  else if(Integer.valueOf(minS).intValue()<100)
		  		sb.append("0");
		  	  
		  	  sb.append(minS);	  	  
		  	  sb.append("]");
	  	  
		  	 if(Integer.valueOf(maxT).intValue()<10)
			  		sb.append("00");
			  	  else if(Integer.valueOf(maxT).intValue()<100)
			  		sb.append("0");
			  	  
			  	  sb.append(maxT);	  	  
			  	  sb.append("]");
		  	  
			  	  
			  	 if(Integer.valueOf(minS).intValue()<10)
				  		sb.append("00");
				  	  else if(Integer.valueOf(minS).intValue()<100)
				  		sb.append("0");
				  	  
				  	  sb.append(minS);	  	  
				  	  sb.append("]");
			  	  
				  	 if(Integer.valueOf(maxA).intValue()<10)
					  		sb.append("00");
					  	  else if(Integer.valueOf(maxA).intValue()<100)
					  		sb.append("0");
					  	  
					  	  sb.append(maxA);	  	  
					  	  sb.append("]");
					  	  
					  	 if(Integer.valueOf(minS).intValue()<10)
						  		sb.append("00");
						  	  else if(Integer.valueOf(minS).intValue()<100)
						  		sb.append("0");
						  	  
						  	  sb.append(minS);	  	  
						  	  sb.append("]");
				  	  
	  	  sb.append("F");
	  	  
	  	if(Flags.CHECKBOX_LOG.isSelected()){
			//Flags.LOG.append("-----------------------------"+"\n");
			 Flags.LOG.append("E: "+sb.toString()+"\n");
		 }
	  	  
	   	  return sb.toString().getBytes();
		}
	
}
