package UniverseP.ScenarioSimulation;

import UniverseP.Units.Trip;
import java.util.List;
import java.util.Optional;

/**
 * Created by EG OLIVER RC on 2/16/2018.
 */
public interface TripSource {
    Optional<List<Trip>> getTrips(int time);
    ScenarioDefinition getScenarioDef();
}
