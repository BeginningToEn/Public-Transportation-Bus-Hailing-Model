package UniverseP;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Bus {
    private Integer ID;
    private boolean hasPassenger;
    private Location currentLocation;
    private Queue<PassengerActionLocation> itinerary;
    private Set<Passenger> currentPassengers;
    private Set<Passenger> passengersToPickUp;

    public Bus(int ID, Location currentLocation){
        this.ID = ID;
        this.hasPassenger = false;      //buses on creation are empty
        this.currentLocation = currentLocation;
        this.itinerary = new ConcurrentLinkedQueue<>();
    }

    public Location getLocation() {
        return currentLocation;
    }

    public boolean hasPassenger() {
        return hasPassenger;
    }

    public void move(){
        if (currentLocation.getX() < itinerary.peek().getX()) {
            currentLocation.moveDown();
        } else if (currentLocation.getX() > itinerary.peek().getX()) {
            currentLocation.moveUp();
        } else if (currentLocation.getY() > itinerary.peek().getY()) {
            currentLocation.moveLeft();
        } else if (currentLocation.getY() < itinerary.peek().getY()) {
            currentLocation.moveRight();
        }
    }

    public Integer getID() {
        return ID;
    }

    public void setItinerary(Queue<PassengerActionLocation> itinerary) {
        this.itinerary = itinerary;
    }

    public void update(){
        for (Passenger it : passengersToPickUp) {
            if (it.getSpawn() == currentLocation){

            }
        }
    }
}
