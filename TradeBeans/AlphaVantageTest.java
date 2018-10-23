// @todo package

import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;


public class AlphaVantageTest {

	public static void main(String[] args) {

		// @example https://www.alphavantage.co/documentation/
		final String BASE_URL = "https://www.alphavantage.co/query?";
		String apiKey = "A4QU1N40TEMK33Y";								// @XXX what the fuck
		String params = "function=TIME_SERIES_MONTHLY&symbol=MTST";

		try {
			// @example https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
			URL request = new URL(BASE_URL + params + "&apikey=" + apiKey);

			InputStreamReader inputStream = new InputStreamReader(request.openStream(), "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStream);
			StringBuilder responseBuilder = new StringBuilder();

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				responseBuilder.append(line);
			}
			bufferedReader.close();
			System.out.println(responseBuilder.toString());	// write file to stdout

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
