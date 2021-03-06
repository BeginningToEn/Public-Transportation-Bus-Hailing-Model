package Strategies;

import Strategies.SinglePassengerStrategy.SinglePassengerStrategy;
import UniverseP.ScenarioSimulation.BusCoordinator;
import UniverseP.Units.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.*;

/**
 * Created by EG OLIVER RC on 2/4/2018.
 */
public class SinglePassengerStrategyTest {
    @Test   //tests the basic functions of assignment
    public void assignBuses1() throws Exception {

        Bus myBus = new Bus(1, new Location(0,0));
        Map<Integer, Bus> allBuses = new HashMap<>();
        allBuses.put(1, myBus);

        Queue<Trip> tripQueue = new ConcurrentLinkedQueue<>();
        tripQueue.offer(new Trip(1, 1, 1, 2, 2, 0));

        BusCoordinator myCoordinator = BusCoordinator.createBusCoordinator(allBuses.keySet());

        SinglePassengerStrategy myStrat = new SinglePassengerStrategy(allBuses, myCoordinator);

        assertTrue(myBus.getItinerary().isEmpty());

        myStrat.assignBuses();

        assertFalse(myBus.getItinerary().isEmpty());
        assertEquals(new PickUpLocation(1,1,1), myBus.getItinerary().peek());

    }

    @Test   //tests that the closest bus is the one receiving the assignment
    public void assignBuses2() throws Exception {

        Bus myBus1 = new Bus(1, new Location(15,8));
        Bus myBus2 = new Bus(2, new Location(14,8));
        Bus myBus3 = new Bus(3, new Location(20,20));

        Map<Integer, Bus> allBuses = new HashMap<>();
        allBuses.put(1, myBus1);
        allBuses.put(2, myBus2);
        allBuses.put(3, myBus3);

        BusCoordinator myCoordinator = BusCoordinator.createBusCoordinator(allBuses.keySet());

        Queue<Trip> tripQueue = new ConcurrentLinkedQueue<>();
        tripQueue.offer(new Trip(1, 17, 9, 2, 2, 0));

        SinglePassengerStrategy myStrat = new SinglePassengerStrategy(allBuses, myCoordinator);

        //assignment has not occured yet so all buses should have no itinerary
        assertTrue(myBus1.getItinerary().isEmpty());
        assertTrue(myBus2.getItinerary().isEmpty());
        assertTrue(myBus3.getItinerary().isEmpty());

        myStrat.assignBuses();

        assertFalse(myBus1.getItinerary().isEmpty());
        assertTrue(myBus2.getItinerary().isEmpty());
        assertTrue(myBus3.getItinerary().isEmpty());
        assertEquals(new PickUpLocation(17,9,1), myBus1.getItinerary().poll());
        assertEquals(new DropOffLocation(2,2,1), myBus1.getItinerary().poll());
        assertTrue(myBus1.getItinerary().isEmpty());
    }

    @Test   //tests scenarios where passengers outnumber buses
    public void assignBuses3() throws Exception {

        Bus myBus1 = new Bus(1, new Location(11,12));
        Bus myBus2 = new Bus(2, new Location(21,22));

        Map<Integer, Bus> allBuses = new HashMap<>();
        allBuses.put(1, myBus1);
        allBuses.put(2, myBus2);

        BusCoordinator myCoordinator = BusCoordinator.createBusCoordinator(allBuses.keySet());

        Queue<Trip> tripQueue = new ConcurrentLinkedQueue<>();
        tripQueue.offer(new Trip(1, 17, 18, 2, 2, 0));
        tripQueue.offer(new Trip(2, 35, 35, 6, 7, 0));
        tripQueue.offer(new Trip(3, 10, 10, 2, 2, 0));

        SinglePassengerStrategy myStrat = new SinglePassengerStrategy(allBuses, myCoordinator);

        assertTrue(myBus1.getItinerary().isEmpty());
        assertTrue(myBus2.getItinerary().isEmpty());

        myStrat.assignBuses();

        assertFalse(myBus1.getItinerary().isEmpty());
        assertFalse(myBus2.getItinerary().isEmpty());
        assertEquals(new PickUpLocation(35,35,2), myBus1.getItinerary().poll());
        assertEquals(new DropOffLocation(6,7,2), myBus1.getItinerary().poll());
        assertEquals(new PickUpLocation(17,18,1), myBus2.getItinerary().poll());
        assertEquals(new DropOffLocation(2,2,1), myBus2.getItinerary().poll());
        assertFalse(tripQueue.isEmpty());
    }
}