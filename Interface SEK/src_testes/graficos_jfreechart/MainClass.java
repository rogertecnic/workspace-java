package graficos_jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Exemplo do m�nimo necessario para constru��o de um grafico com a Lib jfreeChart
 * @author Rogerio
 *
 */
public class MainClass {
	public static void main(String[] args){
		XYSeries serieDeDados = new XYSeries("Serie de dados 1");
		XYSeriesCollection colecaoDeSeries = new XYSeriesCollection(serieDeDados);
		
		
		JFreeChart chart = ChartFactory.createXYLineChart("Gr�fico", "LAbel x", "Label y", colecaoDeSeries, PlotOrientation.VERTICAL, true, true, true);
		ChartFrame frame = new ChartFrame("titulo do frame", chart);
		frame.pack();
		frame.setVisible(true);
		
		
		
		for(int i = 0; i< 11; i++){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			serieDeDados.add(i, i*i);
		}
	}
}
