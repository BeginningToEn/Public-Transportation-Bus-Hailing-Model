package UniverseP.ScenarioSimulation;

import UniverseP.PassengerFactory.PassengerTimeTable;
import UniverseP.Units.Passenger;

import java.util.List;
import java.util.Optional;

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
    public Optional< List<Passenger> > getPassengers(int specificTime) {

        //If no passengers are spawned at specificTime return an empty optional
        if ( !myTable.keySet().contains(specificTime) ){
            return Optional.empty();
        }

        return Optional.of(myTable.get(specificTime));
    }
}
