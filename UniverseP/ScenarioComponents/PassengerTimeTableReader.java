package UniverseP.ScenarioComponents;

import UniverseP.PassengerFactory.PassengerTimeTable;
import UniverseP.Units.Passenger;

import java.util.List;

/**
 * Created by EG OLIVER RC on 2/17/2018.
 */
public class PassengerTimeTableReader implements PassengerSource {

    private PassengerTimeTable myTable;

    public PassengerTimeTableReader( PassengerTimeTable myTable) {
        this.myTable = myTable;
    }

    public ScenarioDefinition getScenarioDef() {
        return myTable.getMyDef();
    }

    /*public List<Passenger> getPassengers() {
        return myTable.get(time++);
    }*/

    @Override
    public List<Passenger> getPassengers(int specificTime) {
        return myTable.get(specificTime);
    }
}
