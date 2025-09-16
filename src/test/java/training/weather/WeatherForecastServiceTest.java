package training.weather;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.weather.app.WeatherForecast;
import training.weather.client.WeatherApiClient;
import training.weather.exceptions.WeatherClientException;
import training.weather.mapper.WeatherCodeMapper;
import training.weather.model.DailyResponseDto;
import training.weather.model.OpenMeteoResponseDto;
import training.weather.model.WeatherResponse;
import training.weather.service.WeatherForecastService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherForecastServiceTest {

	private WeatherApiClient weatherApiClient; // mockeado
	private WeatherCodeMapper weatherCodeMapper; //real o mock
	private WeatherForecastService weatherForecastService;

//	@BeforeEach
//	void setUp(){
//		weatherApiClient = mock(WeatherApiClient.class);
//		weatherCodeMapper = new WeatherCodeMapper();
//		weatherForecastService = new WeatherForecastService();
//	}
//
//	@Test
//	void testGetCityWeather_ReturnsWeatherResponse() throws WeatherClientException {
//		//Datos de simulacion de la API
//		DailyResponseDto daily = new DailyResponseDto();
//		daily.setTime(Arrays.asList(LocalDate.now().toString()));
//		daily.setWeatherCode(Arrays.asList(0));
//
//		OpenMeteoResponseDto apiResponse = new OpenMeteoResponseDto();
//		apiResponse.setDaily(daily);
//
//		when(weatherApiClient.fetchWeather(40.4167, -3.7037)).thenReturn(apiResponse);
//
//		WeatherResponse response = weatherForecastService.getCityWeather(40.4167, -3.7037, LocalDate.now());
//
//		assertNotNull(response);
//		assertEquals("Clear sky", response.getDescription());
//		assertEquals(0, response.getWeatherCode());
//
//
//	}

}