package fr.teama.missionservice.connectors;

import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.IRocketProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class RocketProxy implements IRocketProxy {
    @Value("${rocket.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getRocketStatus() throws RocketServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Ask rocket department for the rocket status");
            ResponseEntity<String> response = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/rocket/status", String.class);
            LoggerHelper.logInfo("The rocket department replied \"" + response.getBody() + "\"");
            return response.getBody();
        } catch (Exception e) {
            LoggerHelper.logError("Rocket department is unavailable");
            throw new RocketServiceUnavailableException();
        }
    }

    @Override
    public void launchRocket() throws RocketServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Order the rocket department to launch the rocket");
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket/launch", null, String.class);
            if (Objects.equals(response.getBody(), "OK"))
                LoggerHelper.logInfo("The rocket department has launch the rocket");
            else
                LoggerHelper.logWarn("The rocket department has not launch the rocket");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket/launch", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Rocket department is unavailable");
            throw new RocketServiceUnavailableException();
        }
    }
}
