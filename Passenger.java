
public class Passenger {
    private boolean onBus;
    private Location spawn;
    private Location destination;
    private int passengerID;

    public Passenger(){

    }

    public Location getSpawn() {
        return spawn;
    }

    public Location getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        return passengerID == passenger.passengerID;

    }

    @Override
    public int hashCode() {
        return passengerID;
    }
}
