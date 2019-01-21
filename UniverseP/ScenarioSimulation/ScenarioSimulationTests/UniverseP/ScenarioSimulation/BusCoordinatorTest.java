package UniverseP.ScenarioSimulation;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by EG OLIVER RC on 10/16/2018.
 */
public class BusCoordinatorTest {

    BusCoordinator myCoord;

    @Before
    public void setUp() throws Exception {
        myCoord = BusCoordinator.createBusCoordinator(new HashSet<>(Arrays.asList(1,2,3,4,5)));
    }

    @Test
    public void createBusCoordinator () throws Exception {
        HashSet<Integer> busIDsOnCreation = new HashSet<>(Arrays.asList(1,2,3,4,5));
        BusCoordinator myCoordinator = BusCoordinator.createBusCoordinator(busIDsOnCreation);
        assertTrue(myCoordinator.busAvailable());
        assertEquals(busIDsOnCreation, myCoordinator.getAvailable());
        assertTrue(myCoordinator.getAssigned().isEmpty());
    }

    @Test
    public void recordAssignments() throws Exception {
        HashSet<Integer> assignments = new HashSet<>(Arrays.asList(4,5));
        HashSet<Integer> endResult = new HashSet<>(Arrays.asList(1,2,3));
        assertNotEquals(endResult, myCoord.getAvailable());
        myCoord.recordAssignments(assignments);
        assertEquals(endResult, myCoord.getAvailable());
        assertEquals(assignments, myCoord.getAssigned());
    }

    @Test
    public void recordAssignments1() throws Exception {
        HashSet<Integer> assignments = new HashSet<>(Arrays.asList(5));
        HashSet<Integer> endResult = new HashSet<>(Arrays.asList(1,2,3,4));
        assertNotEquals(endResult, myCoord.getAvailable());
        myCoord.recordAssignments(5);
        assertEquals(endResult, myCoord.getAvailable());
        assertEquals(assignments, myCoord.getAssigned());
    }

    @Test
    public void recordAvailable() throws Exception {
        HashSet<Integer> result1 = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7));
        HashSet<Integer> result2 = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,99));
        assertNotEquals(result1, myCoord.getAvailable());
        myCoord.recordAvailable(new HashSet<>(Arrays.asList(6,7)));
        assertEquals(result1, myCoord.getAvailable());
        myCoord.recordAvailable(new HashSet<>());
        assertEquals(result1, myCoord.getAvailable());
        myCoord.recordAvailable(new HashSet<>(Arrays.asList(99)));
        assertEquals(result2, myCoord.getAvailable());
    }

    @Test
    public void recordAvailable1() throws Exception {
        HashSet<Integer> result1 = new HashSet<>(Arrays.asList(1,2,3,4,5,6));
        HashSet<Integer> result2 = new HashSet<>(Arrays.asList(1,2,3,4,5,6,99));
        assertNotEquals(result1, myCoord.getAvailable());
        myCoord.recordAvailable(6);
        assertEquals(result1, myCoord.getAvailable());
        myCoord.recordAvailable(new HashSet<>());
        assertEquals(result1, myCoord.getAvailable());
        myCoord.recordAvailable(99);
        assertEquals(result2, myCoord.getAvailable());
    }

    @Test
    public void busAvailable() throws Exception {
        assertTrue(myCoord.busAvailable());
        myCoord.recordAssignments(new HashSet<>(Arrays.asList(1,2,3)));
        assertTrue(myCoord.busAvailable());
        myCoord.recordAssignments(4);
        assertTrue(myCoord.busAvailable());
        myCoord.recordAssignments(5);
        assertFalse(myCoord.busAvailable());
        myCoord.recordAvailable(3);
        assertTrue(myCoord.busAvailable());
    }

}