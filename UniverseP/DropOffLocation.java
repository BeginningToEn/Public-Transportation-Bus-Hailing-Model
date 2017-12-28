package UniverseP;

/**
 * Created by EG OLIVER RC on 9/7/2017.
 */
public class DropOffLocation extends Location implements PassengerActionLocation {
    private Passenger passengerToDropOff;

    public DropOffLocation (int x, int y, Passenger passengerToDropOff) {
        super(x, y);
        this.passengerToDropOff = passengerToDropOff;
    }

    public Passenger getPassenger() {
        return passengerToDropOff;
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