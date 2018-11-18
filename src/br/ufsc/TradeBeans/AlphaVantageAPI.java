package br.ufsc.TradeBeans;

import java.io.BufferedReader;
import java.io.IOException;


/**
 * Connector to the AlphaVantage API.
 */
public class AlphaVantageAPI extends API {

	public static final String TIME_DAILY = "DAILY";
	public static final String TIME_WEEKLY = "WEEKLY";
	public static final String TIME_MONTHLY = "MONTHLY";

	public static final String INDICATOR_AVERAGE_SIMPLE = "SMA";
	public static final String INDICATOR_AVERAGE_EXP = "EMA";
	public static final String INDICATOR_OSC_BOLLINGER = "BBANDS";
	public static final String INDICATOR_OSC_DIVERGENCE = "MACD";
	public static final String INDICATOR_OSC_CHAIKIN = "ADOSC";
	public static final String INDICATOR_OSC_STOCHASTIC = "STOCH";

	public static final String TYPE_CLOSE = "close";
	public static final String TYPE_OPEN = "open";
	public static final String TYPE_HIGH = "high";
	public static final String TYPE_LOW = "low";


	private String key = "9WG1UDP816FDCG94";	// @note default


	/**
	 * Base URL: "https://www.alphavantage.co/query?"
	 * @param key -- AlphaVantage access key.
	 */
	public AlphaVantageAPI(final String key) {
		super("https://www.alphavantage.co/query?");
		this.key =	key == null
					? this.key
					: key;
	}

	public AlphaVantageAPI() {
		this(null);
	}


	// @todo change return type
	// @example https://www.alphavantage.co/documentation/

	/**
	 * Requests some stock information.
	 * @param symbol -- stock symbol. eg: "MSFT" for Microsoft.
	 * @param timeUnit -- time scale, "DAILY", "WEEKLY" or "MONTHLY".
	 * @return BufferedReader with the response, else null.
	 * @throws IOException when final address is invalid or couldn't get a response.
	 */
	public BufferedReader getStock(final String symbol, final String timeUnit) throws IOException {
		return super.get(
			"function=TIME_SERIES_", timeUnit,
			"&symbol=", symbol,
			"&outputsize=", "compact",
			"&apikey=", this.key,
			"&datatype=", "json"
		);
	}

	/**
	 * Requests some digital currency's information.
	 * @param symbol -- currency symbol. eg: "BTC" for Bitcoin.
	 * @param timeUnit -- time scale, "DAILY", "WEEKLY" or "MONTHLY".
	 * @return BufferedReader with the response, else null.
	 * @throws IOException when final address is invalid or couldn't get a response.
	 */
	public BufferedReader getCrypto(final String symbol, final String timeUnit) throws IOException {
		return super.get(
			"function=DIGITAL_CURRENCY_", timeUnit,
			"&symbol=", symbol,
			"&market=", "USD",
			"&apikey=", this.key,
			"&datatype=", "json"
		);
	}

	/**
	 * Requests a technical indicator calculated over a certain period for a specific symbol.
	 * @param symbol -- stock symbol. eg: "MSFT" for Microsoft.
	 * @param timeUnit -- time scale, "DAILY", "WEEKLY" or "MONTHLY".
	 * @param indicator -- function to calculate for. eg: "SMA" for Simple Moving Average.
	 * @param type -- price type: "close", "open", "high" or "low".
	 * @param period -- parameter used to calculate 'moving' indicators, in 'timeUnit's.
	 * @return BufferedReader with the response, else null.
	 * @throws IOException when final address is invalid or couldn't get a response.
	 */
	public BufferedReader getIndicator(final String symbol, final String timeUnit, final String indicator, final String type, int period) throws IOException {
		return super.get(
			"function=", indicator,
			"&symbol=", symbol,
			"&interval=", timeUnit,
			"&time_period=", Integer.toString(period),
			"&series_type=", type,
			"&apikey=", this.key,
			"&datatype=", "json"
		);	// @note other parameters set to default
	}

}
