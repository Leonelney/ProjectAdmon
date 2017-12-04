package Sources;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JFrame;
 
 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafica {
    XYSeries cFijos = new XYSeries("Costos Fijos");
    XYSeries ventas = new XYSeries("Ventas");
    XYSeries cTotales = new XYSeries("Costos Totales");
    
    public void graficarCostosFijos(double x, double y, double x2){
        cFijos.add(x, y);
        cFijos.add(x2, y);
    }
    
    //En esta grafica x es la cantidad de unidades con aumentos vendidas en el anio ObtenerUniVen
    //Por otro lado y es el precio de todas las unidades vendidas
    public void graficarVentas(double x, double y){
        ventas.add(0.0, 0.0);
        ventas.add(x, y);
    }
    
    //En esta grafica x es la cantidad de unidades con aumentos vendidas en el anio ObtenerUniVen
    //Por otro lado y es el costo fijo
    //Por otro lado y2 es la cantidad de costos totales que se generan en el anio
    public void graficarCostosTotales(double x, double y, double y2){
        ventas.add(0.0, y);
        ventas.add(x, y2);
    }
    
    public XYSeriesCollection crearColeccionDatos(){
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(cFijos);
        dataset.addSeries(ventas);
        dataset.addSeries(cTotales);
        return dataset;
    }
    
    public JFreeChart crearGrafica(XYSeriesCollection dataset){
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
            "Punto de equilibrio",
            "Unidades",
            "Cantidad monetaria",
            dataset,
            PlotOrientation.VERTICAL, true, true, false);
        return xylineChart;
    }
    
    public void dibujarDatos(JFreeChart xylineChart){
        XYPlot plot = xylineChart.getXYPlot();
               
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
               
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.CYAN);
        //Grosores de pincel
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        ChartPanel panel = new ChartPanel(xylineChart);
 
        // Creamos la ventana
        JFrame ventana = new JFrame("Grafica");
        ventana.setVisible(true);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        ventana.add(panel);
    }
}
