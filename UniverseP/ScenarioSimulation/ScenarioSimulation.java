package UniverseP.ScenarioSimulation;

import Memory.ScenarioMemory;
import Strategies.SinglePassengerStrategy;
import Strategies.Strategy;
import UniverseP.BusFactory.BusTable;
import UniverseP.Units.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ScenarioSimulation
     Members

     ·         ScenarioDefinition
     ·         AllBuses
     ·         Available Buses
     ·         Passenger Queue
     ·         Passenger Source
     ·         Strategy

     Methods
     · Run()
        o   generateSim()
        o   simulate()
            §  simulate one turn()
                · update passenger queue
                · assign buses
                · move buses
                · ping buses for arrivals
                    o   receive info about arrivals
 */

public class ScenarioSimulation {

    private ScenarioDefinition myDef;
    private TripSource mySource;
    private BusTable allBuses;
    private Strategy myStrat;
    private int turn;

    private ScenarioMemory myMemory;

    private BusCoordinator myBusCoordinator;
    private Set<Integer> allPassengersByID;
    private Set<Integer> passengersToPickUpByID;
    private Set<Integer> passengersEnRouteByID;
    private Set<Integer> deliveredPassengersByID;

    private ScenarioSimulation(ScenarioDefinition myDef, TripSource mySource, BusTable allBuses /*an enum for strat should go here*/ ){
        this.myDef = myDef;
        this.mySource = mySource;
        this.allBuses = allBuses;
        this.myMemory = new ScenarioMemory(myDef);
        this.turn = 0;

        this.myBusCoordinator = BusCoordinator.createBusCoordinator(allBuses.keySet());
        this.allPassengersByID = new HashSet<>();
        this.passengersToPickUpByID = new HashSet<>();
        this.passengersEnRouteByID = new HashSet<>();
        this.deliveredPassengersByID = new HashSet<>();
        this.myStrat = new SinglePassengerStrategy(allBuses, myBusCoordinator);  //this should be a var
    }

    public static ScenarioSimulation setup(ScenarioDefinition myDef, TripSource mySource, BusTable allBuses /*an enum for strat should go here*/){
        return new ScenarioSimulation(myDef, mySource, allBuses);
    }

    public void run(){

        while ( this.turn < myDef.getNumTurns() ) {
            this.handleNewTripRequests(this.turn);
            Map<Integer,Trip> assignments = this.createAssignments();
            this.assignBuses(assignments);
            this.moveBuses();
            this.handlePassengers();
            this.updateBusCoordinator();

            this.printReport();
            this.turn++;
        }

    }

    private void handleNewTripRequests(int turn) {

        if ( !mySource.getTrips(turn).isPresent() ){ return; }

        for ( Trip it : mySource.getTrips(turn).get() ) {
            myStrat.receiveNewTrip(it);
            myMemory.logCreation(it);
        }
    }

    private Map<Integer,Trip> createAssignments(){
        return myStrat.createAssignments();
    }

    private void assignItinerary(int BusID, Trip myTrip) {
        Itinerary myItinerary = Itinerary.createDirectItinerary(myTrip);
        allBuses.get(BusID).setItinerary(myItinerary);
    }

    private void assignBuses(Map<Integer,Trip> assignments) {

        for(int busID : assignments.keySet()) {
            Trip myTrip = assignments.get(busID);
            assignItinerary(busID, myTrip);
            myMemory.logAssignment(myTrip.getID(), busID, turn);
        }
    }

    private void moveBuses() {
        for ( Integer it : allBuses.keySet() ) {
            allBuses.get(it).move();
        }
    }

    //Assigned Buses onboard passengers if they're at a PickUp Location
    private void handlePassengers(){
        for ( int busID : myBusCoordinator.getAssigned() ){
            List<ActionLog> actions = allBuses.get(busID).handlePassengers();
            this.logActions(actions);
        }
    }

    private void logActions(List<ActionLog> actions){
        for(ActionLog action : actions){
            if(action.getMyType() == ActionType.PICKUP){
                myMemory.logOnboarding(action.getTripID(), turn);
            } else if(action.getMyType() == ActionType.DROPOFF){
                myMemory.logOffboarding(action.getTripID(), turn);
            }
        }
    }

    //call this function after handlePassengers() to mark buses that just dropped off all its passengers as unassigned
    private void updateBusCoordinator(){
        Set<Integer> emptyBusIDs = new HashSet<>();
        for ( int it : myBusCoordinator.getAssigned() ){
            Bus iteratorBus = allBuses.get(it);
            if(iteratorBus.isUnassigned()){
                emptyBusIDs.add(iteratorBus.getBusID());
            }
        }
        myBusCoordinator.recordAvailable(emptyBusIDs);
    }

    private void printReport(){
        System.out.println("Turn: " + this.turn);
        for (int it : allBuses.keySet()){
            System.out.println(allBuses.get(it));
        }
    }

    public ScenarioMemory getMemory(){
        return myMemory;
    }
}
