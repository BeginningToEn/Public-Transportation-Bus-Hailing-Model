package UniverseP.BusFactory;

import UniverseP.Units.Bus;
import UniverseP.Units.Location;
import UniverseP.ScenarioComponents.ScenarioDefinition;

import java.util.Random;

/**
 * Created by EG OLIVER RC on 1/4/2018.
 */
public class BusFactory {

    private Random randNumGen;
    private int gridLength;
    private int gridHeight;

    BusFactory() {
        this.randNumGen = new Random();
    }

    private void updateDefinition(ScenarioDefinition myScenarioDef) {
        this.gridLength = myScenarioDef.getGridLength();
        this.gridHeight = myScenarioDef.getGridHeight();
    }

    public BusTable createDistribution(ScenarioDefinition myScenarioDef, int numBuses ) {

        BusTable allBuses = new BusTable(myScenarioDef);

        this.updateDefinition(myScenarioDef);

        for (int i = 0; i < numBuses; i++) {
            allBuses.put(i, this.createBus(i));
        }

        return allBuses;
    }

    private Bus createBus(int ID){
        Location spawn =  new Location(randNumGen.nextInt( gridLength ), randNumGen.nextInt( gridHeight ));
        return new Bus(ID, spawn);
    }
}
