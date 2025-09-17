package training.weather.client;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import training.weather.exceptions.WeatherClientException;
import training.weather.model.OpenMeteoResponseDto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase de {@link WeatherApiClient}.
 * Se utilizan mocks de {@link CloseableHttpClient}, {@link CloseableHttpResponse} y {@link HttpEntity}
 * Para simular la comunicación con la API y cada test verifica un comportamiento diferente del cliente (fallo, exito, json invalido o dto vacio)
 * */
class WeatherApiClientTest {
    private CloseableHttpClient mockHttpClient;
    private CloseableHttpResponse mockResponse;
    private HttpEntity mockEntity;
    private WeatherApiClient client;


    /**
     * Inicializa los mocks y crea una instancia del cliente antes de cada test.
     * */
    @BeforeEach
    void setUp(){
        mockHttpClient = Mockito.mock(CloseableHttpClient.class);
        mockResponse = Mockito.mock(CloseableHttpResponse.class);
        mockEntity = Mockito.mock(HttpEntity.class);
        client = new WeatherApiClient("", mockHttpClient, new Gson()
        );
    }

    /**
     * Test del constructor por defecto.
     * Verifica que se inicializan correctamente la URL, el cliente HTTP y el parser Gson.
     * */
    @Test
    void testDefaultConstructor() {
        WeatherApiClient client = new WeatherApiClient();

        assertNotNull(client.getApiUrl());
        assertTrue(client.getApiUrl().contains("latitude=%s"));
        assertTrue(client.getApiUrl().contains("longitude=%s"));

        assertNotNull(client.getHttpClient());
        assertNotNull(client.getGson());
    }

    /**
     * Test de éxito para {@link WeatherApiClient#fetchWeather}.
     * Simula una respuesta válida de la API y comprueba que los datos se parsean correctamente.
     * */
    @Test
    void testFetchWeatherSuccess() throws Exception{
        String json = "{daily:{time:[2025-09-17],weathercode:[0]}}";

        when(mockHttpClient.execute(any())).thenReturn(mockResponse);
        when(mockResponse.getEntity()).thenReturn(mockEntity);
        when(mockEntity.getContent()).thenReturn(new ByteArrayInputStream(json.getBytes()));

        OpenMeteoResponseDto dto = client.fetchWeather(40.4168, -3.7038);

        assertNotNull(dto);
        assertEquals(1, dto.getDaily().getTime().size());
        assertEquals("2025-09-17", dto.getDaily().getTime().getFirst());
        assertEquals(1, dto.getDaily().getWeatherCode().size());
        assertEquals(0, dto.getDaily().getWeatherCode().getFirst());

        verify(mockHttpClient, times(1)).execute(any());
    }

    /**
     * Test que simula un fallo de conexión a la API.
     * Se espera que {@link WeatherClientException} sea lanzada.
     * */
    @Test
    void testFetchWeatherFailure() throws Exception{
        when(mockHttpClient.execute(any())).thenThrow(new IOException("Conexión fallida"));

        assertThrows(WeatherClientException.class, () -> client.fetchWeather(40.4168, -3.7038));

        verify(mockHttpClient, times(1)).execute(any());
    }

    /**
     * Test que simula un JSON inválido devuelto por la API.
     * Se espera que {@link WeatherClientException} sea lanzada al intentar parsear el JSON.
     * */
    @Test
    void testFetchWeatherInvalidJson() throws Exception{
        String invalidJson = "{daily:{time:[2025-09-17],weathercode:[0]";

        when(mockHttpClient.execute(any())).thenReturn(mockResponse);
        when(mockResponse.getEntity()).thenReturn(mockEntity);
        when(mockEntity.getContent()).thenReturn(new ByteArrayInputStream(invalidJson.getBytes()));

        assertThrows(WeatherClientException.class, () -> client.fetchWeather(40.4168, -3.7038));

        verify(mockHttpClient, times(1)).execute(any());
    }

    /**
     * Test que simula que la API devuelve un DTO vacío o nulo.
     * Se espera que {@link WeatherClientException} sea lanzada.
     * */
    @Test
    void testFetchWeatherEmptyDto() throws Exception{
        String emptyJson = "{daily:null}";

        when(mockHttpClient.execute(any())).thenReturn(mockResponse);
        when(mockResponse.getEntity()).thenReturn(mockEntity);
        when(mockEntity.getContent()).thenReturn(new ByteArrayInputStream(emptyJson.getBytes()));

        assertThrows(WeatherClientException.class, () -> client.fetchWeather(40.4168, -3.7038));

        verify(mockHttpClient, times(1)).execute(any());
    }


}