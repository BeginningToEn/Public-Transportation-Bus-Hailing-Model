package UniverseP.ScenarioSimulation;

import UniverseP.TripFactory.TripTimeTable;
import UniverseP.Units.Trip;

import java.util.List;
import java.util.Optional;

/**
 * Created by EG OLIVER RC on 2/17/2018.
 */
public class TripTimeTableReader implements TripSource {

    private TripTimeTable myTable;

    public TripTimeTableReader(TripTimeTable myTable) {
        this.myTable = myTable;
    }

    public ScenarioDefinition getScenarioDef() {
        return myTable.getMyDef();
    }


    @Override
    public Optional< List<Trip> > getPassengers(int specificTime) {

        //If no trips are requested at specificTime return an empty optional
        if ( !myTable.keySet().contains(specificTime) ){
            return Optional.empty();
        }

        return Optional.of(myTable.get(specificTime));
    }
}
