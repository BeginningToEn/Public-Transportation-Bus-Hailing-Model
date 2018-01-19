package UniverseP.PassengerFactory;

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
    private int spawnTimeAvg;
    private int spawnTimeStandardDeviation;

    private PassengerDistributionDefinition(boolean isUniformDistribution, int numPassengers, int x_spawnAvg,
                                            int y_spawnAvg, int spawnStandardDeviation, int x_destinationAvg,
                                            int y_destinationAvg, int destinationStandardDeviation,
                                            int spawnTimeAvg, int spawnTimeStandardDeviation) {
        this.isUniformDistribution = isUniformDistribution;
        this.numPassengers = numPassengers;
        this.x_spawnAvg = x_spawnAvg;
        this.y_spawnAvg = y_spawnAvg;
        this.spawnStandardDeviation = spawnStandardDeviation;
        this.x_destinationAvg = x_destinationAvg;
        this.y_destinationAvg = y_destinationAvg;
        this.destinationStandardDeviation = destinationStandardDeviation;
        this.spawnTimeAvg = spawnTimeAvg;
        this.spawnTimeStandardDeviation = spawnTimeStandardDeviation;
    }

    public static PassengerDistributionDefinition createNormalDistDef (int numPassengers, int x_spawnAvg,
                                                                        int y_spawnAvg, int spawnStandardDeviation,
                                                                        int x_destinationAvg, int y_destinationAvg,
                                                                        int destinationStandardDeviation,
                                                                        int spawnTimeAvg,
                                                                        int spawnTimeStandardDeviation) {

        if ( !validateInputs(numPassengers, x_spawnAvg, y_spawnAvg, x_destinationAvg, y_destinationAvg, spawnTimeAvg) ) {
            System.out.println("Invalid Input in UniverseP > PassengerFactory > PassengerDistDef > createNormalDist");
            return new PassengerDistributionDefinition(false, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        return new PassengerDistributionDefinition(false, numPassengers, x_spawnAvg, y_spawnAvg, spawnStandardDeviation,
                x_destinationAvg, y_destinationAvg, destinationStandardDeviation, spawnTimeAvg,
                spawnTimeStandardDeviation);
    }

    public static PassengerDistributionDefinition createUniformDistDef(int numPassengers ) {

        if ( numPassengers < 0 ) {
            System.out.println("Invalid Input in UniverseP > PassengerFactory > PassengerDistDef > createUnifDist");
            return new PassengerDistributionDefinition(true, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        return new PassengerDistributionDefinition(true, numPassengers, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    private static boolean validateInputs(int numPassengers,
                                   int x_spawnAvg, int y_spawnAvg,
                                   int x_destinationAvg, int y_destinationAvg,
                                   int spawnTimeAvg) {

        if ( numPassengers <= 0 ) return false;
        if ( x_spawnAvg < 0 ) return false;
        if ( y_spawnAvg < 0 ) return false;
        if ( x_destinationAvg < 0 ) return false;
        if ( y_destinationAvg < 0 ) return false;
        if ( spawnTimeAvg < 0 ) return false;
        return true;
    }

    boolean isUniformDistribution() { return isUniformDistribution; }
    int getNumPassengers() {
        return numPassengers;
    }
    int getX_spawnAvg() {
        return x_spawnAvg;
    }
    int getY_spawnAvg() {
        return y_spawnAvg;
    }
    int getSpawnStandardDeviation() {
        return spawnStandardDeviation;
    }
    int getX_destinationAvg() {
        return x_destinationAvg;
    }
    int getY_destinationAvg() {
        return y_destinationAvg;
    }
    int getDestinationStandardDeviation() {
        return destinationStandardDeviation;
    }
    int getSpawnTimeAvg() {return spawnTimeAvg;}
    int getSpawnTimeStandardDeviation() {return spawnTimeStandardDeviation;}
}
