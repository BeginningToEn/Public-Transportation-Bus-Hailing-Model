package UniverseP.Memory;

import UniverseP.Units.Location;
import UniverseP.Units.Trip;

import java.util.List;
import java.util.Optional;

/**
 * Created by EG OLIVER RC on 1/21/2019.
 */
public class PassengerMemory {

    private Trip passenger;
    private Optional<Integer> pickUpTurn;
    private Optional<Integer> dropOfTurn;
    private List<Location> positionHistory;

    //turned passenger was assigned to be picked up by a bus
    //down the line need to change it to a list of ints to handle reasignments
    private Optional<Integer> turnAssigned;

    public PassengerMemory(Trip trip){
        this.passenger = trip;
    }

}
