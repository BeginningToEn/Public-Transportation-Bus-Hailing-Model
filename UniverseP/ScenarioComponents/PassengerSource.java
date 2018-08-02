package UniverseP.ScenarioComponents;

import UniverseP.Units.Passenger;

import java.util.List;

/**
 * Created by EG OLIVER RC on 2/16/2018.
 */
public interface PassengerSource {
    List<Passenger> getPassengers(int time);
    ScenarioDefinition getScenarioDef();
}
