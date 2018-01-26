import UniverseP.PassengerFactory.*;
import UniverseP.ScenarioDefinition;

/**
 * Created by EG OLIVER RC on 8/17/2017.
 */
public class Main {
    public static void main(String[] args) {

        ScenarioDefinition myScenDef = new ScenarioDefinition(50, 139, 157, 100);
        NormalDistributionDefinition myPassDef = NormalDistributionDefinition.createUniformDistDef(20);
        //PassengerDistributionDefinition myPassDef = PassengerDistributionDefinition.createNormalDistDef(100, 69, 110, 40, 80, 90, 20, 60, 25);
        PassengerTimeTableFactory myPassFact = new PassengerTimeTableFactory();

        myPassFact.createNormalDistribution(myScenDef, myPassDef).printAllPassengers();
    }
}
