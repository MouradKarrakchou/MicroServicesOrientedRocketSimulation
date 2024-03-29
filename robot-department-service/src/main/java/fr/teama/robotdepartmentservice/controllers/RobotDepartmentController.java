package fr.teama.robotdepartmentservice.controllers;

import fr.teama.robotdepartmentservice.connectors.externalDTO.PositionDTO;
import fr.teama.robotdepartmentservice.exceptions.RobotHardwareServiceUnavailableException;
import fr.teama.robotdepartmentservice.helpers.LoggerHelper;
import fr.teama.robotdepartmentservice.interfaces.IRobotManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = RobotDepartmentController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class RobotDepartmentController {

    public static final String BASE_URI = "/api/robot";

    @Autowired
    private IRobotManager robotManager;

    @PostMapping("/destination")
    public void sendDestination(@RequestBody PositionDTO positionDTO) throws RobotHardwareServiceUnavailableException {
        LoggerHelper.logInfo("Destination received from Scientific Department");
        robotManager.startRobot(positionDTO);
    }

}
