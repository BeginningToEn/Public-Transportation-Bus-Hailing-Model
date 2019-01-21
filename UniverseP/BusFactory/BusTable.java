package UniverseP.BusFactory;

import UniverseP.Units.Bus;
import UniverseP.ScenarioSimulation.ScenarioDefinition;

import java.util.HashMap;

/**
 * Created by EG OLIVER RC on 2/20/2018.
 */
public class BusTable extends HashMap<Integer, Bus> {

    private ScenarioDefinition myDef;

    public BusTable (ScenarioDefinition myDef) {
        this.myDef = myDef;
    }

    public void printAllBuses() {
        for ( int iterator : this.keySet() ) {
            System.out.println(this.get(iterator));
        }
    }

}
