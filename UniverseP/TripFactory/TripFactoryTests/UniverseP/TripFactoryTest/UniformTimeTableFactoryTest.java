package UniverseP.TripFactoryTest;

import UniverseP.TripFactory.TripTimeTable;
import UniverseP.TripFactory.TripTimeTableFactory;
import UniverseP.Units.Trip;
import UniverseP.ScenarioSimulation.ScenarioDefinition;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by EG OLIVER RC on 1/30/2018.
 */
public class UniformTimeTableFactoryTest {

    @Test
    public void createDistribution() throws Exception {
        int length = 139;
        int height = 157;
        int numPass = 100;
        int numBuses = 10;
        int busCapacity = 1;
        int numTurns = 100;

        ScenarioDefinition myScenDef = new ScenarioDefinition( length, height, numPass, numBuses, busCapacity, numTurns);

        TripTimeTableFactory myFactory= new TripTimeTableFactory();

        TripTimeTable myTable = myFactory.createUniformDistribution(myScenDef);

        //check for correct number of passengers
        assertEquals(numPass, myTable.howManyPassengers());

        for ( int iterator : myTable.keySet() ) {
            for ( Trip passIt : myTable.get(iterator) ) {

                assertTrue(passIt.getSpawn().getX() >= 0);
                assertTrue(passIt.getSpawn().getY() >= 0);
                assertTrue(passIt.getSpawn().getX() < length);
                assertTrue(passIt.getSpawn().getY() < height);

                assertTrue(passIt.getDestination().getX() >= 0);
                assertTrue(passIt.getDestination().getY() >= 0);
                assertTrue(passIt.getDestination().getX() < length);
                assertTrue(passIt.getDestination().getY() < height);

                assertTrue(passIt.getTimeRequested() >= 0);
                assertTrue(passIt.getTimeRequested() < numTurns);
            }
        }
    }

}