package UniverseP;

/**
 * Created by EG OLIVER RC on 9/7/2017.
 */
public abstract class ActionableLocation extends Location{

    private int passengerID;

    public ActionableLocation(int x, int y, int passengerID) {
        super(x, y);
        this.passengerID = passengerID;
    }

    abstract boolean isPickUpLocation();

    public int getX() {
        return super.getX();
    }
    public int getY() { return super.getY(); }
    public int getPassengerID() { return passengerID; }

    @Override
    abstract public boolean equals(Object o);
}
