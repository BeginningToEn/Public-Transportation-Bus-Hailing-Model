package UniverseP.Units;

import java.util.Set;
import java.util.HashSet;

public class Bus {
    private Integer ID;
    private Location currentLocation;
    private Itinerary myItinerary;
    private Set<Integer> currentPassengers;
    private Set<Integer> passengersToPickUp;

    public Bus(int ID, Location currentLocation){
        this.ID = ID;
        this.currentLocation = currentLocation;
        this.myItinerary = Itinerary.createEmptyItinerary();
        this.currentPassengers = new HashSet<>();
    }

    public Location getLocation() {
        return currentLocation;
    }
    public int getID(){ return this.ID; }

    public boolean isEmpty(){
        return currentPassengers.isEmpty();
    }

    public boolean isUnassigned(){
        return myItinerary.isEmpty();
    }

    public int howManyPassengers() {
        return currentPassengers.size();
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

    public void setMyItinerary(Itinerary myItinerary) {
        this.myItinerary = myItinerary;
    }

    public void handlePassengers(){

        while( !myItinerary.isEmpty() && this.reachedDestination() ){

            int passengerID = myItinerary.peek().getPassengerID();

            if (myItinerary.peek().isPickUpLocation()){
                this.onboard(passengerID);
            } else {    //drop off location
                this.deboard(passengerID);
            }

            myItinerary.poll();
        }
    }

    public boolean reachedDestination(){
        ActionableLocation destination = myItinerary.peek();
        return currentLocation.getX() == destination.getX() && currentLocation.getY() == destination.getY();
    }

    public void onboard(int passengerID){
        currentPassengers.add(passengerID);
    }

    public void deboard(int passengerID){
        currentPassengers.remove(passengerID);
    }

    @Override
    public String toString(){
        return "BusID: " + ID + " -- Bus(X,Y): " + currentLocation + " -- Passengers: " + currentPassengers +
                " -- Itinerary: " + myItinerary;
    }
}
