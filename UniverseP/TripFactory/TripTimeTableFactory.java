package UniverseP.TripFactory;

import UniverseP.ScenarioSimulation.ScenarioDefinition;

//Creates PassengerTimeTables with pre-defined numTurns and grid dimensions

public class TripTimeTableFactory {

    private UniformTimeTableFactory myUniformFactory;
    private NormalTimeTableFactory myNormFactory;

    public TripTimeTableFactory() {
        this.myUniformFactory = new UniformTimeTableFactory();
        this.myNormFactory = new NormalTimeTableFactory();
    }

    public TripTimeTable createNormalDistribution(ScenarioDefinition myScenarioDef,
                                                  NormalDistributionDefinition myPassengerDef) {
        return myNormFactory.createTrips(myScenarioDef, myPassengerDef);
    }

    public TripTimeTable createUniformDistribution(ScenarioDefinition myScenarioDef) {
        return myUniformFactory.createTrips(myScenarioDef);
    }
}
