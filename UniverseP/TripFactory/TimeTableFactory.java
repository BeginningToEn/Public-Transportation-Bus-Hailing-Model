package UniverseP.TripFactory;

import UniverseP.ScenarioSimulation.ScenarioDefinition;


public interface TimeTableFactory {
    TripTimeTable createTrips(ScenarioDefinition myScenarioDef,
                              NormalDistributionDefinition myPassengerDef);
}
