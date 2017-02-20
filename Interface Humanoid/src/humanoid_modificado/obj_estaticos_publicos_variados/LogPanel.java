package humanoid_modificado.obj_estaticos_publicos_variados;

import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class LogPanel{
	private static JPanel panel =null;
	private static JTextArea log = new JTextArea();
	private static JScrollPane scrollPanel = new JScrollPane(log);

	public static JPanel getLogPanel(){
		if(panel == null){
			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.setBorder(new TitledBorder("Log do arduino:"));
			panel.add(scrollPanel);
			panel.add(TodosOsCheckBox.printarDadosRecebidosNoLog());

			//configurando
			scrollPanel.setWheelScrollingEnabled(true);
			scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPanel.getVerticalScrollBar().setAutoscrolls(true);
			scrollPanel.setPreferredSize(new Dimension(300, 100));
			scrollPanel.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
				int logHeigth = 0;
				@Override
				public void adjustmentValueChanged(AdjustmentEvent e) {
					if(log.getHeight() !=logHeigth){
						e.getAdjustable().setValue(e.getAdjustable().getMaximum());
						logHeigth = log.getHeight();
					}
				}
			});
			log.setLineWrap(false);
			log.setEditable(false);


			Thread threadLog = new Thread(new Runnable() {
				String s = "";
				@Override
				public void run() {
					while(true){
						try {
							Thread.sleep(20);
							s = ArduinoPanel.arduino().read();
							if(s != "" && TodosOsCheckBox.printarDadosRecebidosNoLog().isSelected()){
								log.setText(log.getText() + s + "\n");
								s ="";
							}

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});

			threadLog.setDaemon(true);
			threadLog.setName("threadLog");
			threadLog.start();
		}
		return panel;
	}


}
