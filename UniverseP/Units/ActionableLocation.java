package UniverseP.Units;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by EG OLIVER RC on 9/7/2017.
 */
public abstract class ActionableLocation extends Location{

    private Collection<Integer> passengerIDs;

    public ActionableLocation(int x, int y, int passengerID) {
        super(x, y);
        this.passengerIDs = new ArrayList<>();
        passengerIDs.add(passengerID);
    }
    public ActionableLocation(int x, int y, Collection<Integer> passengerIDs) {
        super(x, y);
        this.passengerIDs = new ArrayList<>(passengerIDs);
    }

    abstract boolean isPickUpLocation();
    abstract boolean isDropOffLocation();

    public int getX() {
        return super.getX();
    }
    public int getY() { return super.getY(); }
    public Collection<Integer> getPassengerID() { return passengerIDs; }

    public static int getDistance(ActionableLocation a, ActionableLocation b){
        return Location.getDistance(a, b);
    }

    @Override
    abstract public boolean equals(Object o);
}
