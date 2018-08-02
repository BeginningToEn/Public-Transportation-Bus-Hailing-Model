package UniverseP.ScenarioComponents;

import Strategies.SinglePassengerStrategy;
import Strategies.Strategy;
import UniverseP.BusFactory.BusTable;
import UniverseP.Units.Bus;
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

    private Set<Integer> availableBusesByIDs;
    private Set<Integer> assignedBusesByID;
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

        this.availableBusesByIDs = new HashSet<>();
        this.assignedBusesByID = new HashSet<>();
        this.allPassengersByID = new HashSet<>();
        this.passengersToPickUpByID = new HashSet<>();
        this.passengersEnRouteByID = new HashSet<>();
        this.deliveredPassengersByID = new HashSet<>();
        this.myStrat = new SinglePassengerStrategy(allBuses, availableBusesByIDs, assignedBusesByID, myQueue);  //this should be a var
    }

    public static ScenarioSimulation simulate(ScenarioDefinition myDef, PassengerSource mySource, BusTable allBuses /*an enum for strat should go here*/){

        ScenarioSimulation output = new ScenarioSimulation(myDef, mySource, allBuses);

        for ( int turn = 0; turn < myDef.getNumTurns(); turn++) {
            output.updatePassengers(turn);
            output.assignBuses();
            output.moveBuses();
            output.onboardPassengers();
            output.deboardPassengers();
        }

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
}
