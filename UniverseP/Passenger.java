package UniverseP;

public class Passenger {
    private boolean onBus;
    private ActionableLocation spawn;
    private ActionableLocation destination;
    private int passengerID;
    private int spawnTurn;  //the turn during which the passenger asked for a ride

    public Passenger(int ID, int spawn_x, int spawn_y, int destination_x, int destination_y, int spawnTurn) {
        this.onBus = false;
        this.spawn = new PickUpLocation(spawn_x, spawn_y, ID);
        this.destination = new DropOffLocation(destination_x, destination_y, ID);
        this.passengerID = ID;
        this.spawnTurn = spawnTurn;
    }

    public ActionableLocation getSpawn() {
        return spawn;
    }

    public ActionableLocation getDestination() {
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
