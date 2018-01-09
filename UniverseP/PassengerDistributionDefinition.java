package UniverseP;

/**
 *
 */
public class PassengerDistributionDefinition  {

    private boolean isUniformDistribution;
    private int numPassengers;
    private int x_spawnAvg;
    private int y_spawnAvg;
    private int spawnStandardDeviation;
    private int x_destinationAvg;
    private int y_destinationAvg;
    private int destinationStandardDeviation;

    private PassengerDistributionDefinition(boolean isUniformDistribution, int numPassengers, int x_spawnAvg,
                                           int y_spawnAvg, int spawnStandardDeviation, int x_destinationAvg,
                                           int y_destinationAvg, int destinationStandardDeviation) {
        this.numPassengers = numPassengers;
        this.x_spawnAvg = x_spawnAvg;
        this.y_spawnAvg = y_spawnAvg;
        this.spawnStandardDeviation = spawnStandardDeviation;
        this.x_destinationAvg = x_destinationAvg;
        this.y_destinationAvg = y_destinationAvg;
        this.destinationStandardDeviation = destinationStandardDeviation;
    }

    public static PassengerDistributionDefinition createUniformDistDef (int numPassengers, int x_spawnAvg,
                                                                        int y_spawnAvg, int spawnStandardDeviation,
                                                                        int x_destinationAvg, int y_destinationAvg,
                                                                        int destinationStandardDeviation ) {
        return new PassengerDistributionDefinition(true, numPassengers, x_spawnAvg, y_spawnAvg, spawnStandardDeviation,
                x_destinationAvg, y_destinationAvg, destinationStandardDeviation);
    }

    public PassengerDistributionDefinition createNormalDistDef(int numPassengers ) {
        return new PassengerDistributionDefinition(false, numPassengers, 0, 0, 0, 0, 0, 0);
    }

    public int getNumPassengers() {
        return numPassengers;
    }
    public int getX_spawnAvg() {
        return x_spawnAvg;
    }
    public int getY_spawnAvg() {
        return y_spawnAvg;
    }
    public int getSpawnStandardDeviation() {
        return spawnStandardDeviation;
    }
    public int getX_destinationAvg() {
        return x_destinationAvg;
    }
    public int getY_destinationAvg() {
        return y_destinationAvg;
    }
    public int getDestinationStandardDeviation() {
        return destinationStandardDeviation;
    }

}
