package UniverseP.ScenarioSimulation;

import UniverseP.Units.Location;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by EG OLIVER RC on 10/9/2018.
 */
public class ScenarioDefinitionTest {

    //tests for Locations
    @Test
    public void getDistance() throws Exception {
        assertEquals(0, ScenarioDefinition.getDistance(new Location(0,0), new Location(0,0)));
        assertEquals(2, ScenarioDefinition.getDistance(new Location(0,0), new Location(1,1)));
        assertEquals(4, ScenarioDefinition.getDistance(new Location(1,2), new Location(3,4)));
    }

}