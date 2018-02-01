import UniverseP.Passenger;
import UniverseP.PassengerFactory.*;
import UniverseP.ScenarioDefinition;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by EG OLIVER RC on 8/17/2017.
 */
public class Main {
    public static void main(String[] args) {

        //ScenarioDefinition myScenDef = new ScenarioDefinition(50, 139, 157, 100);
        //NormalDistributionDefinition myPassDef = NormalDistributionDefinition.createUniformDistDef(20);
        //PassengerDistributionDefinition myPassDef = PassengerDistributionDefinition.createNormalDistDef(100, 69, 110, 40, 80, 90, 20, 60, 25);
        //PassengerTimeTableFactory myPassFact = new PassengerTimeTableFactory();

        //myPassFact.createNormalDistribution(myScenDef, myPassDef).printAllPassengers();

        ScenarioDefinition myScenDef = new ScenarioDefinition( 139, 157, 100, 10, 100);
        NormalLocation mySpawn = new NormalLocation(27, 32, 10);
        NormalLocation myDestination = new NormalLocation(109, 128, 15);
        NormalDistributionDefinition myDistDef = NormalDistributionDefinition.createNormalDistDef(mySpawn, myDestination, 30, 10);
        PassengerTimeTableFactory myFactory= new PassengerTimeTableFactory();

        PassengerTimeTable myTable = myFactory.createNormalDistribution(myScenDef, myDistDef);
        myTable.printAllPassengers();
    }
}
