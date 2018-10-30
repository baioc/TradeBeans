package br.ufsc.TradeBeans;

import java.io.BufferedReader;
import java.io.IOException;


/**
 * Connector to the AlphaVantage API.
 */
public class AlphaVantageAPI extends API {

	private String key = "9WG1UDP816FDCG94";	// @fixme default


	/**
	 * @param key -- AlphaVantage access key.
	 */
	public AlphaVantageAPI(final String key) {
		super("https://www.alphavantage.co/query?");
		this.key =	key == null
					? this.key
					: key;
	}


	// @example https://www.alphavantage.co/documentation/

	/**
	 * Requests some stock information.
	 * @param symbol -- Stock symbol. eg: "MSFT" for Microsoft.
	 * @param timeUnit -- Time scale, "DAILY", "WEEKLY" or "MONTHLY".
	 * @return BufferedReader with the response, else null.
	 * @throws IOException when final address is invalid or couldn't get a response.
	 */
	public BufferedReader getStock(final String symbol, final String timeUnit) throws IOException {
		// @todo
		return null;
	}

	/**
	 * Requests some digital currency's information.
	 * @param symbol -- Currency symbol. eg: "BTC" for Bitcoin.
	 * @param timeUnit -- Time scale, "DAILY", "WEEKLY" or "MONTHLY".
	 * @return BufferedReader with the response, else null.
	 * @throws IOException when final address is invalid or couldn't get a response.
	 */
	public BufferedReader getCrypto(final String symbol, final String timeUnit) throws IOException {
		// @todo
		return null;
	}

	/**
	 * Requests a technical indicator calculated over a certain period for a specific symbol.
	 * @param symbol -- Stock symbol. eg: "MSFT" for Microsoft.
	 * @param timeUnit -- Time scale, "DAILY", "WEEKLY" or "MONTHLY".
	 * @param indicator -- Function to calculate for. eg: "SMA" for Simple Moving Average.
	 * @param period -- Parameter used to calculate 'moving' indicators.
	 * @return BufferedReader with the response, else null.
	 * @throws IOException when final address is invalid or couldn't get a response.
	 */
	public BufferedReader getIndicator(	final String symbol, final String timeUnit,
										final String indicator, int period	) throws IOException {
		// @todo
		return null;
	}

}
