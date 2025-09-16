package training.weather.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * DTO que representa los datos diarios de la respuesta de la API
 * Contiene las fechas y códigos de clima que le corresponde
 */
@Data
public class DailyResponseDto {
    /**
     * Lista de fechas (en formato yyyy-MM-dd) para las predicciones.
     */
    private List<String> time;

    /**
     * Lista de códigos de clima correspondientes a cada fecha.
     * El campo se mapea al nombre "weathercode" del JSON
     */
    @SerializedName("weathercode")
    private List<Integer> weatherCode;
}
