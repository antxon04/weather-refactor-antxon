package training.weather.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import training.weather.client.WeatherApiClient;
import training.weather.exceptions.WeatherClientException;
import training.weather.mapper.WeatherCodeMapper;
import training.weather.model.DailyResponseDto;
import training.weather.model.OpenMeteoResponseDto;
import training.weather.model.WeatherResponse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase de {@link WeatherForecastService}.
 * Se utilizan mocks de {@link WeatherApiClient} para simular las respuestas de la API y se comprueba que el servicio se comporta correctamente
 * */
class WeatherForecastServiceTest {
    private WeatherApiClient mokClient;
    private WeatherCodeMapper mapper;
    private WeatherForecastService service;

    /**
     * Inicializa los objetos antes de cada test, se crea mock para cliente de la api y un mapper real y que no tiene conexión externa
     * */
    @BeforeEach
    void setUp() {
        mokClient = Mockito.mock(WeatherApiClient.class);
        mapper = new WeatherCodeMapper();
        service = new WeatherForecastService(mokClient, mapper);
    }

    /**
     * Test de éxito: La api devuelve datos válidos, el servicio los mapea correctamente el codigo de clima
     * Se comprueba que el resultado no es vacio y que la descripcion es la que se espera
     * */
    @Test
    void getCityWeather_returnWeatherResponse() throws WeatherClientException {
        DailyResponseDto daily = new DailyResponseDto();
        daily.setTime(Arrays.asList(LocalDate.now().toString()));
        daily.setWeatherCode(Arrays.asList(0));

        OpenMeteoResponseDto fakeResponse = new OpenMeteoResponseDto();
        fakeResponse.setDaily(daily);

        when(mokClient.fetchWeather(40.4168, -3.7038)).thenReturn(fakeResponse);

        Optional<WeatherResponse> result = service.getCityWeather(40.4168, -3.7038, LocalDate.now());

        assertTrue(result.isPresent());
        assertEquals("Clear sky", result.get().getDescription());
        verify(mokClient, times(1)).fetchWeather(40.4168, -3.7038);
    }

    /**
     * Test para fecha fuera de rango: Se espera que el servicio lange una excepcion {@link WeatherClientException} por enviar una fecha fuera de rango
     * */
    @Test
    void getCityWeather_returnEmptyForInvalidDate(){
        LocalDate invalidDate = LocalDate.now().plusDays(10);

        WeatherClientException exception = assertThrows(WeatherClientException.class, () -> service.getCityWeather(40.4168, -3.7038, invalidDate));

        assertTrue(exception.getMessage().contains("La fecha debe estar entre"));
    }

    /**
     * Test para api con valores vacios: Se espera que se devuelve un opcional vacío
     * */
    @Test
    void getCityWeather_returnEmptyOptionalWhenApiReturnsNoData() throws WeatherClientException {
        DailyResponseDto daily = new DailyResponseDto();
        daily.setTime(Arrays.asList());
        daily.setWeatherCode(Arrays.asList());


        OpenMeteoResponseDto fakeResponse = new OpenMeteoResponseDto();
        fakeResponse.setDaily(daily);

        when(mokClient.fetchWeather(40.4168, -3.7038)).thenReturn(fakeResponse);

        Optional<WeatherResponse> result = service.getCityWeather(40.4168, -3.7038, LocalDate.now());

        assertTrue(result.isEmpty());
    }


    /**
     * Test para el caso de que la fecha solicitada no se encuentre dentro de los datos de la API, debe devolver un Optional vacío.
     * */
    @Test
    void getCityWeather_returnEmptyOptionalWhenDataNotFound() throws WeatherClientException {
        DailyResponseDto daily = new DailyResponseDto();
        daily.setTime(List.of("2025-09-16", "2025-09-17"));
        daily.setWeatherCode(List.of(1, 2));

        OpenMeteoResponseDto responseDto = new OpenMeteoResponseDto();
        responseDto.setDaily(daily);

        when(mokClient.fetchWeather(40.4168, -3.7038)).thenReturn(responseDto);

        LocalDate dateNoExistence = LocalDate.of(2025, 9, 18);

        Optional<WeatherResponse> result = service.getCityWeather(40.4168, -3.7038, dateNoExistence);

        assertTrue(result.isEmpty());
    }
}