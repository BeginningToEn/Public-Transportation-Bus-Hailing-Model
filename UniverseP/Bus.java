package UniverseP;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Bus {
    private Integer ID;
    private Location currentLocation;
    private Itinerary myItinerary;
    private Set<Passenger> currentPassengers;
    private Set<Passenger> passengersToPickUp;

    public Bus(int ID, Location currentLocation){
        this.ID = ID;
        this.currentLocation = currentLocation;
        this.myItinerary = new Itinerary();
    }

    public Location getLocation() {
        return currentLocation;
    }

    public int howManyPassengers() {
        return currentPassengers.size();
    }

    public void move(){
        if (currentLocation.getX() < myItinerary.peek().getX()) {
            currentLocation.moveDown();
        } else if (currentLocation.getX() > myItinerary.peek().getX()) {
            currentLocation.moveUp();
        } else if (currentLocation.getY() > myItinerary.peek().getY()) {
            currentLocation.moveLeft();
        } else if (currentLocation.getY() < myItinerary.peek().getY()) {
            currentLocation.moveRight();
        }
    }

    public Integer getID() {
        return ID;
    }

    public Itinerary getItinerary() {
        return myItinerary;
    }

    public void setMyItinerary(Itinerary myItinerary) {
        this.myItinerary = myItinerary;
    }

    public void update(){
        for (Passenger it : passengersToPickUp) {
            if (it.getSpawn() == currentLocation){

            }
        }
    }

    @Override
    public String toString(){
        return "ID: " + ID + " Current Location: " + currentLocation;
    }
}
