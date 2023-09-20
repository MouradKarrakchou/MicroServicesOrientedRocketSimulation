package fr.teama.rockethardwaremock.components;

import fr.teama.rockethardwaremock.interfaces.IHardware;
import fr.teama.rockethardwaremock.models.Rocket;
import fr.teama.rockethardwaremock.models.Stage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class Hardware implements IHardware {

    long delay = 1;

    Rocket rocket = new Rocket(List.of(new Stage(1, 200), new Stage(2, 100)));

    @Override
    public void rocketLaunched() {
        final int[] stageLevel = {1};
        while (true) {
            rocket.setAltitude(rocket.getAltitude() +  new Random().nextDouble() * 100);
            rocket.setSpeed(rocket.getSpeed() + new Random().nextDouble() * 10);
            rocket.getStages().forEach(stage -> {
                if (stage.getStageId() == stageLevel[0]) {
                    if (stage.isActivated() == false) {
                        stage.setActivated(true);
                    }
                    stage.setFuel(stage.getFuel() - new Random().nextDouble() * 10);
                    if (stage.getFuel() <= 0) {
                        stage.setFuel(0);
                        stage.setActivated(false);
                        stage.setDetached(true);
                        stageLevel[0]++;
                    }
                }
            });

            waitDelay(delay);
        }
    }

    @Override
    public void startSendData() {
        while (true) {
            System.out.println(rocket);
            waitDelay(delay);
        }
    }

    private void waitDelay(long delay) {
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
