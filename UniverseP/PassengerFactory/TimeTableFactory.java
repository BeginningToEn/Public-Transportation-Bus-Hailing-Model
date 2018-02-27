package UniverseP.PassengerFactory;

import UniverseP.ScenarioComponents.ScenarioDefinition;


public interface TimeTableFactory {
    PassengerTimeTable createDistribution(ScenarioDefinition myScenarioDef,
                                                    NormalDistributionDefinition myPassengerDef);
}
