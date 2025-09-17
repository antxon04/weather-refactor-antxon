package training.weather.client;

import lombok.NonNull;
import training.weather.exceptions.WeatherClientException;
import training.weather.model.OpenMeteoResponseDto;


/**
 * Interfaz para obtener la previsión del tiempo desde la API de Open-Meteo.
 * Esta interfaz se encarga de comunicarse con la API y devolver los datos ya parseados
 * a un objeto {@link OpenMeteoResponseDto}.
 */
public interface WeatherClientInterface {
    /**
     * Devuelve la previsión diaria para unas coordenadas
     *
     * @param latitude Latitud de la ubicación en grados decimales. (Es de tipo primitivo double, por lo que nunca podra ser nulo)
     * @param longitude Longitud de la ubicación en grados decimales. (Es de tipo primitivo double, por lo que nunca podra ser nulo)
     * @return Devuelve {@link OpenMeteoResponseDto} con la información de la previsión diaria. (Nunva podrá ser nulo por la anotación)
     * @throws WeatherClientException Si ocurre un error de red, la API devuelve un error de la excepcion personalizada que tenemos creada.
     */
    @NonNull
    OpenMeteoResponseDto fetchWeather(double latitude, double longitude) throws WeatherClientException;
}
