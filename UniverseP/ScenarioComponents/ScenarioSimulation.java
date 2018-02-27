package UniverseP.ScenarioComponents;

import Strategies.SinglePassengerStrategy;
import Strategies.Strategy;
import UniverseP.BusFactory.BusTable;
import UniverseP.Units.Passenger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by EG OLIVER RC on 2/15/2018.
 */
public class ScenarioSimulation {
    private ScenarioDefinition myDef;
    private PassengerSource mySource;
    private Queue<Passenger> myQueue;
    private BusTable allBuses;
    private Strategy myStrat;

    private ScenarioSimulation(ScenarioDefinition myDef, PassengerSource mySource, BusTable allBuses /*an enum for strat should go here*/ ){
        this.myDef = myDef;
        this.mySource = mySource;
        this.myQueue = new ConcurrentLinkedQueue<Passenger>();
        this.allBuses = allBuses;
        this.myStrat = new SinglePassengerStrategy(allBuses, myQueue);  //this should be a var
    }

    public static ScenarioSimulation simulate(ScenarioDefinition myDef, PassengerSource mySource, BusTable allBuses /*an enum for strat should go here*/){

        ScenarioSimulation output = new ScenarioSimulation(myDef, mySource, allBuses);

        //for ( int i = 0; i < myDef.getNumTurns(); i++) {
            output.updatePassengers();
            output.assignBuses();
            output.moveBuses();
        //}

        return output;
    }

    private void updatePassengers() {
        for ( Passenger it : mySource.getPassengers() ) {
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
}
