package UniverseP.TripFactoryTest;

import UniverseP.TripFactory.NormalDistributionDefinition;
import UniverseP.TripFactory.NormalLocation;
import UniverseP.TripFactory.TripTimeTable;
import UniverseP.TripFactory.TripTimeTableFactory;
import UniverseP.Units.Trip;
import UniverseP.ScenarioSimulation.ScenarioDefinition;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by EG OLIVER RC on 1/18/2018.
 */
public class TripTableFactoryTest {

    @Test
    public void createDistribution() throws Exception {

        ScenarioDefinition myScenDef = new ScenarioDefinition( 139, 157, 100, 10, 1, 100);
        NormalLocation mySpawn = new NormalLocation(27, 32, 10);
        NormalLocation myDestination = new NormalLocation(109, 128, 15);
        NormalDistributionDefinition myDistDef = NormalDistributionDefinition.createNormalDistDef(mySpawn, myDestination, 30, 10);

        TripTimeTableFactory myFactory= new TripTimeTableFactory();

        TripTimeTable myTable = myFactory.createNormalDistribution(myScenDef, myDistDef);

        //check for correct number of passengers
        assertEquals(100, myTable.howManyPassengers());

        for ( int iterator : myTable.keySet() ) {
            for ( Trip passIt : myTable.get(iterator) ) {

                assertTrue(passIt.getSpawn().getX() >= 0);
                assertTrue(passIt.getSpawn().getY() >= 0);
                assertTrue(passIt.getSpawn().getX() < myScenDef.getGridLength());
                assertTrue(passIt.getSpawn().getY() < myScenDef.getGridHeight());

                assertTrue(passIt.getDestination().getX() >= 0);
                assertTrue(passIt.getDestination().getY() >= 0);
                assertTrue(passIt.getDestination().getX() < myScenDef.getGridLength());
                assertTrue(passIt.getDestination().getY() < myScenDef.getGridHeight());

                assertTrue(passIt.getTimeRequested() >= 0);
                assertTrue(passIt.getTimeRequested() < myScenDef.getNumTurns());
            }
        }
    }

}