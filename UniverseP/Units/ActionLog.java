package UniverseP.Units;

/**
 * Created by EG OLIVER RC on 10/17/2019.
 */
public class ActionLog {

    private ActionType myType;
    private int busID;
    private int tripID;
    private Location myLocation;

    public ActionLog(ActionType myType, int busID, int tripID, Location myLocation) {
        this.myType = myType;
        this.busID = busID;
        this.tripID = tripID;
        this.myLocation = myLocation;
    }

    public ActionType getMyType() {
        return myType;
    }

    public int getBusID() {
        return busID;
    }

    public int getTripID() {
        return tripID;
    }

    public Location getMyLocation() {
        return myLocation;
    }
}
