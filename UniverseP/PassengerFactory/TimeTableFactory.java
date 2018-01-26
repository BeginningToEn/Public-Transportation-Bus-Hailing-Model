package UniverseP.PassengerFactory;

import UniverseP.ScenarioDefinition;


public interface TimeTableFactory {
    PassengerTimeTable createDistribution(ScenarioDefinition myScenarioDef,
                                                    NormalDistributionDefinition myPassengerDef);
}
