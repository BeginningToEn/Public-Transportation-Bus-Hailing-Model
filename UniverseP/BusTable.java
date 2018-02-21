package UniverseP;

import java.util.HashMap;

/**
 * Created by EG OLIVER RC on 2/20/2018.
 */
public class BusTable extends HashMap<Integer, Bus> {

    private ScenarioDefinition myDef;

    public BusTable (ScenarioDefinition myDef) {
        this.myDef = myDef;
    }

}
