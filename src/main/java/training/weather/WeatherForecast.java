package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {

	public String getCityWeather(float lat, float lon, Date datetime) throws IOException {
		if (datetime == null) {
			datetime = new Date();
		}
		if (datetime.before(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 6)))) {
			HttpRequestFactory rf = new NetHttpTransport().createRequestFactory();
			// https://open-meteo.com/en/docs
			HttpRequest req = rf.buildGetRequest(new GenericUrl("https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lon + "&daily=weathercode&timezone=CET"));
			String r = req.execute().parseAsString();
			JSONArray results = new JSONObject(r).getJSONObject("daily").getJSONArray("time");
			for (int i = 0; i < results.length(); i++) {
				if (new SimpleDateFormat("yyyy-MM-dd").format(datetime)
					.equals(results.get(i).toString())) {
					int c = new JSONObject(r).getJSONObject("daily").getJSONArray("weathercode").getInt(i);
					if (c == 0) {
						return "Clear sky";
					} else if (c == 1 || c == 2 || c == 3) {
						return "Mainly clear, partly cloudy, and overcast";
					} else if (c == 45 || c == 48) {
						return "Fog and depositing rime fog";
					} else if (c == 51 || c == 53 || c == 55) {
						return "Drizzle: Light, moderate, and dense intensity";
					} else if (c == 56 || c == 57) {
						return "Freezing Drizzle: Light and dense intensity";
					} else if (c == 61 || c == 63 || c == 65) {
						return "Rain: Slight, moderate and heavy intensity";
					} else if (c == 66 || c == 67) {
						return "Freezing Rain: Light and heavy intensity";
					} else if (c == 71 || c == 73 || c == 75) {
						return "Snow fall: Slight, moderate, and heavy intensity";
					} else if (c == 77) {
						return "Snow grains";
					} else if (c == 80 || c == 81 || c == 82) {
						return "Rain showers: Slight, moderate, and violent";
					} else if (c == 85 || c == 86) {
						return "Snow showers slight and heavy";
					} else if (c == 95) {
						return "Thunderstorm: Slight or moderate";
					} else if (c == 96 || c == 99) {
						return "Thunderstorm with slight and heavy hail";
					}
				}
			}
		}
		return "";
	}
}
