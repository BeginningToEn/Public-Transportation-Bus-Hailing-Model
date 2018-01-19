package UniverseP.PassengerFactory;

import UniverseP.Passenger;
import UniverseP.ScenarioDefinition;

import java.util.*;

//Creates PassengerTimeTables with pre-defined numTurns and grid dimensions

public class PassengerTimeTableFactory {

    private UniformTimeTableFactory myUniformFactory;
    private NormalTimeTableFactory myNormFactory;

    public PassengerTimeTableFactory() {
        this.myUniformFactory = new UniformTimeTableFactory();
        this.myNormFactory = new NormalTimeTableFactory();

    }

    public PassengerTimeTable createDistribution(ScenarioDefinition myScenarioDef,
                                                           PassengerDistributionDefinition myPassengerDef) {

        if ( myPassengerDef.isUniformDistribution() ) {
            return myUniformFactory.createDistribution(myScenarioDef, myPassengerDef);
        } else {
            return myNormFactory.createDistribution(myScenarioDef, myPassengerDef);
        }

    }

}
