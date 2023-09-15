package fr.teama.missionservice.controllers;

import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;
import fr.teama.missionservice.interfaces.IPollMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MissionController {
    public static final String BASE_URI = "/api/mission";

    @Autowired
    private IPollMaker pollMaker;

    @PostMapping(path = BASE_URI + "/poll")
    public ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException {
        return pollMaker.startMission();
    }
}
