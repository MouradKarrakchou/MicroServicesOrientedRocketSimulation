package fr.teama.robotdepartmentservice.helpers;

import fr.teama.robotdepartmentservice.services.LogKafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerHelper.class);
    private static final String SERVICE_NAME = "robot-department-service";
    private static final String SERVICE_COLOR = "\u001B[31m";

    public static void logInfo(String logging) {
        LOGGER.info(SERVICE_COLOR + SERVICE_NAME + ": \u001B[32m" + logging + "\u001B[0m");
        LogKafkaProducerService.sendMissionLog(SERVICE_NAME, logging);
    }

    public static void logWarn(String logging) {
        LOGGER.warn(SERVICE_COLOR + SERVICE_NAME + ": \u001B[33m" + logging + "\u001B[0m");
        LogKafkaProducerService.sendMissionLog(SERVICE_NAME, logging);
    }

    public static void logError(String logging) {
        LOGGER.error(SERVICE_COLOR + SERVICE_NAME + ": \u001B[31m" + logging + "\u001B[0m");
        LogKafkaProducerService.sendMissionLog(SERVICE_NAME, logging);
    }
}
