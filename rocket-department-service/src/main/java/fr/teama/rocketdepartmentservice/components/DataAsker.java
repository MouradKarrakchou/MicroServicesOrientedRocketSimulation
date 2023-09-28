package fr.teama.rocketdepartmentservice.components;

import fr.teama.rocketdepartmentservice.connectors.externalDTO.TrackingFieldDTO;
import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.IDataAsker;
import fr.teama.rocketdepartmentservice.interfaces.proxy.ITelemetryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataAsker implements IDataAsker {
    @Autowired
    private ITelemetryProxy telemetryProxy;

    /*
     * Log template:
     * "the <service/hardware> <verb> <data tracked> of"
     * example: "the rocket reaches the fuel of"
     */
    @Override
    public void getNotificationOnEvents() throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("The rocket department wants the telemetry service to notify it when the fuel is empty");
        telemetryProxy.askWhenEventHappens(TrackingFieldDTO.FUEL, 0.0, "rocket-department",
                "/rocket/stage", "the rocket reaches the fuel of");

        LoggerHelper.logInfo("The rocket department wants the telemetry service to notify it when the rocket enters the Max Q");
        telemetryProxy.askWhenEventHappens(TrackingFieldDTO.HEIGHT, 1200.0, "rocket-department",
                "/rocket/enters-q", "the rocket reaches the altitude of");

        LoggerHelper.logInfo("The rocket department wants the telemetry service to notify it when the rocket leaves the Max Q");
        telemetryProxy.askWhenEventHappens(TrackingFieldDTO.HEIGHT, 1500.0, "rocket-department",
                "/rocket/leaves-q","the rocket reaches the altitude of");
    }
}
