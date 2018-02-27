package UniverseP.ScenarioComponents;

import Memory.UniverseMemory;
import UniverseP.Units.Dispatch;
import UniverseP.Units.Passenger;

import java.util.List;
import java.util.Map;

public class Scenario {
    private ScenarioDefinition myDefinition;
    private Dispatch myDispatch;
    private UniverseMemory myMemory;
    private Map<Integer,List<Passenger>> myPassengerSpawnTable;
    private int round;

}
