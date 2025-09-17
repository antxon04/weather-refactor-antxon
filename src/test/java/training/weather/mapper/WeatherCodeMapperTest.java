package training.weather.mapper;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase de {@link WeatherCodeMapper}.
 * Crea la instancia de un mapper real
 * */
class WeatherCodeMapperTest {
    private final WeatherCodeMapper mapper = new WeatherCodeMapper();


    /**
     * Test que se encarga de comprobar que cada uno de los codigos devuelve la descripción asignada a el.
     * */
    @Test
    void testKnownCodes() {
        assertEquals(Optional.of("Clear sky"), mapper.map(0));
        assertEquals(Optional.of("Mainly clear, partly cloudy, and overcast"), mapper.map(1));
        assertEquals(Optional.of("Mainly clear, partly cloudy, and overcast"), mapper.map(2));
        assertEquals(Optional.of("Mainly clear, partly cloudy, and overcast"), mapper.map(3));
        assertEquals(Optional.of("Fog and depositing rime fog"), mapper.map(45));
        assertEquals(Optional.of("Fog and depositing rime fog"), mapper.map(48));
        assertEquals(Optional.of("Drizzle: Light, moderate, and dense intensity"), mapper.map(51));
        assertEquals(Optional.of("Drizzle: Light, moderate, and dense intensity"), mapper.map(53));
        assertEquals(Optional.of("Drizzle: Light, moderate, and dense intensity"), mapper.map(55));
        assertEquals(Optional.of("Freezing Drizzle: Light and dense intensity"), mapper.map(56));
        assertEquals(Optional.of("Freezing Drizzle: Light and dense intensity"), mapper.map(57));
        assertEquals(Optional.of("Rain: Slight, moderate and heavy intensity"), mapper.map(61));
        assertEquals(Optional.of("Rain: Slight, moderate and heavy intensity"), mapper.map(63));
        assertEquals(Optional.of("Rain: Slight, moderate and heavy intensity"), mapper.map(65));
        assertEquals(Optional.of("Freezing Rain: Light and heavy intensity"), mapper.map(66));
        assertEquals(Optional.of("Freezing Rain: Light and heavy intensity"), mapper.map(67));
        assertEquals(Optional.of("Snow fall: Slight, moderate, and heavy intensity"), mapper.map(71));
        assertEquals(Optional.of("Snow fall: Slight, moderate, and heavy intensity"), mapper.map(73));
        assertEquals(Optional.of("Snow fall: Slight, moderate, and heavy intensity"), mapper.map(75));
        assertEquals(Optional.of("Snow grains"), mapper.map(77));
        assertEquals(Optional.of("Rain showers: Slight, moderate, and violent"), mapper.map(80));
        assertEquals(Optional.of("Rain showers: Slight, moderate, and violent"), mapper.map(81));
        assertEquals(Optional.of("Rain showers: Slight, moderate, and violent"), mapper.map(82));
        assertEquals(Optional.of("Snow showers slight and heavy"), mapper.map(85));
        assertEquals(Optional.of("Snow showers slight and heavy"), mapper.map(86));
        assertEquals(Optional.of("Thunderstorm: Slight or moderate"), mapper.map(95));
        assertEquals(Optional.of("Thunderstorm with slight and heavy hail"), mapper.map(96));
        assertEquals(Optional.of("Thunderstorm with slight and heavy hail"), mapper.map(99));
    }

    /**
     * Test que se encarga de devolver un Optional empty cuando le pasas un id que no tiene asignado una descripción
     * */
    @Test
    void testUnknownCodes() {
        assertEquals(Optional.empty(), mapper.map(999));
    }
}