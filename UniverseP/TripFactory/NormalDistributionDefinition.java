package UniverseP.TripFactory;

/**
 *
 */
public class NormalDistributionDefinition {

    private NormalLocation spawn;
    private NormalLocation destination;
    private int spawnTimeAvg;
    private int spawnTimeStandardDeviation;

    private NormalDistributionDefinition(NormalLocation spawn, NormalLocation destination,
                                         int spawnTimeAvg, int spawnTimeStandardDeviation) {
        this.spawn = spawn;
        this.destination = destination;
        this.spawnTimeAvg = spawnTimeAvg;
        this.spawnTimeStandardDeviation = spawnTimeStandardDeviation;
    }

    public static NormalDistributionDefinition createNormalDistDef (NormalLocation spawn, NormalLocation destination,
                                                                    int spawnTimeAvg, int spawnTimeStandardDeviation) {

        if ( !validateInputs(spawn, destination, spawnTimeAvg) ) {
            throw new IllegalArgumentException();
        }

        return new NormalDistributionDefinition( spawn, destination, spawnTimeAvg, spawnTimeStandardDeviation);
    }

    private static boolean validateInputs(NormalLocation spawn, NormalLocation destination, int spawnTimeAvg) {

        if ( spawn.getX() <  0 ) return false;
        if ( spawn.getY() < 0 ) return false;
        if ( destination.getX() < 0 ) return false;
        if ( destination.getY() < 0 ) return false;
        if ( spawnTimeAvg < 0 ) return false;
        return true;
    }

    NormalLocation getSpawn() {
        return spawn;
    }
    NormalLocation getDestination() {
        return destination;
    }
    int getSpawnTimeAvg() {return spawnTimeAvg;}
    int getSpawnTimeStandardDeviation() {return spawnTimeStandardDeviation;}
}
