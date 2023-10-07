package fr.teama.logsservice.controllers;

import fr.teama.logsservice.controllers.dto.MissionLogDTO;
import fr.teama.logsservice.helpers.LoggerHelper;
import fr.teama.logsservice.interfaces.ILogsManager;
import fr.teama.logsservice.models.MissionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = LogsController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class LogsController {
    public static final String BASE_URI = "/api/logs";

    @Autowired
    ILogsManager logsManager;

    @PostMapping("/save")
    public ResponseEntity<String> saveNewLog(@RequestBody MissionLogDTO missionLogDTO) {
        LoggerHelper.logInfo("Request to store a log from " + missionLogDTO.getServiceName());
        logsManager.saveLog(missionLogDTO.getServiceName(), missionLogDTO.getText(), missionLogDTO.getDate());
        return ResponseEntity.ok().body("Log successfully saved");
    }

    @GetMapping
    public ResponseEntity<List<MissionLog>> getAllLogs() {
        LoggerHelper.logInfo("Request received to get all logs");
        return ResponseEntity.ok().body(logsManager.getAllLogs());
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearLogs() {
        LoggerHelper.logInfo("Request received to clear all logs");
        logsManager.clearLogs();
        return ResponseEntity.ok().body("Logs successfully cleared");
    }
}
