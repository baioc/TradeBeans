package br.ufsc.TradeBeans;

import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.IOException;


public class AlphaVantageTest {

	public static void main(String[] args) {
		StringBuilder responseBuilder = new StringBuilder();

		try {
			AlphaVantageAPI api = new AlphaVantageAPI(null);
			BufferedReader file = api.get(
				"&function=","TIME_SERIES_MONTHLY",
				"&symbol=","MSFT",
				"&apikey=","eA4QU1N40TEMK33Y"	// @XXX this works
			);

			String line;
			while ((line = file.readLine()) != null) {
				responseBuilder.append(line);
			}

			file.close();	// @note important

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(responseBuilder.toString()); // write file to stdout
	}

}
