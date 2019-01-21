package UniverseP.ScenarioSimulation;

import Strategies.SinglePassengerStrategy;
import Strategies.Strategy;
import UniverseP.BusFactory.BusTable;
import UniverseP.Units.Bus;
import UniverseP.Units.Passenger;

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
    private PassengerSource mySource;
    private Queue<Passenger> myQueue;
    private BusTable allBuses;
    private Strategy myStrat;
    private int turn;

    private BusCoordinator myCoordinator;
    private Set<Integer> allPassengersByID;
    private Set<Integer> passengersToPickUpByID;
    private Set<Integer> passengersEnRouteByID;
    private Set<Integer> deliveredPassengersByID;

    private ScenarioSimulation(ScenarioDefinition myDef, PassengerSource mySource, BusTable allBuses /*an enum for strat should go here*/ ){
        this.myDef = myDef;
        this.mySource = mySource;
        this.myQueue = new ConcurrentLinkedQueue<Passenger>();
        this.allBuses = allBuses;
        this.turn = 0;

        this.myCoordinator = BusCoordinator.createBusCoordinator(allBuses.keySet());
        this.allPassengersByID = new HashSet<>();
        this.passengersToPickUpByID = new HashSet<>();
        this.passengersEnRouteByID = new HashSet<>();
        this.deliveredPassengersByID = new HashSet<>();
        this.myStrat = new SinglePassengerStrategy(allBuses, myCoordinator, myQueue);  //this should be a var
    }

    public static ScenarioSimulation setup(ScenarioDefinition myDef, PassengerSource mySource, BusTable allBuses /*an enum for strat should go here*/){
        return new ScenarioSimulation(myDef, mySource, allBuses);
    }

    public void run(){

        while ( this.turn < myDef.getNumTurns() ) {
            this.updatePassengers(this.turn);
            this.assignBuses();
            this.moveBuses();
            this.handlePassengers();
            this.updateBusCoordinator();

            this.printReport();
            this.turn++;
        }

    }

    /*
    public static ScenarioSimulation simulate(ScenarioDefinition myDef, PassengerSource mySource, BusTable allBuses /*an enum for strat should go here){

        ScenarioSimulation output = new ScenarioSimulation(myDef, mySource, allBuses);

        System.out.println("Start: ");
        output.printReport();
        System.out.println();

        for ( int turn = 0; turn < myDef.getNumTurns(); turn++) {
            output.updatePassengers(turn);
            output.assignBuses();
            output.moveBuses();
            output.handlePassengers();

            output.printReport();
        }

        System.out.println("\nEnd: ");
        output.printReport();

        return output;
    }*/

    private void updatePassengers(int turn) {

        if ( !mySource.getPassengers(turn).isPresent() ){ return; }

        for ( Passenger it : mySource.getPassengers(turn).get() ) {
            myQueue.offer(it);
        }
    }

    private void assignBuses() {
        myStrat.assignBuses();
    }

    private void moveBuses() {
        for ( Integer it : allBuses.keySet() ) {
            allBuses.get(it).move();
        }
    }

    //Assigned Buses onboard passengers if they're at a PickUp Location
    private void handlePassengers(){
        for ( int it : myCoordinator.getAssigned() ){
            allBuses.get(it).handlePassengers();
        }
    }

    //call this function after handlePassengers() to mark buses that just dropped off all its passengers as unassigned
    private void updateBusCoordinator(){
        for ( int it : myCoordinator.getAssigned() ){
            Bus iteratorBus = allBuses.get(it);
            if(iteratorBus.isUnassigned()){
                myCoordinator.recordAvailable(iteratorBus.getID());
            }
        }
    }

    private void printReport(){
        System.out.println("Turn: " + this.turn);
        for (int it : allBuses.keySet()){
            System.out.println(allBuses.get(it));
        }
    }
}
