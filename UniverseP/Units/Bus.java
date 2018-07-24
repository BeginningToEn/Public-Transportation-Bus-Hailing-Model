package UniverseP.Units;

import java.util.Set;

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

    public Itinerary getItinerary() {
        return myItinerary;
    }

    public void setMyItinerary(Itinerary myItinerary) {
        this.myItinerary = myItinerary;
    }

    public boolean isAtActionableLocation() {

        if ( currentLocation.equals(myItinerary.peek()) ) {
            return true;
        }
        return false;
    }

    public void replyToPing() {
        if ( this.isAtActionableLocation() ) {
            //positiveResponse
        } else {
            //return empty response
        }
    }

    private void negativePingResp() {

    }

    private void positivePingResp() {

    }

    @Override
    public String toString(){
        return "ID: " + ID + " Current Location: " + currentLocation;
    }
}
