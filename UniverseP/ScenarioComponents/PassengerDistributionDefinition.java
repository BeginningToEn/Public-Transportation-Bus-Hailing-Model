package UniverseP.ScenarioComponents;

/**
 * Created by EG OLIVER RC on 1/29/2018.
 */
public interface PassengerDistributionDefinition {
    default boolean isUniform() {
        return false;
    }
}
