package UniverseP.ScenarioComponents;

import Strategies.SinglePassengerStrategy;
import Strategies.Strategy;
import UniverseP.BusFactory.BusTable;
import UniverseP.Units.Bus;
import UniverseP.Units.BusCoordinator;
import UniverseP.Units.Passenger;

import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
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

    public static ScenarioSimulation simulate(ScenarioDefinition myDef, PassengerSource mySource, BusTable allBuses /*an enum for strat should go here*/){

        ScenarioSimulation output = new ScenarioSimulation(myDef, mySource, allBuses);

        output.printReport();

        for ( int turn = 0; turn < myDef.getNumTurns(); turn++) {
            output.updatePassengers(turn);
            output.assignBuses();
            output.moveBuses();
            output.onboardPassengers();
            output.deboardPassengers();
        }

        output.printReport();

        return output;
    }

    private void updatePassengers(int turn) {
        for ( Passenger it : mySource.getPassengers(turn) ) {
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

    private void onboardPassengers(){

    }

    private void deboardPassengers(){

    }

    private void printReport(){
        System.out.println("Turn: " + this.turn);
        for (int it : allBuses.keySet()){
            System.out.println(allBuses.get(it));
        }
    }
}
