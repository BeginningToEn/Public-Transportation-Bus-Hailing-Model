package UniverseP;

/**
 * Created by EG OLIVER RC on 9/5/2017.
 */
public class PickUpLocation extends Location implements ActionableLocation {
    private int passengerID;

    public PickUpLocation(int x, int y, int passengerID) {
        super(x, y);
        this.passengerID = passengerID;
    }

    public int getPassengerID() {
        return passengerID;
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
}
