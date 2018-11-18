package br.ufsc.TradeBeans;

import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.IOException;


public class AlphaVantageTest {

	public static void main(String[] args) {
		StringBuilder responseBuilder = new StringBuilder();

		try {
			AlphaVantageAPI api = new AlphaVantageAPI();
			// BufferedReader file = api.getStock("MSFT", AlphaVantageAPI.TIME_MONTHLY);
			// BufferedReader file = api.getCrypto("BTC", AlphaVantageAPI.TIME_WEEKLY);
			BufferedReader file = api.getIndicator(
				"AAPL", AlphaVantageAPI.TIME_DAILY,
				AlphaVantageAPI.INDICATOR_AVERAGE_SIMPLE, AlphaVantageAPI.TYPE_CLOSE, 30
			);

			String line;
			while ((line = file.readLine()) != null) {
				responseBuilder.append(line);
			}

			file.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		System.out.println(responseBuilder.toString()); // write file to stdout
	}

}
