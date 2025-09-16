package training.weather.model;

import lombok.Data;


/**
 * DTO principal que representa la respuesta de la API.
 * Contiene la información diaria de la previsión del tiempo.
 */
@Data
public class OpenMeteoResponseDto {
    /**
     * Datos diarios de la previsión del tiempo.
     */
    private DailyResponseDto daily;
}
