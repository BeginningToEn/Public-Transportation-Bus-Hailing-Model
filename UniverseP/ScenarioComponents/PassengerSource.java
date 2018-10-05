package UniverseP.ScenarioComponents;

import UniverseP.Units.Passenger;
import java.util.List;
import java.util.Optional;

/**
 * Created by EG OLIVER RC on 2/16/2018.
 */
public interface PassengerSource {
    Optional<List<Passenger>> getPassengers(int time);
    ScenarioDefinition getScenarioDef();
}
