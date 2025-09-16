package training.weather;

import java.io.IOException;
import java.util.Date;
import org.junit.Test;

public class WeatherForecastTest {

	@Test
	public void unfinished_test() throws IOException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = weatherForecast.getCityWeather(40.4167f, -3.7037f, new Date(1679056861000L));
		System.out.println(forecast);
	}
}