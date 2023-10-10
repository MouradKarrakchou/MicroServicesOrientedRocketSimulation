package fr.teama.rocketdepartmentservice.components;

import fr.teama.rocketdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketdepartmentservice.exceptions.WebcasterServiceUnavailableException;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.IDataAsker;
import fr.teama.rocketdepartmentservice.interfaces.IRocketAction;
import fr.teama.rocketdepartmentservice.interfaces.proxy.IRocketHardwareProxy;
import fr.teama.rocketdepartmentservice.interfaces.proxy.IWebcasterProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Component
public class RocketAction implements IRocketAction {

    @Autowired
    private IRocketHardwareProxy rocketHardwareProxy;

    @Autowired
    private IDataAsker dataAsker;

    @Autowired
    IWebcasterProxy webcasterProxy;

    @Override
    public void stageRocket() throws RocketHardwareServiceUnavailableException, WebcasterServiceUnavailableException {
        rocketHardwareProxy.stageRocket();
        webcasterProxy.warnWebcaster("Main engine cut-off. Staging separation");
        rocketHardwareProxy.activateCurrentStage();
        webcasterProxy.warnWebcaster("Second engine starting");
    }

    @Override
    public void launchRocket() throws TelemetryServiceUnavailableException, RocketHardwareServiceUnavailableException,
            InterruptedException, WebcasterServiceUnavailableException {
        for (int i = 60; i > 10; i-=10) {
            LoggerHelper.logInfo("The mission will start in " + i + " seconds");
            sleep(500);
        }

        for (int i = 10; i > 3; i--) {
            LoggerHelper.logInfo("The mission will start in " + i + " seconds");
            sleep(500);
        }
        LoggerHelper.logInfo("The mission will start in 3 seconds");
        LoggerHelper.logInfo("Main engine starting");
        rocketHardwareProxy.activateCurrentStage();
        LoggerHelper.logInfo("The mission will start in 2 seconds");
        LoggerHelper.logInfo("The mission will start in 1 second");
        LoggerHelper.logInfo("Rocket launched");
        webcasterProxy.warnWebcaster("The mission will start in 3 seconds");
        webcasterProxy.warnWebcaster("Main engine starting");
        webcasterProxy.warnWebcaster("The mission will start in 2 seconds");
        webcasterProxy.warnWebcaster("The mission will start in 1 second");
        webcasterProxy.warnWebcaster("Rocket launched");
        dataAsker.getNotificationOnEvents();
    }

    @Override
    public void slowDownRocket() throws RocketHardwareServiceUnavailableException {
        rocketHardwareProxy.slowDown();
    }

    @Override
    public void activeStage() throws RocketHardwareServiceUnavailableException {
        rocketHardwareProxy.activateCurrentStage();
    }

    @Override
    public void fairing() throws RocketHardwareServiceUnavailableException {
        LoggerHelper.logInfo("The rocket department wants the rocket hardware to eject the fairing");
        rocketHardwareProxy.fairing();
    }
}
