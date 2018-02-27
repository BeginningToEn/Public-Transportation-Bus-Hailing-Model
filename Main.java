import UniverseP.ScenarioComponents.PassengerSource;
import UniverseP.ScenarioComponents.PassengerTimeTableReader;
import UniverseP.ScenarioComponents.ScenarioDefinition;
import UniverseP.ScenarioComponents.ScenarioSimulation;
import UniverseP.BusFactory.BusTable;
import UniverseP.PassengerFactory.PassengerTimeTable;
import UniverseP.Units.*;

import java.util.*;


/**
 * Created by EG OLIVER RC on 8/17/2017.
 */
public class Main {
    public static void main(String[] args) {

        /*
        ScenarioDefinition myScenDef = new ScenarioDefinition( 139, 157, 100, 10, 100);
        NormalLocation mySpawn = new NormalLocation(27, 32, 10);
        NormalLocation myDestination = new NormalLocation(109, 128, 15);
        NormalDistributionDefinition myDistDef = NormalDistributionDefinition.createNormalDistDef(mySpawn, myDestination, 30, 10);
        PassengerTimeTableFactory myFactory= new PassengerTimeTableFactory();

        PassengerTimeTable myTable = myFactory.createNormalDistribution(myScenDef, myDistDef);
        myTable.printAllPassengers();
        */

        /*
        Bus myBus = new Bus(1, new Location(0,0));
        Bus myBus2 = new Bus(2, new Location(20,20));
        Map<Integer, Bus> allBuses = new HashMap<>();
        allBuses.put(1, myBus);
        Queue<Passenger> passengerQueue = new ConcurrentLinkedQueue<>();
        passengerQueue.offer(new Passenger(1, 1, 1, 2, 2, 0));
        SinglePassengerStrategy myStrat = new SinglePassengerStrategy(allBuses, passengerQueue);

        System.out.println(myBus.getItinerary().isEmpty());

        myStrat.assignBuses();

        System.out.println(myBus.getItinerary().peek().getClass());
        System.out.println(new PickUpLocation(1,1,1).equals(myBus.getItinerary().peek()) + "\n");

        System.out.println(myBus.getItinerary().toArray()[0]);
        System.out.println(myBus.getItinerary().toArray()[1]);
        System.out.println("Bus 2: " + myBus2.getItinerary().isEmpty());
        ActionableLocation myLoc = new PickUpLocation(1,1,1);
        System.out.println(myLoc.getClass());
        */

        ScenarioDefinition myScenDef = new ScenarioDefinition( 139, 157, 100, 10, 100);

        BusTable allBuses = new BusTable(myScenDef);
        allBuses.put(1, new Bus(1, new Location(0,0))) ;
        List<Passenger> myList = new ArrayList<>();
        myList.add(new Passenger(1, 1, 1, 2, 2, 0));

        PassengerTimeTable myPassTable = new PassengerTimeTable();
        myPassTable.put(0, myList);
        PassengerSource mySource = new PassengerTimeTableReader(myPassTable);

        allBuses.printAllBuses();

        ScenarioSimulation mySim = ScenarioSimulation.simulate(myScenDef, mySource, allBuses);

    }
}
