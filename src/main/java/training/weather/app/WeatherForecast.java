package training.weather.app;

import training.weather.client.WeatherApiClient;
import training.weather.exceptions.WeatherClientException;
import training.weather.mapper.WeatherCodeMapper;
import training.weather.model.WeatherResponse;
import training.weather.service.WeatherForecastService;

import java.time.LocalDate;
import java.util.Optional;

public class WeatherForecast {

	public static void main(String[] args){
		WeatherForecastService weatherForecastService = new WeatherForecastService(new WeatherApiClient(), new WeatherCodeMapper());
		try{
			Optional<WeatherResponse> response = weatherForecastService.getCityWeather(39.5563, 2.6741, LocalDate.now());

			response.ifPresentOrElse(r -> System.out.println("Predicción para la fecha " + r.getDate() + ": " + r.getDescription()),
					() -> System.out.println("No se encontró predicción para esa fecha."));
		}catch (WeatherClientException e){
			System.out.println(e.getMessage());
		}

	}
}
