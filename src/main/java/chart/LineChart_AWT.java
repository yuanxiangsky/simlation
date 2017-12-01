package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.util.List;

public class LineChart_AWT extends ApplicationFrame
{
    public LineChart_AWT( String applicationTitle , String chartTitle, List<Double> result )
    {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Times","Trust",
                createDataset(result),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private DefaultCategoryDataset createDataset(List<Double> array)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        dataset.addValue( 15 , "123" , "1970" );
        dataset.addValue( 30 , "1223" , "1980" );
        dataset.addValue( 60 , "1223" ,  "1990" );
        dataset.addValue( 120 , "123" , "2000" );
        dataset.addValue( 240 , "123" , "2010" );
        dataset.addValue( 300 , "123" , "2014" );
        return dataset;
    }
    public static void main( String[ ] args )
    {
/*        LineChart_AWT chart = new LineChart_AWT(
                "School Vss Years" ,
                "Numer of Schools vs years");

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );*/
    }
}