package Strategies.ZoneStrategy;

import UniverseP.Units.ActionLog;
import UniverseP.Units.ActionType;
import UniverseP.Units.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses data from action log and outputs which passengers arrived at the assembly point and distribution point
 */
public class DropOffDataParser {

    private List<Integer> arrivedAtAssembly;
    private List<Integer> arrivedAtDistribution;

    private PartialZoneDefinition myDef;

    public DropOffDataParser(PartialZoneDefinition myDef) {
        this.myDef = myDef;
    }

    public void parseData(Iterable<ActionLog> actionsLogs){
        arrivedAtAssembly = new ArrayList<>();
        arrivedAtDistribution = new ArrayList<>();

        for(ActionLog myLog : actionsLogs){
            if(myLog.getMyType() != ActionType.DROPOFF){

                Location dropOff = myLog.getMyLocation();

                if( droppedOffAtAssembly(dropOff)){
                    arrivedAtAssembly.add(myLog.getTripID());
                } else if(droppedOffAtDistribution(dropOff)){
                    arrivedAtDistribution.add(myLog.getTripID());
                }
            }
        }
    }

    public List<Integer> getArrivedAtAssembly() {
        return arrivedAtAssembly;
    }

    public List<Integer> getArrivedAtDistribution() {
        return arrivedAtDistribution;
    }

    private boolean droppedOffAtAssembly(Location myLocation){
        return myLocation.equals(myDef.getAssemblyZone().getCentralStation());
    }

    private boolean droppedOffAtDistribution(Location myLocation){
        return myLocation.equals(myDef.getDistributeZone().getCentralStation());
    }
}
