package UniverseP.Units;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by EG OLIVER RC on 10/31/2018.
 */
public class BusTest {

    Bus myBus;

    @Before
    public void setUp() throws Exception {
        myBus = new Bus(1, new Location(1,1));
    }

    @Test
    public void move() throws Exception {

        //no movement
        assertEquals(new Location(1,1), myBus.getLocation());

        //movement with no itinerary
        myBus.move();
        assertEquals(new Location(1,1), myBus.getLocation());

        //movement with itinerary
        Trip myTrip = new Trip(0, 3, 2, 1, 1, 0);
        myBus.setItinerary(Itinerary.createDirectItinerary(myTrip));
        myBus.move();
        assertEquals(new Location(2,1), myBus.getLocation());
        myBus.move();
        assertEquals(new Location(3,1), myBus.getLocation());
        myBus.move();
        assertEquals(new Location(3,2), myBus.getLocation());

        myBus.getItinerary().poll();

        myBus.move();
        assertEquals(new Location(2,2), myBus.getLocation());
        myBus.move();
        assertEquals(new Location(1,2), myBus.getLocation());
        myBus.move();
        assertEquals(new Location(1,1), myBus.getLocation());

    }

    @Test
    public void handlePassengers() throws Exception {
        HashSet<Integer> noPassengers = new HashSet<>();
        HashSet<Integer> withPassengers = new HashSet<>(Arrays.asList(0));
        assertEquals(noPassengers, myBus.getCurrentPassengers());
        Trip myTrip = new Trip(0, 2, 1, 2, 3, 0);
        myBus.setItinerary(Itinerary.createDirectItinerary(myTrip));
        myBus.move();
        myBus.handlePassengers();
        assertEquals(withPassengers, myBus.getCurrentPassengers());
        myBus.move();
        myBus.handlePassengers();
        assertEquals(withPassengers, myBus.getCurrentPassengers());
        myBus.move();
        myBus.handlePassengers();
        assertEquals(noPassengers, myBus.getCurrentPassengers());
    }

    @Test
    public void reachedDestination() throws Exception {
        Trip myTrip = new Trip(0, 2, 1, 2, 3, 0);
        myBus.setItinerary(Itinerary.createDirectItinerary(myTrip));
        assertFalse(myBus.reachedDestination());
        myBus.move();
        assertTrue(myBus.reachedDestination());
        myBus.handlePassengers();
        myBus.move();
        assertFalse(myBus.reachedDestination());
        myBus.move();
        assertTrue(myBus.reachedDestination());
    }

}