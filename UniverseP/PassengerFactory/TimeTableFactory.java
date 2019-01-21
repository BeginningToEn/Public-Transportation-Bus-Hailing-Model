package UniverseP.PassengerFactory;

import UniverseP.ScenarioSimulation.ScenarioDefinition;


public interface TimeTableFactory {
    PassengerTimeTable createDistribution(ScenarioDefinition myScenarioDef,
                                                    NormalDistributionDefinition myPassengerDef);
}
