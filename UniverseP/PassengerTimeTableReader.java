package UniverseP;

import UniverseP.PassengerFactory.PassengerTimeTable;

import java.util.List;

/**
 * Created by EG OLIVER RC on 2/17/2018.
 */
public class PassengerTimeTableReader implements PassengerSource {

    private int time;
    private PassengerTimeTable myTable;

    public PassengerTimeTableReader( PassengerTimeTable myTable) {
        this.time = 0;
        this.myTable = myTable;
    }

    @Override
    public List<Passenger> getPassengers() {
        return myTable.get(time++);
    }

    public List<Passenger> getPassengers(int specificTime) {
        return myTable.get(specificTime);
    }
}
