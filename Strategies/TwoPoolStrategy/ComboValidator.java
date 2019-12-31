package Strategies.TwoPoolStrategy;

import UniverseP.Units.Trip;

import static UniverseP.ScenarioSimulation.ScenarioDefinition.getDistance;

/**
 * Created by EG OLIVER RC on 12/17/2019.
 */
public class ComboValidator {

    private static int maxDistance = 20;

    public static boolean isValidCombo(Trip a, Trip b){
        return isValidDistance(a, b);
    }

    public static boolean isValidDistance(Trip a, Trip b){
        ////////////////////////////
        return getDistance(a.getSpawn(), b.getSpawn()) + getDistance(a.getDestination(), b.getDestination()) <= maxDistance;
    }
}
