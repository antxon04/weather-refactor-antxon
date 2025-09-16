package training.weather.util;

import com.google.gson.Gson;
import lombok.experimental.UtilityClass;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.time.LocalDate;
/**
 * En esta clase se define todas las variables o datos fijos que no van a cambiar
 * Se usa {@link UtilityClass} haciendo que la clase sea {@code final},
 * generando un constructor privado para que no se pueda instanciar,
 * y haciendo los atributos {@code static} si no lo son;
 * */
@UtilityClass
public class WeatherData {

    /** URL de la API */
    public static final String API_URL = "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&daily=weathercode&timezone=CET";
    /** Cliente HTTP reutilizable*/
    public static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();
    /** Parser de JSON a objetos*/
    public static final Gson GSON = new Gson();


    /**Fecha del día actual*/
    public static final LocalDate TODAY = LocalDate.now();

    /**Fecha del día maximo permitido*/
    public static final LocalDate MAX_DATE = TODAY.plusDays(6);
}
