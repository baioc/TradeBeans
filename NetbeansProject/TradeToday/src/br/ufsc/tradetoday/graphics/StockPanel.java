package br.ufsc.tradetoday.graphics;

import javax.swing.JFrame;

import java.util.Map;
import java.math.BigDecimal;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Day;

import br.ufsc.tradetoday.backend.AlphaVantageAPI;


public class StockPanel {

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * Build a new TimeSeries from stock data.
     * @param stock -- a Map with date-stamp Strings as keys and value Strings as values.
     * @param name -- TimeSeries' name.
     * @return generated TimeSeries.
     */
    private static TimeSeries timeSeriesFromStock(final Map<String, String> stock, final String name) {
        TimeSeries series = new TimeSeries(name);

        for (String stamp : stock.keySet()) {
            BigDecimal value = new BigDecimal( stock.get(stamp) );

            Date time = null;
            try {
                time = FORMAT.parse(stamp);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            series.add(new Day(time), value);
        }

        return series;
    }


    /**
     * Test routine.
     * @param args -- stock symbols to display, Bitcoin price is shown when empty.
     */
    public static void main(String[] args) {
        final AlphaVantageAPI api = new AlphaVantageAPI();

        TimeSeriesCollection displayData = new TimeSeriesCollection();

        if (args.length < 1) {
            Map<String, String> stock = api.getCrypto("BTC", AlphaVantageAPI.TIME_DAILY);
            TimeSeries series = timeSeriesFromStock(stock, "Bitcoin");
            displayData.addSeries(series);
        } else {
            for (String symbol : args) {
                Map<String, String> stock = api.getStock(symbol, AlphaVantageAPI.TIME_MONTHLY);
                TimeSeries series = timeSeriesFromStock(stock, symbol);
                displayData.addSeries(series);
            }
        }

		JFreeChart chart = ChartFactory.createTimeSeriesChart(
			"Ativos Financeiros",
			"Data", "Valor (USD)",
			displayData,
			true, false, false
		);

		ChartPanel chartPanel = new ChartPanel(chart);

        JFrame window = new JFrame("Testing JFreeChart");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(chartPanel);
		window.pack();
		window.setVisible(true);
    }

}
