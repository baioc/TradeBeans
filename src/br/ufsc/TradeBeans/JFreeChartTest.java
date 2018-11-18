package br.ufsc.TradeBeans;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;


public class JFreeChartTest extends JFrame implements ActionListener {

    private TimeSeries series;
    private double lastValue = 100.0;


    public JFreeChartTest(final String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.series = new TimeSeries("Data");
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);
        final JButton button = new JButton("Add Random");
        button.setActionCommand("ADD_DATA");
        button.addActionListener(this);

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(chartPanel);
        panel.add(button, BorderLayout.SOUTH);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);
    }


    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Ativo financeiro",
            "Datas", "Valores",
            dataset,
            false, false, false
        );
        final XYPlot plot = chart.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000.0);  // 60 seconds
        axis = plot.getRangeAxis();
        axis.setRange(0.0, 200.0);
        return chart;
    }

    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals("ADD_DATA")) {
            final double factor = 0.90 + 0.2 * Math.random();
            this.lastValue = this.lastValue * factor;
            final Millisecond now = new Millisecond();
            System.out.println("Now = " + now.toString());
            this.series.add(new Millisecond(), this.lastValue);
        }
	}


    public static void main(final String[] args) {
        final JFreeChartTest demo = new JFreeChartTest("Dynamic JFreeChart Demo");
        demo.pack();
        demo.setVisible(true);
    }

}
