package UniverseP;

/**
 * Created by EG OLIVER RC on 9/7/2017.
 */
public class DropOffLocation extends Location implements ActionableLocation {
    private int passengerID;

    public DropOffLocation (int x, int y, int passengerID) {
        super(x, y);
        this.passengerID = passengerID;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public boolean isPickUpLocation() {
        return false;
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