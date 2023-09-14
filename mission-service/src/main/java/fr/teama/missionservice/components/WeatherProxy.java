package fr.teama.missionservice.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherProxy {
    @Value("${api.baseurl}")
    private String apiBaseUrlHostAndPort;

    private RestTemplate restTemplate = new RestTemplate();

    public String getWeatherStatus() {
        ResponseEntity<String> response = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/weather", String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Impossible to fetch weather data.");
        }
    }
}
