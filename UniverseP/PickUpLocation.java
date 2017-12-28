package UniverseP;

/**
 * Created by EG OLIVER RC on 9/5/2017.
 */
public class PickUpLocation extends Location implements PassengerActionLocation{
    private Passenger passengerToPickUp;

    public PickUpLocation(int x, int y, Passenger passengerToPickUp) {
        super(x, y);
        this.passengerToPickUp = passengerToPickUp;
    }

    public Passenger getPassenger() {
        return passengerToPickUp;
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
