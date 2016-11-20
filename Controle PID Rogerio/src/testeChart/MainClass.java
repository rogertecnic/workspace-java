package testeChart;

import java.awt.BasicStroke;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MainClass {
	public static void main(String[] args) {
		XYSeries seriesPVt = new XYSeries("v(t) vs t");
		XYSeries seriesUtPV = new XYSeries("a(t) vs t");
		XYSeries seriesSP = new XYSeries("setpoint v(t)");
		Scanner scan = new Scanner(System.in);
		
		// o controle p: u(t) = Kp*e(t) onde:
		// PV(t): variavel de processo que vc quer controlar (sinal de saida)
		// SP(t): é o sinal que vc quer que PV(t) assuma (setpoint)
		// u(t): é a variavel que deve-se alterar (sinal de controle)
		// Kp: cte de proporcionalidade do controle P
		// e(t): erro, é inversamente ou diretamente proporcional:

		// -->diretamente: PV(t) - SP(t); erro negativo se PV(t) < SP(t);
		// -->inversamente: SP(t) - PV(t); o erro é positivo se PV(t) < SP(t);

		//
		//
		// -----------------elementos do chart PV vs t
		XYSeriesCollection datasetPVt = new XYSeriesCollection(seriesPVt);
		datasetPVt.addSeries(seriesSP);
		JFreeChart chartPVt = ChartFactory.createXYLineChart("controle P velocidade (t) (tempo real)", "tempo (t)", "v(t) setpoint 10", datasetPVt,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot categoryplotPVt = (XYPlot) chartPVt.getPlot();
		XYLineAndShapeRenderer lineandshaperendererPVt = (XYLineAndShapeRenderer) categoryplotPVt.getRenderer();
		lineandshaperendererPVt.setSeriesStroke(0, new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
				0.0f, new float[] { 1.0f, 1.0f }, 0.0f));
		lineandshaperendererPVt.setSeriesStroke(1, new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
				0.0f, new float[] { 1.0f, 1.0f }, 0.0f));

		ChartFrame framePVt = new ChartFrame("Chart grafico", chartPVt);
		framePVt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePVt.pack();
		framePVt.setVisible(true);
		// -----------------elementos do chart PV vs t
		//
		//

		//
		//
		// -----------------elementos do chart u(t) vs t
		XYSeriesCollection datasetUtPV = new XYSeriesCollection(seriesUtPV);
		JFreeChart chartUtPV = ChartFactory.createXYLineChart("controle P aceleracao (t) (tempo real)", "(t)", "a(t)", datasetUtPV,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot categoryplotUtPV = (XYPlot) chartUtPV.getPlot();
		XYLineAndShapeRenderer lineandshaperendererUtPV = (XYLineAndShapeRenderer) categoryplotUtPV.getRenderer();
		lineandshaperendererUtPV.setSeriesStroke(0, new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
				0.0f, new float[] { 1.0f, 1.0f }, 0.0f));

		ChartFrame frameUtPV = new ChartFrame("Chart grafico", chartUtPV);
		frameUtPV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameUtPV.pack();
		frameUtPV.setVisible(true);
		// -----------------elementos do chart u(t) vs t
		//
		//
		float SP = 100;
		float Kp = 8f;
		float Kd = 2f;
		float Ki = 2f;
		float u = 0, e = 0, PV = 0, eAnt = 0, PVAnt = 0;
		float timeInterval = 0.01f;
		// e = SP - PV ; inversamente prop
		// u = Kp*e
		// PV = PV0 + u*t ; lei que associa a variavel de controle com a
		// variavel
		// controlada;
		float I=0,P=0,D=0;
		try {
			Thread.sleep(3000);
			
			for (int t = 0; t <= (60 / timeInterval); t++) {
				seriesSP.add(0.01f*t, SP);
				
				e = SP - PV;
				P = (Kp * e);
				I += e*Ki*timeInterval;
				D = Kd*(e - eAnt)*timeInterval;
				u =  P+D+I;
				PV = PVAnt + u*timeInterval;
				PVAnt = PV;
				eAnt = e;

				System.out.println( "SP: "+SP+ "\nPV: "+ PV +"\nu (P+D): "+u + "\ne: "+e + "\nP: "+P+ "\nI: "+I+"\nD: "+D);

				seriesPVt.add(timeInterval * t, PV);
				seriesUtPV.add(timeInterval * t, u);
				Thread.sleep((long) (timeInterval*1000));
				
				if((t>2/timeInterval) && (t<10/timeInterval ))
					 SP = SP+(float)Math.cos((t - 2/timeInterval)*timeInterval );
				
				if(t==11/timeInterval) SP = 80f;

			}
			
		} catch (InterruptedException err) {
			// TODO Auto-generated catch block
			err.printStackTrace();
		}
	}
}
