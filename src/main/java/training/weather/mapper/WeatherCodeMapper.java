package training.weather.mapper;

import java.util.Optional;

/**
 * Convierte códigos del clima de open-meteo en las descripciones.
 * Lo manejamos con optional para evitar que devuelva strings vacíos
 */
public class WeatherCodeMapper {
    /**
     * Mapea un código de clima a su descripción.
     *
     * @param code Código de clima devuelto por la API
     * @return Optional con la descripción si existe, o vacío si el código no está definido
     */
    public Optional<String> map(int code){
        return switch (code) {
            case 0 -> Optional.of("Clear sky");
            case 1, 2, 3 -> Optional.of("Mainly clear, partly cloudy, and overcast");
            case 45, 48 -> Optional.of("Fog and depositing rime fog");
            case 51, 53, 55 -> Optional.of("Drizzle: Light, moderate, and dense intensity");
            case 56, 57 -> Optional.of("Freezing Drizzle: Light and dense intensity");
            case 61, 63, 65 -> Optional.of("Rain: Slight, moderate and heavy intensity");
            case 66, 67 -> Optional.of("Freezing Rain: Light and heavy intensity");
            case 71, 73, 75 -> Optional.of("Snow fall: Slight, moderate, and heavy intensity");
            case 77 -> Optional.of("Snow grains");
            case 80, 81, 82 -> Optional.of("Rain showers: Slight, moderate, and violent");
            case 85, 86 -> Optional.of("Snow showers slight and heavy");
            case 95 -> Optional.of("Thunderstorm: Slight or moderate");
            case 96, 99 -> Optional.of("Thunderstorm with slight and heavy hail");
            default -> Optional.empty();
        };
    }
}
