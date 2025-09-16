package training.weather.client;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import training.weather.exceptions.WeatherClientException;
import training.weather.model.OpenMeteoResponseDto;
import java.io.IOException;
import static training.weather.util.WeatherData.*;


/**
 * Cliente que se comunica con la API de Open-Meteo para obtener la predicción diaria del tiempo y devolverla como objeto Java.
 */
@RequiredArgsConstructor
public class WeatherApiClient implements WeatherClientInterface{
    /**
     * Obtiene la predicción del tiempo para unas coordenadas.
     *
     * @param latitude Latitud de la ubicación
     * @param longitude Longitud de la ubicación
     * @return DTO con la predicción diaria. Nunca es nulo.
     * @throws WeatherClientException Si hay error de conexión, parseo o datos vacíos
     */

    @Override
    public @NonNull OpenMeteoResponseDto fetchWeather(double latitude, double longitude) throws WeatherClientException {
        String url = String.format(API_URL, latitude, longitude);

        HttpGet request = new HttpGet(url);

        try(CloseableHttpResponse response = HTTP_CLIENT.execute(request)){
            String jsonResponse = EntityUtils.toString(response.getEntity());

            OpenMeteoResponseDto dto;
            try{
                dto = GSON.fromJson(jsonResponse, OpenMeteoResponseDto.class);
            }catch (Exception e){
                throw new WeatherClientException("Error al parsear el JSON a objeto", e);
            }

            if(dto == null || dto.getDaily() == null){
                throw new WeatherClientException("La API ha devuelto una predición vacía o que no es valida");
            }

            return dto;
        }catch (IOException e){
            throw new WeatherClientException("Error al conectarnos a la API de Open-Meteo", e);
        }

    }
}
