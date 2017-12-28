package UniverseP;

public class Passenger {
    private boolean onBus;
    private PassengerActionLocation spawn;
    private PassengerActionLocation destination;
    private int passengerID;

    public Passenger(){

    }

    public PassengerActionLocation getSpawn() {
        return spawn;
    }

    public PassengerActionLocation getDestination() {
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
