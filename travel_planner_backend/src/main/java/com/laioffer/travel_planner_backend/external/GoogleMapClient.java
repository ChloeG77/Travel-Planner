package com.laioffer.travel_planner_backend.external;

import com.laioffer.travel_planner_backend.ApplicationConfig;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GoogleMapClient {

		private static final String SEARCH_BY_ID_TEMPLATE =
				"https://maps.googleapis.com/maps/api/place/details/json?placeid=%s&fields=%&key=%s";
		
		public void searchByID(String place_id, ArrayList<String> fields) throws GoogleMapException, IOException {
				try {
						place_id = URLEncoder.encode(place_id, "UTF-8");
				} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
				}
				String searchURL = String.format(SEARCH_BY_ID_TEMPLATE, this.getKey(), String.join(",", fields));
				
				CloseableHttpClient httpclient = HttpClients.createDefault();
				
				// TODO: finish this

		}
		
		private String getKey() throws IOException {
				Properties prop = new Properties();
				String propFileName = "config.properties";
				InputStream inputStream = ApplicationConfig.class.getClassLoader().getResourceAsStream(propFileName);
				prop.load(inputStream);
				return prop.getProperty("google_key");
		}
		
}
