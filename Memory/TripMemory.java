package Memory;

import UniverseP.Units.Trip;

/**
 * Created by EG OLIVER RC on 2/14/2019.
 */
public class TripMemory {

    private final Trip myTrip;

    private int timeRequested;
    private int timeAssigned;
    private int timePickedUp;
    private int timeDroppedOff;

    private int waitTime;
    private int travelTime;

    public TripMemory(Trip myTrip, int timeRequested){
        this.myTrip = myTrip;
        this.timeRequested = timeRequested;
    }

    public void setTimeAssigned(int timeAssigned) {
        this.timeAssigned = timeAssigned;
    }

    public void setTimePickedUp(int timePickedUp) {
        this.timePickedUp = timePickedUp;
    }

    public void setTimeDroppedOff(int timeDroppedOff) {
        this.timeDroppedOff = timeDroppedOff;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }
}
