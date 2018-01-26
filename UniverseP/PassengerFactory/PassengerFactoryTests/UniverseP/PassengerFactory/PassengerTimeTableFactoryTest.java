package UniverseP.PassengerFactory;

import UniverseP.ScenarioDefinition;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by EG OLIVER RC on 1/18/2018.
 */
public class PassengerTimeTableFactoryTest {
    @Test
    public void createDistribution() throws Exception {
        //This is comparing number of passengers to number of unique spawnTimes of all passengers
        //which is incorrect need to fix
        ScenarioDefinition myScenDef = new ScenarioDefinition(1,100,100,100);
        NormalDistributionDefinition myPassDef = NormalDistributionDefinition.createUniformDistDef(20);
        assertEquals(20, new PassengerTimeTableFactory().createNormalDistribution(myScenDef,myPassDef).size());
    }

}