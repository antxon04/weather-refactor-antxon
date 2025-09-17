package training.weather.util;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
/**
 * En esta clase se define todas las variables o datos fijos que no van a cambiar
 * Se usa {@link UtilityClass} haciendo que la clase sea {@code final},
 * generando un constructor privado para que no se pueda instanciar,
 * y haciendo los atributos {@code static} si no lo son;
 * */
@UtilityClass
public class WeatherData {

    /**Fecha del día actual*/
    public static final LocalDate TODAY = LocalDate.now();

    /**Fecha del día maximo permitido*/
    public static final LocalDate MAX_DATE = TODAY.plusDays(6);
}
