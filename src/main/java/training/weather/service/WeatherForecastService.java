package training.weather.service;

import lombok.RequiredArgsConstructor;
import training.weather.client.WeatherApiClient;
import training.weather.exceptions.WeatherClientException;
import training.weather.mapper.WeatherCodeMapper;
import training.weather.model.OpenMeteoResponseDto;
import training.weather.model.WeatherResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static training.weather.util.WeatherData.MAX_DATE;
import static training.weather.util.WeatherData.TODAY;

/**
 * Servicio que obtiene y transforma la información del clima consultando la API.
 */
@RequiredArgsConstructor
public class WeatherForecastService {
    private final WeatherApiClient weatherApiClient;
    private final WeatherCodeMapper weatherCodeMapper;

    /**
     * Obtiene la predicción del clima para una fecha y ubicación.
     *
     * @param latitude  Latitud en grados decimales.
     * @param longitude Longitud en grados decimales.
     * @param date      Fecha deseada. Si es {@code null}, se toma la fecha actual.
     * @return Un objeto {@link WeatherResponse} con la información del clima.
     * @throws WeatherClientException Si la fecha es inválida, la API falla o devuelve datos vacíos.
     */
    public Optional<WeatherResponse> getCityWeather(double latitude, double longitude, LocalDate date) throws WeatherClientException {
        LocalDate targetDate = date != null ? date : TODAY;

        if(targetDate.isBefore(TODAY) || targetDate.isAfter(MAX_DATE)){
            throw new WeatherClientException("La fecha debe estar entre " + TODAY + " y " + MAX_DATE);
        }

        OpenMeteoResponseDto response = weatherApiClient.fetchWeather(latitude, longitude);

        List<String> dates = response.getDaily().getTime();
        List<Integer> codes = response.getDaily().getWeatherCode();

        if(dates.isEmpty() || codes.isEmpty()){
            return Optional.empty();
        }

        int idx = dates.indexOf(targetDate.toString());
        if(idx < 0){
            return Optional.empty();
        }

        int weatherCode = codes.get(idx);
        String description = weatherCodeMapper.map(weatherCode).orElse("Desconocido");

        return Optional.of(new WeatherResponse(targetDate,weatherCode,description));
    }

}
