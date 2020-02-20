package UniverseP.Units;

/**
 * Send buses to StandByLocation to await further instructions
 */
public class StandByLocation extends ActionableLocation {

    public StandByLocation(int x, int y, int passengerID) {
        super(x, y, passengerID);
    }

    public StandByLocation(Location newLocation, int passengerID) {
        super(newLocation.getX(), newLocation.getY(), passengerID);
    }

    public StandByLocation(ActionableLocation newLocation) {
        super(newLocation.getX(), newLocation.getY(), newLocation.getPassengerID());
    }

    public boolean isPickUpLocation() {
        return false;
    }
    public boolean isDropOffLocation() {
        return false;
    }

    @Override
    public String toString() {
        return "PickUp- PassID: " + this.getPassengerID() +  " (" + this.getX() + "," + this.getY() + ")";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        StandByLocation compared = (StandByLocation) o;

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
