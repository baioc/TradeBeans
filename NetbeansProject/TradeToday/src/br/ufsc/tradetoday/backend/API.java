package br.ufsc.tradetoday.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.lang.StringBuilder;


/**
 * Represents some external abstract API.
 */
public abstract class API {

	private String base;


	/**
	 * @param location -- API base address to build upon.
	 */
	protected API(final String location) {
		this.base = location;
	}


	/**
	 * @return API base address.
	 */
	public String getLocation() {
		return this.base;
	}

	/**
	 * Makes a GET request to the API.
	 * @param parameters -- parameters for the API call.
	 * @return BufferedReader with the response, or null.
	 * @throws IOException when final address is invalid or couldn't get a response.
	 */
	public BufferedReader get(final String... parameters) throws IOException {
		StringBuilder params = new StringBuilder("");
		for (String p : parameters) {
			params.append(p);
		}

		BufferedReader response = null;
		try {	// @example https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
			URL link = new URL(this.base + params.toString());
			final InputStreamReader downloadStream = new InputStreamReader(link.openStream(), "UTF-8");
			response = new BufferedReader(downloadStream);

		} catch (IOException up) {
			throw up;
		}

		return response;
	}

}
