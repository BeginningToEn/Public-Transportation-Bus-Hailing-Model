package UniverseP.PassengerFactory;

import UniverseP.ScenarioSimulation.ScenarioDefinition;

//Creates PassengerTimeTables with pre-defined numTurns and grid dimensions

public class PassengerTimeTableFactory {

    private UniformTimeTableFactory myUniformFactory;
    private NormalTimeTableFactory myNormFactory;

    public PassengerTimeTableFactory() {
        this.myUniformFactory = new UniformTimeTableFactory();
        this.myNormFactory = new NormalTimeTableFactory();
    }

    public PassengerTimeTable createNormalDistribution(ScenarioDefinition myScenarioDef,
                                                       NormalDistributionDefinition myPassengerDef) {
        return myNormFactory.createDistribution(myScenarioDef, myPassengerDef);
    }

    public PassengerTimeTable createUniformDistribution(ScenarioDefinition myScenarioDef) {
        return myUniformFactory.createDistribution(myScenarioDef);
    }
}
