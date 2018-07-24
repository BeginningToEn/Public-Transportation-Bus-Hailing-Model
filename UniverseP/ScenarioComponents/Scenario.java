package UniverseP.ScenarioComponents;

import Memory.ScenarioMemory;
import UniverseP.Units.Dispatch;
import UniverseP.Units.Passenger;

import java.util.List;
import java.util.Map;

public class Scenario {
    private ScenarioDefinition myDefinition;
    private Dispatch myDispatch;
    private ScenarioMemory myMemory;
    private Map<Integer,List<Passenger>> myPassengerSpawnTable;
    private int currentRound;

}
