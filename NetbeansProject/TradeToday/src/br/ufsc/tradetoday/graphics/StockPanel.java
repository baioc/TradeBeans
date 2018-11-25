package br.ufsc.tradetoday.graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Map;
import java.util.stream.Collectors;
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
import org.jfree.data.time.Day;

import br.ufsc.tradetoday.backend.AlphaVantageAPI;
import java.awt.Dimension;


public class StockPanel extends JPanel {

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Build a new TimeSeries from stock data.
     * @param stock -- a Map with date-stamp Strings as keys and value Strings as values.
     * @param name -- TimeSeries' name.
     * @return generated TimeSeries.
     */
    static TimeSeries timeSeriesFromStock(final Map<String, String> stock, final String name) {
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
    
    
    private TimeSeriesCollection displayData;
    
    public StockPanel() {
    	super();
    	
    	displayData = new TimeSeriesCollection();
    	
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
			null, "Data", "Preço (USD)",
			displayData
			,true, false, false
		);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		
		this.add(chartPanel);
    }
    
    public StockPanel(Dimension dimension) {
    	super();
    	
    	displayData = new TimeSeriesCollection();
    	
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
			null, "Data", "Preço (USD)",
			displayData
			,true, false, false
		);
		
		//ChartPanel chartPanel = new ChartPanel(chart);
                ChartPanel panel = new ChartPanel(chart, dimension.width, 
                        dimension.height,
                        dimension.width,dimension.height,
                        dimension.width,dimension.height,true, true, true, true, true, true);
		this.add(panel);
        //this.setSize(dimension);
        //chartPanel.setSize(dimension);
    }
    
    
    public TimeSeriesCollection getSeriesCollection() {
    	return displayData;
    }



    /**
     * Test routine.
     * @param args -- stock symbols to display, Bitcoin price is shown when empty.
     */
    public static void main(String[] args) {
        StockPanel chart = new StockPanel(new Dimension(200,200));
        
        JFrame window = new JFrame("Testing StockPanel");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(chart);
		window.pack();
		window.setVisible(true);
		
		final AlphaVantageAPI api = new AlphaVantageAPI();
		final int TRY_LIMIT = 99;
		int n;

        if (args.length < 1) {
        	System.out.printf("GetCrypto() Fetching raw BTC data from Alpha Vantage...");
            Map<String, String> stock = null;
            n = 0;
            
            while ((stock = api.getCrypto("BTC", AlphaVantageAPI.TIME_DAILY)) == null) {
            	System.out.printf(".");
            	if (++n > TRY_LIMIT) {
            		System.out.printf("\nOnGetCryptoFail() Gave up after trying %d times :(\n", n);
            		return;
            	}
            }
            
            System.out.printf("\nOnGotCrypto() Plotting chart...\n");
            TimeSeries series = timeSeriesFromStock(stock, "Bitcoin");
            chart.getSeriesCollection().addSeries(series);
            
            
        } else {
            symbols: for (String symbol : args) {
            	System.out.printf("GetStock() Fetching raw %s data from Alpha Vantage...", symbol);
            	Map<String, String> stock = null;
            	n = 0;
            	
                while ((stock = api.getStock(symbol, AlphaVantageAPI.TIME_DAILY)) == null) {
                	System.out.printf(".");
                	if (++n > TRY_LIMIT) {
                		System.out.printf("\nOnGetStockFail() Gave up after trying %d times :(\n", n);
                		continue symbols;
                	}
                }
				
                System.out.printf("\nGetIndicator() Fetching %s %s oscillator data from Alpha Vantage...", symbol, AlphaVantageAPI.INDICATOR_OSC_BOLLINGER);		
                Map<String, Map<String, String>> bbands = null;
				n = 0;
				
				while ((bbands = api.getIndicator(
					symbol, AlphaVantageAPI.TIME_DAILY,
					AlphaVantageAPI.INDICATOR_OSC_BOLLINGER, 20)
						) == null) {
					System.out.printf(".");
                	if (++n > TRY_LIMIT) {
                		System.out.printf("\nOnGetIndicatorFail() Gave up after trying %d times :(\n", n);
                		continue symbols;
                	}
				}
				
				Map<String, String> upper =	bbands.entrySet()
												  .stream()
												  .collect(Collectors.toMap(
														  e -> e.getKey(),
														  e -> e.getValue().get("Real Upper Band")
												  ));
				
				Map<String, String> lower =	bbands.entrySet()
												  .stream()
												  .collect(Collectors.toMap(
														  e -> e.getKey(),
														  e -> e.getValue().get("Real Lower Band")
												  ));
				
				System.out.printf("\nOnGotData() Plotting chart...\n");
				TimeSeries sell = timeSeriesFromStock(upper, symbol + " Sell Limit");
				chart.getSeriesCollection().addSeries(sell);
				TimeSeries series = timeSeriesFromStock(stock, symbol);
				chart.getSeriesCollection().addSeries(series);
				TimeSeries buy = timeSeriesFromStock(lower, symbol + " Buy Limit");
				chart.getSeriesCollection().addSeries(buy);
            }
        }
        
        System.out.println("OnStockPanelDone() StockPanel ready!");
        return;
    }

}
