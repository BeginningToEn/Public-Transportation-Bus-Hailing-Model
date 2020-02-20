package UniverseP.Units;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.lang.String;

public class Bus {
    private Integer busID;
    private Location currentLocation;
    private Itinerary myItinerary;
    private Set<Integer> onboardTrips;  // trip IDs where passengers have been onboarded
    private Set<Integer> assignedTrips;

    public Bus(int busID, Location currentLocation){
        this.busID = busID;
        this.currentLocation = currentLocation;
        this.myItinerary = Itinerary.createEmptyItinerary();
        this.onboardTrips = new HashSet<>();
    }

    public Location getLocation() {
        return currentLocation;
    }
    public int getBusID(){ return this.busID; }
    public Set<Integer> getOnboardTrips() { return this.onboardTrips;}

    public boolean isEmpty(){
        return onboardTrips.isEmpty();
    }

    public boolean isUnassigned(){
        return myItinerary.isEmpty();
    }

    public int howManyPassengers() {
        return onboardTrips.size();
    }

    public void move(){

        if (myItinerary.isEmpty()){
            return;
        }

        if (currentLocation.getX() < myItinerary.peek().getX()) {
            currentLocation.moveRight();
        } else if (currentLocation.getX() > myItinerary.peek().getX()) {
            currentLocation.moveLeft();
        } else if (currentLocation.getY() > myItinerary.peek().getY()) {
            currentLocation.moveUp();
        } else if (currentLocation.getY() < myItinerary.peek().getY()) {
            currentLocation.moveDown();
        }
    }

    public Itinerary getItinerary() {
        return myItinerary;
    }

    public void setItinerary(Itinerary myItinerary) {
        this.myItinerary = myItinerary;
    }

    public List<ActionLog> handlePassengers(){

        List<ActionLog> actions = new ArrayList<ActionLog>();

        while( !myItinerary.isEmpty() && this.reachedDestination() ){

            int tripID = myItinerary.peek().getPassengerID();

            if (myItinerary.peek().isPickUpLocation()){
                this.onboard(tripID);
                actions.add(new ActionLog(ActionType.PICKUP, this.busID, tripID, currentLocation));
            } else if (myItinerary.peek().isDropOffLocation()){
                this.deboard(tripID);
                actions.add(new ActionLog(ActionType.DROPOFF, this.busID, tripID, currentLocation));
            }

            myItinerary.poll();
        }

        return actions;
    }

    public boolean reachedDestination(){
        ActionableLocation destination = myItinerary.peek();
        return currentLocation.getX() == destination.getX() && currentLocation.getY() == destination.getY();
    }

    public void onboard(int tripID){
        onboardTrips.add(tripID);
    }

    public void deboard(int tripID){
        onboardTrips.remove(tripID);
    }

    @Override
    public String toString(){
        return String.format("BusID: %d, Location: %s -- Passengers: %s -- Itinerary: %s", busID, currentLocation,
                onboardTrips, myItinerary);
        /*return "BusID: " + busID + " -- Bus(X,Y): " + currentLocation + " -- Passengers: " + onboardTrips +
                " -- Itinerary: " + myItinerary;*/
    }
}
