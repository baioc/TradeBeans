package br.ufsc.tradetoday.backend;

import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


/**
 * Connector to the AlphaVantage API.
 */
public class AlphaVantageAPI extends API {

	public static final String TIME_DAILY = "Daily";
	public static final String TIME_WEEKLY = "Weekly";
	public static final String TIME_MONTHLY = "Monthly";
	public static final String INDICATOR_OSC_BOLLINGER = "Bollinger Bands";
	public static final String INDICATOR_OSC_ULTIMATE = "Ultimate Oscillator";
	public static final String INDICATOR_OSC_STOCHASTIC = "Stochastic Oscillator";
	public static final String SIZE_FULL = "full";
	public static final String SIZE_COMPACT = "compact";


	private static final Gson GSON = new Gson();
	private static final JsonParser PARSER = new JsonParser();

	private String key = "9WG1UDP816FDCG94";	// @note default key


	/**
	 * Base URL: "https://www.alphavantage.co/query?"
	 * @param key -- AlphaVantage access key, if null default is used.
	 */
	public AlphaVantageAPI(final String key) {
		super("https://www.alphavantage.co/query?");
		if (key != null && !key.isEmpty()) {
			this.key = key;
		}
	}

	public AlphaVantageAPI() {
		this(null);
	}


	// @example https://www.alphavantage.co/documentation/

	/**
	 * Requests some stock information.
	 * @param symbol -- stock symbol. eg: "MSFT" for Microsoft.
	 * @param timeUnit -- time scale: "Daily", "Weekly" or "Monthly".
	 * @param size -- output size: "full" for all the available data and "compact" for the last 100 points.
	 * @return A map with date stamps as keys to the respective stock value, null on fail.
	 */
	public Map<String, String> getStock(final String symbol, final String timeUnit, final String size) {
		BufferedReader file = null;
		String json = null;
		try {
			file = super.get(
				"function=TIME_SERIES_", timeUnit.toUpperCase(),
				"&symbol=", symbol.toUpperCase(),
				"&outputsize=", size.toLowerCase(),
				"&apikey=", this.key,
				"&datatype=", "json"
			);

			json = readJson(file);
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		String dataKey = null;
		switch (timeUnit) {
			case TIME_DAILY:
				dataKey = "Time Series (Daily)";
				break;
			case TIME_WEEKLY: case TIME_MONTHLY:
				dataKey = timeUnit + " Time Series";
				break;
			default:
				return null;
		}

		final Map<String, Map<String, String>> parsedData = parseJson(json, dataKey);
		if (parsedData == null) return null;

		Map<String, String> data = new LinkedHashMap<>(parsedData.size());

		for (String dateStamp : parsedData.keySet()) {
			String value = parsedData.get(dateStamp).get("4. close");
			if (value == null) {
				return null;
			}
			data.put(dateStamp, value);
		}

		return data;
	}

	/**
	 * Requests some stock information.
	 * @param symbol -- stock symbol. eg: "MSFT" for Microsoft.
	 * @param timeUnit -- time scale: "Daily", "Weekly" or "Monthly".
	 * @return A map with date stamps as keys to the respective stock value, null on fail.
	 */
	public Map<String, String> getStock(final String symbol, final String timeUnit) {
		return getStock(symbol, timeUnit, SIZE_COMPACT);
	}

	/**
	 * Requests some digital currency's information.
	 * @param symbol -- currency symbol. eg: "BTC" for Bitcoin.
	 * @param timeUnit -- time scale: "Daily", "Weekly" or "Monthly".
	 * @return A map with date stamps as keys to the respective stock value, null on fail.
	 */
	public Map<String, String> getCrypto(final String symbol, final String timeUnit) {
		BufferedReader file = null;
		String json = null;
		try {
			file = super.get(
				"function=DIGITAL_CURRENCY_", timeUnit.toUpperCase(),
				"&symbol=", symbol.toUpperCase(),
				"&market=", "USD",
				"&apikey=", this.key,
				"&datatype=", "json"
			);

			json = readJson(file);
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		String dataKey = null;
		switch (timeUnit) {
			case TIME_DAILY: case TIME_WEEKLY: case TIME_MONTHLY:
				dataKey = "Time Series (Digital Currency " + timeUnit + ")";
				break;
			default:
				return null;
		}

		final Map<String, Map<String, String>> parsedData = parseJson(json, dataKey);
		if (parsedData == null) return null;

		Map<String, String> data = new LinkedHashMap<>(parsedData.size());

		for (String dateStamp : parsedData.keySet()) {
			String value = parsedData.get(dateStamp).get("4b. close (USD)");
			if (value == null) return null;
			data.put(dateStamp, value);
		}

		return data;
	}

	/**
	 * Requests a technical indicator calculated over a certain period for a specific symbol.
	 * @param symbol -- stock symbol. eg: "MSFT" for Microsoft.
	 * @param timeUnit -- time scale: "Daily", "Weekly" or "Monthly".
	 * @param indicator -- function to calculate for. eg: "SMA" for Simple Moving Average.
	 * @param period -- parameter used to calculate moving indicators, in 'timeUnit's.
	 * @return A map with date stamps as keys to the respective indicator object-value (Map<String, String>), null on fail.
	 */
	public Map<String, Map<String, String>> getIndicator(final String symbol, final String timeUnit, final String indicator, int period) {
		BufferedReader file = null;
		String json = null;
		
		String actualIndicator = null;
		switch (indicator) {
			case INDICATOR_OSC_BOLLINGER:
				actualIndicator = "BBANDS";
				break;
				
			case INDICATOR_OSC_ULTIMATE:
				actualIndicator = "ULTOSC";
				break;
				
			case INDICATOR_OSC_STOCHASTIC:
				actualIndicator = "STOCH";
				break;
				
			default:
				return null;
		}
		
		try {
			file = super.get(
				"function=", actualIndicator,
				"&symbol=", symbol.toUpperCase(),
				"&interval=", timeUnit.toLowerCase(),
				"&time_period=", Integer.toString(period),
				"&series_type=", "close",
				"&apikey=", this.key,
				"&datatype=", "json"
			);	// @note other parameters set to alphavantage's defaults

			json = readJson(file);
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		String dataKey = "Technical Analysis: " + indicator;

		return parseJson(json, dataKey);
	}

	/**
	 * Requests a technical indicator calculated over a certain period for a specific symbol.
	 * @param symbol -- stock symbol. eg: "MSFT" for Microsoft.
	 * @param timeUnit -- time scale: "Daily", "Weekly" or "Monthly".
	 * @param indicator -- function to calculate for. eg: "SMA" for Simple Moving Average.
	 * @return A map with date stamps as keys to the respective indicator object-value (Map<String, String>), null on fail.
	 */
	public Map<String, Map<String, String>> getIndicator(final String symbol, final String timeUnit, final String indicator) {
		return getIndicator(symbol, timeUnit, indicator, 20);
	}


	/**
	 * @param file -- a BufferedReader with the file's contents.
	 * @return JSON as String.
	 */
	private String readJson(BufferedReader file) throws IOException {
		StringBuilder response = new StringBuilder();

		String line = null;
		while ((line = file.readLine()) != null) {
			response.append(line);
		}

		return response.toString();
	}

	/**
	 * @param json - JSON as String.
	 * @param dataKey - Used to access the Time Series object within the JSON.
	 * @return JSON Time Series as a Map with date stamps as keys and Map<String, String>'s as values.
	 */
	private Map<String, Map<String, String>> parseJson(final String json, final String dataKey) {
		final JsonElement jsonElement = PARSER.parse(json);
		final JsonObject rootObject = jsonElement.getAsJsonObject();
		final Type dataType = new TypeToken<Map<String, Map<String, String>>>(){}.getType();
		return GSON.fromJson(rootObject.get(dataKey), dataType);
	}


	/**
	 * Test routine.
	 * @param args -- first element should be either "stock", "crypto" or "indicator".
	 */
	public static void main(String[] args) {
		final AlphaVantageAPI api = new AlphaVantageAPI();

		String type = "stock";
		if (args.length > 0) {
			type = args[0].toLowerCase();
		}

		Map<String, String> data = null;
		switch (type) {
			case "stock":
				System.err.println("GetStock() Fetching data from Alpha Vantage...");
				data = api.getStock("MSFT", AlphaVantageAPI.TIME_MONTHLY, AlphaVantageAPI.SIZE_FULL);
				data.entrySet()
					.stream()
					.forEach(System.out::println);
				break;

			case "crypto":
				System.err.println("GetCrypto() Fetching raw BTC data from Alpha Vantage...");
				data = api.getCrypto("BTC", AlphaVantageAPI.TIME_WEEKLY);
				data.entrySet()
					.stream()
					.forEach(System.out::println);
				break;

			case "indicator":
                System.err.println("GetIndicator() Fetching indicator data from Alpha Vantage...");
				Map<String, Map<String, String>> indicator = api.getIndicator(
					"GOOGL", AlphaVantageAPI.TIME_DAILY,
					AlphaVantageAPI.INDICATOR_OSC_BOLLINGER
				);
				for (String dateStamp : indicator.keySet()) {
					Map<String, String> valueMap = indicator.get(dateStamp);

					System.out.println(dateStamp + " {");
					for (Map.Entry<String, String> entry : valueMap.entrySet()) {
						System.out.println("\t" + entry);
					}
					System.out.println("},");
				}
				break;
		}
	}

}
