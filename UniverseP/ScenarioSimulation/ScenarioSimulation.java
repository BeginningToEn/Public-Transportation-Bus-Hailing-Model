package UniverseP.ScenarioSimulation;

import Memory.ScenarioMemory;
import Strategies.SinglePassengerStrategy.SinglePassengerStrategy;
import Strategies.Strategy;
import UniverseP.BusFactory.BusTable;
import UniverseP.Units.*;

import java.util.*;

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
    private Map<Integer, Bus> allBuses;
    private Strategy myStrat;
    private int turn;

    private ScenarioMemory myMemory;

    private ScenarioSimulation(ScenarioDefinition myDef, TripSource mySource, Map<Integer, Bus> allBuses /*an enum for strat should go here*/ ){
        this.myDef = myDef;
        this.mySource = mySource;
        this.allBuses = allBuses;
        this.myMemory = new ScenarioMemory(myDef);
        this.turn = 0;

        this.myStrat = new SinglePassengerStrategy(allBuses);  //this should be a var
    }

    public static ScenarioSimulation setup(ScenarioDefinition myDef, TripSource mySource, Map<Integer, Bus> allBuses /*an enum for strat should go here*/){
        return new ScenarioSimulation(myDef, mySource, allBuses);
    }

    public void run(){

        while ( this.turn < myDef.getNumTurns() ) {
            this.handleNewTripRequests(this.turn);
            //Map<Integer,Itinerary> assignments = this.createItineraries();    //refactored out
            List<Assignment> myAssignments = this.createAssignments();
            this.assignBuses(myAssignments);
            this.moveBuses();
            this.handlePassengers();
            this.updateBusCoordinator();

            this.printReport();
            this.turn++;
        }

    }

    private void handleNewTripRequests(int turn) {

        if ( mySource.getTrips(turn).isPresent() ){
            myStrat.receiveNewTrips(mySource.getTrips(turn).get(), turn);
            myMemory.logCreation(mySource.getTrips(turn).get());
        }
    }

    private List<Assignment> createAssignments(){return myStrat.createAssignments();}

    private void assignItinerary(int busID, Itinerary myItinerary) {
        allBuses.get(busID).setItinerary(myItinerary);
    }

    private void logAssignments(int busID, Itinerary myItinerary){
        for(int tripID : myItinerary.getTripIDs()){
            myMemory.logAssignment(tripID, busID, turn);
        }
    }

    private void assignBuses(List<Assignment> assignments) {

        for(Assignment myAssignment : assignments) {

            Itinerary myItinerary = myAssignment.getItinerary();
            int busID = myAssignment.getBusID();

            assignItinerary(busID, myItinerary);
            logAssignments(busID, myItinerary);
        }
    }

    private void moveBuses() {
        for ( Integer it : allBuses.keySet() ) {
            allBuses.get(it).move();
        }
    }

    //Assigned Buses pick-up and drop-off passengers if they're at a PickUp or DropOff Location
    private void handlePassengers(){

        List<ActionLog> allActions = new ArrayList<>();

        for ( int busID : myStrat.getAssigned() ){

            List<ActionLog> actions = allBuses.get(busID).handlePassengers();

            for(ActionLog action : actions){
                allActions.add(action);
                this.logActions(action);
            }
        }

        myStrat.receivePickUpDropOffLog(allActions);
    }

    private void logActions(ActionLog action){
        if(action.getMyType() == ActionType.PICKUP){
            myMemory.logOnboarding(action.getTripID(), turn);
        } else if(action.getMyType() == ActionType.DROPOFF){
            myMemory.logOffboarding(action.getTripID(), turn);
        }
    }

    //call this function after handlePassengers() to mark buses that just dropped off all its passengers as unassigned
    private void updateBusCoordinator(){
        Set<Integer> emptyBusIDs = new HashSet<>();
        for ( int it : myStrat.getAssigned() ){
            Bus iteratorBus = allBuses.get(it);
            if(iteratorBus.isUnassigned()){
                emptyBusIDs.add(iteratorBus.getBusID());
            }
        }
        myStrat.recordAvailable(emptyBusIDs);
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
