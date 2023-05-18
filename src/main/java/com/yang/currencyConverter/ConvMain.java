package com.yang.currencyConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class ConvMain {
	public static void main(String args[]) throws IOException {

		// Setting URL
		String url_str = "https://v6.exchangerate-api.com/v6/8df47781ac3d279c54abb657/latest/USD";

		// Making Request
		URL url = new URL(url_str);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();

		// Convert to JSON
		JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
		JsonObject jsonobj = root.getAsJsonObject();

		// Accessing object
		String req_result = jsonobj.get("result").getAsString();
		
		System.out.println(req_result);
		
		request.disconnect();
		
	}
}
