package br.ufsc.TradeBeans;

import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


public class GsonTest {

	public static void main(String[] args) {
		AlphaVantageAPI api = new AlphaVantageAPI();
		StringBuilder responseBuilder = new StringBuilder();

		try (BufferedReader file = api.getCrypto("BTC", AlphaVantageAPI.TIME_WEEKLY)) {
			String line;
			while ((line = file.readLine()) != null) {
				responseBuilder.append(line);
			}

			file.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}


		String json = responseBuilder.toString();

		Gson gson = new Gson();
		JsonElement jsonElement = new JsonParser().parse(json);
		JsonObject rootObject = jsonElement.getAsJsonObject();

		Type dataType = new TypeToken<Map<String, Map<String, String>>>(){}.getType();
		String stockKey = "Time Series (Digital Currency Weekly)";	// @todo string needs to be defined for each different request
		Map<String, Map<String, String>> stockData = gson.fromJson(rootObject.get(stockKey), dataType);

		for (String date : stockData.keySet()) {
			Map<String, String> map = stockData.get(date);
			System.out.println(date + " {");
			for (String key : map.keySet()) {
				System.out.println("\t" + key + " = " + map.get(key));
			}
			System.out.println("}");
		}

		// Type metaDataType = new TypeToken<Map<String, String>>(){}.getType();
		// Map<String, String> metaData = gson.fromJson(rootObject.get("Meta Data"), metaDataType);
	}

}
