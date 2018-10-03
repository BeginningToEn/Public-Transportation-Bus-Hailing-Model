package Strategies;

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

        Queue<Passenger> passengerQueue = new ConcurrentLinkedQueue<>();
        passengerQueue.offer(new Passenger(1, 1, 1, 2, 2, 0));

        BusCoordinator myCoordinator = BusCoordinator.createBusCoordinator(allBuses.keySet());

        SinglePassengerStrategy myStrat = new SinglePassengerStrategy(allBuses, myCoordinator, passengerQueue);

        assertTrue(myBus.getItinerary().isEmpty());

        myStrat.assignBuses();

        assertFalse(myBus.getItinerary().isEmpty());
        assertEquals(new PickUpLocation(1,1,1), myBus.getItinerary().peek());

        Bus myBus2 = new Bus(2, new Location(15,8));
        Bus myBus3 = new Bus(3, new Location(20,20));
        allBuses.put(2, myBus2);
        allBuses.put(3, myBus3);
        passengerQueue.offer(new Passenger(2, 17, 9, 2, 2, 0));

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

        Queue<Passenger> passengerQueue = new ConcurrentLinkedQueue<>();
        passengerQueue.offer(new Passenger(1, 17, 9, 2, 2, 0));

        SinglePassengerStrategy myStrat = new SinglePassengerStrategy(allBuses, myCoordinator, passengerQueue);

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

        Queue<Passenger> passengerQueue = new ConcurrentLinkedQueue<>();
        passengerQueue.offer(new Passenger(1, 17, 18, 2, 2, 0));
        passengerQueue.offer(new Passenger(2, 35, 35, 6, 7, 0));
        passengerQueue.offer(new Passenger(3, 10, 10, 2, 2, 0));

        SinglePassengerStrategy myStrat = new SinglePassengerStrategy(allBuses, myCoordinator, passengerQueue);

        assertTrue(myBus1.getItinerary().isEmpty());
        assertTrue(myBus2.getItinerary().isEmpty());

        myStrat.assignBuses();

        assertFalse(myBus1.getItinerary().isEmpty());
        assertFalse(myBus2.getItinerary().isEmpty());
        assertEquals(new PickUpLocation(35,35,2), myBus1.getItinerary().poll());
        assertEquals(new DropOffLocation(6,7,2), myBus1.getItinerary().poll());
        assertEquals(new PickUpLocation(17,18,1), myBus2.getItinerary().poll());
        assertEquals(new DropOffLocation(2,2,1), myBus2.getItinerary().poll());
        assertFalse(passengerQueue.isEmpty());
    }
}