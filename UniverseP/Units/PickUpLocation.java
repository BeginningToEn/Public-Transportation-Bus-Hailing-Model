package UniverseP.Units;

/**
 * Created by EG OLIVER RC on 9/5/2017.
 */
public class PickUpLocation extends ActionableLocation {

    public PickUpLocation(int x, int y, int passengerID) {
        super(x, y, passengerID);
    }

    public boolean isPickUpLocation() {
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
        return "PickUp: (" + this.getX() + "," + this.getY() + "), ID: " + this.getPassengerID();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PickUpLocation compared = (PickUpLocation) o;

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
