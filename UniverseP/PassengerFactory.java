package UniverseP;

import java.util.Random;

public class PassengerFactory {
    private int maxLatitude;
    private int maxLongitude;
    private Random randNumGen;

    public PassengerFactory(UniverseDefinition myDefinition) {
        this.maxLatitude = myDefinition.getGridHeight();
        this.maxLongitude = myDefinition.getGridLength();
        this.randNumGen = new Random();
    }
}
