import Strategies.SinglePassengerStrategy;
import UniverseP.BusFactory.BusFactory;
import UniverseP.BusFactory.BusTable;
import UniverseP.TripFactory.NormalDistributionDefinition;
import UniverseP.TripFactory.NormalLocation;
import UniverseP.TripFactory.TripTimeTable;
import UniverseP.TripFactory.TripTimeTableFactory;
import UniverseP.ScenarioSimulation.*;
import UniverseP.Units.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by EG OLIVER RC on 1/20/2019.
 */
public class PrintConsoleTests {

    public static void testPassengerCreation() {
        ScenarioDefinition myScenDef = new ScenarioDefinition( 139, 157, 100, 10, 1, 100);
        NormalLocation mySpawn = new NormalLocation(27, 32, 10);
        NormalLocation myDestination = new NormalLocation(109, 128, 15);
        NormalDistributionDefinition myDistDef = NormalDistributionDefinition.createNormalDistDef(
                mySpawn, myDestination, 30, 10);
        TripTimeTableFactory myFactory= new TripTimeTableFactory();

        TripTimeTable myTable = myFactory.createNormalDistribution(myScenDef, myDistDef);
        myTable.printAllTrips();
    }

    public static void testItineraryEmpty(){
        Itinerary myItinerary = Itinerary.createEmptyItinerary();
        System.out.println("Expect True: " + myItinerary.isEmpty());

        Itinerary myItinerary2 = Itinerary.createDirectItinerary(new Trip(1, 1, 1, 2, 2, 0));
        System.out.println("Expect False: " + myItinerary2.isEmpty());
    }

    public static void testItinerary(){
        Bus myBus = new Bus(1, new Location(0,0));
        Bus myBus2 = new Bus(2, new Location(20,20));


        Map<Integer, Bus> allBuses = new HashMap<>();
        allBuses.put(1, myBus);
        allBuses.put(2, myBus2);

        BusCoordinator myCoordinator = BusCoordinator.createBusCoordinator(allBuses.keySet());

        Queue<Trip> tripQueue = new ConcurrentLinkedQueue<>();
        tripQueue.offer(new Trip(1, 1, 1, 2, 2, 0));
        SinglePassengerStrategy myStrat = new SinglePassengerStrategy(allBuses, myCoordinator, tripQueue);

        System.out.println(myBus.getItinerary().isEmpty());

        myStrat.assignBuses();

        System.out.println(myBus.getItinerary().isEmpty());
        System.out.println(myBus.getItinerary().peek().getClass());
        System.out.println(new PickUpLocation(1,1,1).equals(myBus.getItinerary().peek()) + "\n");

        System.out.println(myBus.getItinerary().toArray()[0]);
        System.out.println(myBus.getItinerary().toArray()[1]);
        System.out.println("Bus 2: " + myBus2.getItinerary().isEmpty());
        ActionableLocation myLoc = new PickUpLocation(1,1,1);
        System.out.println(myLoc.getClass());
    }

    public static void test() {

        Bus myBus = new Bus(1, new Location(0,0));
        Map<Integer, Bus> allBuses = new HashMap<>();
        allBuses.put(1, myBus);

        Queue<Trip> passengerQueue = new ConcurrentLinkedQueue<>();
        passengerQueue.offer(new Trip(1, 1, 1, 2, 2, 0));

        BusCoordinator myCoordinator = BusCoordinator.createBusCoordinator(allBuses.keySet());

        SinglePassengerStrategy myStrat = new SinglePassengerStrategy(allBuses, myCoordinator, passengerQueue);

        //assertTrue(myBus.getItinerary().isEmpty());

        myStrat.assignBuses();

        System.out.println(myBus.getItinerary().peek());

        //assertEquals(new PickUpLocation(1,1,1), myBus.getItinerary().peek());

        Bus myBus2 = new Bus(2, new Location(15,8));
        Bus myBus3 = new Bus(3, new Location(20,20));
        allBuses.put(2, myBus2);
        allBuses.put(3, myBus3);
        passengerQueue.offer(new Trip(2, 17, 9, 2, 2, 0));

    }

    public static void testSim(){

        //create definition that defines grid, busCapacity, numTurns, and can be used to create bus and pass tables
        ScenarioDefinition myDef = new ScenarioDefinition(100,100,1,1,1,8);

        //create a custom bus table and use it to create a BusCoordinator
        Bus myBus = new Bus(1, new Location(0,0));
        BusTable allBuses = new BusTable(myDef);
        allBuses.put(1, myBus);

        //Create a passenger source which here takes the form of a PassengerTimeTableReader but can be other things
        TripTimeTable myPassTTable = new TripTimeTable();
        List<Trip> spawnAtZero = new ArrayList<>();
        spawnAtZero.add(new Trip(1, 1, 1, 2, 2, 0));
        List<Trip> spawnAtFive = new ArrayList<>();
        spawnAtFive.add(new Trip(1, 1, 1, 2, 2, 0));
        myPassTTable.put(0, spawnAtZero);   //need to check what the key is supposed to be, right now key = spawn turn
        myPassTTable.put(5, spawnAtFive);
        TripSource mySource = new TripTimeTableReader(myPassTTable);


        ScenarioSimulation mySim = ScenarioSimulation.setup(myDef, mySource, allBuses);
        mySim.run();
    }

    public static void testSim2(){

        //create definition that defines grid, busCapacity, numTurns, and can be used to create bus and pass tables
        //length, height, numPass, numBuses, busCapacity, numTurns
        ScenarioDefinition myDef = new ScenarioDefinition(100,100,10,2,1,8);

        //create a custom Passengers
        TripTimeTableFactory myFactory = new TripTimeTableFactory();
        TripTimeTable passTable = myFactory.createUniformDistribution(myDef);
        TripSource tripSource = new TripTimeTableReader(passTable);

        //create a custom bus table and use it to create a BusCoordinator
        Bus myBus = new Bus(1, new Location(0,0));
        Bus myBus2 = new Bus(2, new Location(15,8));
        BusTable allBuses = new BusTable(myDef);
        allBuses.put(1, myBus);
        allBuses.put(2, myBus2);

        passTable.printAllWithLambdas();

        ScenarioSimulation mySim = ScenarioSimulation.setup(myDef, tripSource, allBuses);
        mySim.run();
    }

    public static void testTripTableCreation(){
        //create definition that defines grid, busCapacity, numTurns, and can be used to create bus and pass tables
        //length, height, numPass, numBuses, busCapacity, numTurns
        ScenarioDefinition myDef = new ScenarioDefinition(100,100,1000,2,1,100);

        NormalLocation normSpawn = new NormalLocation(50,50,20);
        NormalLocation normDest = new NormalLocation(35,35,10);
        NormalDistributionDefinition myNormDef = NormalDistributionDefinition.createNormalDistDef(
                normSpawn, normDest, 50, 15);

        //create a custom Trips
        TripTimeTableFactory myFactory = new TripTimeTableFactory();
        //TripTimeTable passTable = myFactory.createUniformDistribution(myDef);
        TripTimeTable tripTable = myFactory.createNormalDistribution(myDef, myNormDef);

        tripTable.printAllWithLambdas();
    }

    public static void testScenario(){
        //create definition that defines grid, busCapacity, numTurns, and can be used to create bus and pass tables
        //length, height, numPass, numBuses, busCapacity, numTurns
        ScenarioDefinition myDef = new ScenarioDefinition(100,100,100,2,1,8);

        //create a custom Passengers
        TripTimeTableFactory myFactory = new TripTimeTableFactory();
        TripTimeTable passTable = myFactory.createUniformDistribution(myDef);
        TripSource tripSource = new TripTimeTableReader(passTable);

        BusFactory myBusFactory = new BusFactory();
        BusTable myBusTable = myBusFactory.createDistribution(myDef);

        ScenarioSimulation mySim = ScenarioSimulation.setup(myDef, tripSource, myBusTable);
        mySim.run();
    }
}
