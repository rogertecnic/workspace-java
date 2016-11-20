package supervisor;

import java.awt.BasicStroke;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import controladores.ControleP;
import objetos.Robo;

public class Supervisor {

	private static int iteracoes = 10;
	private static double time = 0.03;
	private static double[] Wd_We = new double[2];
	private static double thetaAlvo = 2;
	private static double V = 0;
	private static double W = 0;

	private static Robo meuRobo;
	private static ControleP controleP;
	private static XYSeries series;

	public static void main(String[] args) {
		meuRobo = new Robo();
		controleP = new ControleP();

		series = new XYSeries("XYGraph");
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		// JFreeChart chart = ChartFactory.createScatterPlot("Gráfico", "eixo
		// x", "eixo y", dataset, PlotOrientation.VERTICAL, true, true, false);
		JFreeChart chart = ChartFactory.createXYLineChart("Controle P", "tempo", "theta", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot categoryplot = (XYPlot) chart.getPlot();
		XYLineAndShapeRenderer lineandshaperenderer = (XYLineAndShapeRenderer) categoryplot.getRenderer();
		lineandshaperenderer.setSeriesStroke(0, new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
				0.0f, new float[] { 1.0f, 1.0f }, 0.0f));

		ChartFrame frame = new ChartFrame("Second", chart);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		for (int i = 0; i < (iteracoes/0.03); i++) {

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			meuRobo.updateState();

			W = controleP.execute(thetaAlvo, meuRobo.getState()[2]);
			Wd_We = convertePara_WdWe(V, W);

			meuRobo.motorDireita.setVelocidade((int) (Wd_We[0] * 360 / (2 * Robo.PI)));
			meuRobo.motorEsquerda.setVelocidade((int) (Wd_We[1] * 360 / (2 * Robo.PI)));

			series.add(time * i, meuRobo.getState()[2]);
			// series.add(time*i, Wd_We[0]);

			System.out.println("X = " + meuRobo.getState()[0]);
			System.out.println("Y = " + meuRobo.getState()[1]);
			System.out.println("theta = " + meuRobo.getState()[2] + "\n\n\n");

			meuRobo.motorDireita.updateTachoTime(time);
			meuRobo.motorEsquerda.updateTachoTime(time);
		}

		
	}

	private static double[] convertePara_WdWe(double V, double W) {
		double Wd = (2 * V + Robo.DISTANCIA_ENTRE_RODAS * W) / (2 * Robo.RAIO);
		double We = (2 * V - Robo.DISTANCIA_ENTRE_RODAS * W) / (2 * Robo.RAIO);

		return new double[] { Wd, We };

	}
}
