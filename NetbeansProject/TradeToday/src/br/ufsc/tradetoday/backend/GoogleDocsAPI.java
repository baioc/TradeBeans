package br.ufsc.TradeBeans;

import java.lang.StringBuilder;
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


	/**
	 * Test routine.
	 * @param args -- first element should be a valid request parameter, if empty the default one is used.
	 */
	public static void main(String[] args) {
		API api = new GoogleDocsAPI();
		StringBuilder response = new StringBuilder();

		String fileID = null;
		if (args.length < 1) {
			fileID = "spreadsheets/d/e/2PACX-1vTthBZd6gPf6Ak8R0cXzxvoB-_9SkMcEyNqmaCjPNTCXLnc-rIz_e1VF55iGS7SB1ECQUjmYDg2Mk8F/pub?output=csv";
		} else {
			fileID = args[0];
		}

		try (BufferedReader file = api.get(fileID)) {
			String line;
			while ((line = file.readLine()) != null) {
				response.append(line).append("\n");
			}

			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(response.toString()); // write file to stdout
	}

}
