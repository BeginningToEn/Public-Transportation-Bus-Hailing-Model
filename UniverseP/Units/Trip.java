package UniverseP.Units;

import java.lang.String;

public class Trip {
    private ActionableLocation spawn;
    private ActionableLocation destination;
    private int passengerID;
    private int timeRequested;  //the turn during which the passenger asked for a ride

    public Trip(int ID, int spawn_x, int spawn_y, int destination_x, int destination_y, int timeRequested) {
        this.spawn = new PickUpLocation(spawn_x, spawn_y, ID);
        this.destination = new DropOffLocation(destination_x, destination_y, ID);
        this.passengerID = ID;
        this.timeRequested = timeRequested;
    }

    public ActionableLocation getSpawn() {
        return spawn;
    }
    public ActionableLocation getDestination() {
        return destination;
    }
    public int getTimeRequested() {
        return timeRequested;
    }
    public int getID() { return this.passengerID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        return passengerID == trip.passengerID;
    }

    @Override
    public int hashCode() {
        return passengerID;
    }

    @Override
    public String toString() {
        return String.format("ID: %03d-----Spawn: (%03d, %03d)-----Destination: (%03d, %03d)-----SpawnTurn: %03d\n",
                passengerID, spawn.getX(), spawn.getY(), destination.getX(), destination.getY() , timeRequested);
    }

}
