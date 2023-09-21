package fr.teama.payloadservice.controllers;

import fr.teama.payloadservice.interfaces.IDataAsker;
import fr.teama.payloadservice.interfaces.IPayloadReleaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = PayloadController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class PayloadController {

    public static final String BASE_URI = "/api/payload";

    @Autowired
    private IPayloadReleaser payloadReleaser;

    @Autowired
    private IDataAsker dataAsker;

    @PostMapping
    public ResponseEntity<String> missionStartWarning() {
        return dataAsker.askOrbitToTelemetry();
    }

    @PostMapping("/drop")
    public ResponseEntity<String> dropPayload() {
        return payloadReleaser.dropPayload();
    }
}
