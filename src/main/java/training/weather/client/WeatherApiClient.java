package training.weather.client;
import com.google.gson.Gson;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import training.weather.exceptions.WeatherClientException;
import training.weather.model.OpenMeteoResponseDto;
import java.io.IOException;


/**
 * Cliente que se comunica con la API de Open-Meteo para obtener la predicción diaria del tiempo y devolverla como objeto Java.
 */
@Data
@RequiredArgsConstructor
public class WeatherApiClient implements WeatherClientInterface{
    /**
     * Definición de URL de la API de OpenMeteo, se usa como plantilla para reemplazar la latitud y longitud
     * */
    private final String apiUrl;

    /**
     * Cliente HTTP que se reutilizable para realizar las peticiones a la API
     * */
    private final CloseableHttpClient httpClient;

    /**
     * Objeto Gson para convertir JSON de la API a objetos Java
     * */
    private final Gson gson;

    /**
     * Constructor por defecto que inicializa los campos con valores predeterminados
     * {@code apiUrl}: Plantilla de la API con parametros de latitud y longitud
     * {@code httpClient}: instacia por defecto de {@link CloseableHttpClient}.
     * {@code gson}: nueva instancia de {@link Gson} para parsear JSON.
     * */
    public WeatherApiClient() {
        this.apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&daily=weathercode&timezone=CET";
        this.httpClient = HttpClients.createDefault();
        this.gson = new Gson();
    }

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
        String url = String.format(apiUrl, latitude, longitude);

        HttpGet request = new HttpGet(url);

        try(CloseableHttpResponse response = httpClient.execute(request)){
            String jsonResponse = EntityUtils.toString(response.getEntity());

            OpenMeteoResponseDto dto;
            try{
                dto = gson.fromJson(jsonResponse, OpenMeteoResponseDto.class);
            }catch (Exception e){
                throw new WeatherClientException("Error al parsear el JSON a objeto", e);
            }

            if(dto == null || dto.getDaily() == null){
                throw new WeatherClientException("La API ha devuelto una predición vacía o que no es valida");
            }

            return dto;
        }catch (IOException e){
            throw new WeatherClientException("Error al conectarnos a la API de OpenMeteo", e);
        }

    }
}
