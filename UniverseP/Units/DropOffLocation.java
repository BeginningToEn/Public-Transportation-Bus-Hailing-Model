package UniverseP.Units;

import java.util.Collection;

/**
 * Created by EG OLIVER RC on 9/7/2017.
 */
public class DropOffLocation extends ActionableLocation  {

    public DropOffLocation (int x, int y, int passengerID) {
        super(x, y, passengerID);
    }
    public DropOffLocation (int x, int y, Collection<Integer> passengerIDs) {
        super(x, y, passengerIDs);
    }

    public DropOffLocation (Location newLocation, int passengerID) {
        super(newLocation.getX(), newLocation.getY(), passengerID);
    }
    public DropOffLocation (Location newLocation, Collection<Integer> passengerIDs) {
        super(newLocation.getX(), newLocation.getY(), passengerIDs);
    }

    public boolean isPickUpLocation() {
        return false;
    }
    public boolean isDropOffLocation() {
        return true;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public String toString() {
        return "DropOff- PassID: " + this.getPassengerID() +  " (" + this.getX() + "," + this.getY() + ")";
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DropOffLocation compared = (DropOffLocation) o;

        if (this.getX() != compared.getX()) {
            return false;
        }

        if (this.getY() != compared.getY()) {
            return false;
        }

        if (this.getPassengerID() != compared.getPassengerID()) {
            return false;
        }

        return true;
    }

}