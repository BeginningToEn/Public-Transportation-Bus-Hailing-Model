package UniverseP.TripFactoryTest;

import UniverseP.TripFactory.TripTimeTable;
import UniverseP.Units.Trip;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by EG OLIVER RC on 1/28/2018.
 */
public class TripTimeTableTest {
    @Test
    public void printAllPassengers() throws Exception {

    }

    @Test
    public void toStringAllXSpawn() throws Exception {
        TripTimeTable myTable = new TripTimeTable();

        List<Trip> firstList = new ArrayList<>();
        firstList.add(new Trip(1, 10, 10, 10, 10, 20));
        firstList.add(new Trip(2, 11, 10, 10, 10, 20));
        myTable.put(20, firstList);
        assertEquals("10\n11\n", myTable.toStringAllXSpawn());

        List<Trip> secondList = new ArrayList<>();
        secondList.add(new Trip(3, 27, 10, 10, 10, 22));
        myTable.put(22, secondList);
        assertEquals("10\n11\n27\n", myTable.toStringAllXSpawn());
    }

    @Test
    public void testAllXSpawn() throws Exception {

    }

    @Test
    public void howManyPassengers() throws Exception {
        TripTimeTable myTable = new TripTimeTable();

        assertEquals(0, myTable.howManyPassengers());

        List<Trip> firstList = new ArrayList<>();
        firstList.add(new Trip(1, 10, 10, 10, 10, 20));
        myTable.put(20, firstList);
        assertEquals(1, myTable.howManyPassengers());

        List<Trip> secondList = new ArrayList<>();
        secondList.add(new Trip(2, 11, 10, 10, 10, 20));
        secondList.add(new Trip(3, 27, 10, 10, 10, 22));
        myTable.put(22, secondList);
        assertEquals(3, myTable.howManyPassengers());
    }
}