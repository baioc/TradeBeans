package br.ufsc.TradeBeans;

import java.io.BufferedReader;
import java.io.IOException;


/**
 * Connector for publicly-shared Google Docs files.
 */
public class GoogleDocsAPI extends API {

	/**
	 * Base URL = "https://docs.google.com/"
	 */
	public GoogleDocsAPI() {
		super("https://docs.google.com/");
	}

}
