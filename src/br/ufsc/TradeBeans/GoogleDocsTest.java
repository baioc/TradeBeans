package br.ufsc.TradeBeans;

import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.IOException;


public class GoogleDocsTest {

	public static void main(String[] args) {
		StringBuilder responseBuilder = new StringBuilder();

		try {
			API api = new GoogleDocsAPI();
			BufferedReader file = api.get(
				"spreadsheets/d/e/",
				"2PACX-1vTthBZd6gPf6Ak8R0cXzxvoB-_9SkMcEyNqmaCjPNTCXLnc-rIz_e1VF55iGS7SB1ECQUjmYDg2Mk8F",
				"/pub",
				"?output=csv"
			);

			String line;
			while ((line = file.readLine()) != null) {
				responseBuilder.append(line + "\n");
			}

			file.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		System.out.println(responseBuilder.toString()); // write file to stdout
	}

}
