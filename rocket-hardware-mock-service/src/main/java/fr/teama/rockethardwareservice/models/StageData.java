package fr.teama.rockethardwareservice.models;

public class StageData {

    private final int stageLevel;

    private Double fuel;

    private boolean isActivated;

    private boolean isDetached;

    public StageData(int stageLevel, double fuel) {
        this.stageLevel = stageLevel;
        this.fuel = fuel;
        this.isActivated = false;
        this.isDetached = false;
    }

    public int getStageLevel() {
        return stageLevel;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public void setDetached(boolean detached) {
        isDetached = detached;
    }

    @Override
    public String toString() {
        return "StageData{" +
                "stageLevel=" + stageLevel +
                ", fuel=" + fuel +
                '}';
    }
}