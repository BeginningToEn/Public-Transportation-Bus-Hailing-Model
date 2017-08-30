import java.util.List;
import java.util.Queue;

public class Bus {
    private Integer ID;
    private boolean hasPassenger;
    private Location currentLocation;
    private Queue<Location> destinations;
    private List<Passenger> currentPassengers;

    public Bus(int ID, Location currentLocation){
        this.ID = ID;
        this.hasPassenger = false;      //buses on creation are empty
        this.currentLocation = currentLocation;
    }

    public Location getLocation() {
        return currentLocation;
    }

    public boolean hasPassenger() {
        return hasPassenger;
    }

    public void move(){
        if (currentLocation.getX() < destinations.peek().getX()) {
            currentLocation.moveDown();
        } else if (currentLocation.getX() > destinations.peek().getX()) {
            currentLocation.moveUp();
        } else if (currentLocation.getY() > destinations.peek().getY()) {
            currentLocation.moveLeft();
        } else if (currentLocation.getY() < destinations.peek().getY()) {
            currentLocation.moveRight();
        }
    }

    public Integer getID() {
        return ID;
    }

    public void setDestinations(Queue<Location> destinations) {
        this.destinations = destinations;
    }

    public void addDestination(Location myLocation){
        destinations.offer(myLocation);
    }
}
