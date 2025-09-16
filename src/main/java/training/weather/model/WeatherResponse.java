package training.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * Representa la predicción diaria del tiempo.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {

    /** Fecha de la predicción */
    private LocalDate date;

    /**Código de la predicción con la fecha elegida*/
    private int weatherCode;

    /**Descripción del clima, usando el mapper*/
    private String description;
}
