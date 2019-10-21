package UniverseP.Units;

/**
 * Created by EG OLIVER RC on 10/17/2019.
 */
public class ActionLog {

    private ActionType myType;
    private int busID;
    private int tripID;

    public ActionLog(ActionType myType, int busID, int tripID) {
        this.myType = myType;
        this.busID = busID;
        this.tripID = tripID;
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
}
