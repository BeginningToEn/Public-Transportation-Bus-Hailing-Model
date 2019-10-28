package Memory;

import UniverseP.Units.Trip;
import java.util.Optional;
import java.lang.String;

/**
 * Created by EG OLIVER RC on 2/14/2019.
 */
public class TripMemory {

    private final Trip myTrip;

    private int timeRequested;
    private Optional<Integer> busID;
    private Optional<Integer> timeAssigned;
    private Optional<Integer> timePickedUp;
    private Optional<Integer> timeDroppedOff;

    private int waitTime;
    private int travelTime;

    public TripMemory(Trip myTrip){
        this.myTrip = myTrip;
        this.timeRequested = myTrip.getTimeRequested();
        busID = Optional.empty();
        timeAssigned = Optional.empty();
        timePickedUp = Optional.empty();
        timeDroppedOff = Optional.empty();
    }

    public void setAssigned(int busID, int timeAssigned) {
        this.busID = Optional.of(busID);
        this.timeAssigned = Optional.of(timeAssigned);
    }

    public void setTimePickedUp(int timePickedUp) {
        this.timePickedUp = Optional.of(timePickedUp);
    }

    public void setTimeDroppedOff(int timeDroppedOff) {
        this.timeDroppedOff = Optional.of(timeDroppedOff);
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    public int getTimeRequested() {
        return timeRequested;
    }

    public Optional<Integer> getTimeAssigned() {
        return timeAssigned;
    }

    public Optional<Integer> getTimePickedUp() {
        return timePickedUp;
    }

    public Optional<Integer> getTimeDroppedOff() {
        return timeDroppedOff;
    }

    @Override
    public String toString(){
        return String.format("TripID(%d), BusID(%d), requested(%d), assigned(%d), pickedup(%d), droppedoff(%d),\n",
                myTrip.getID(), busID, timeRequested, timeAssigned, timePickedUp, timeDroppedOff);
    }
}
