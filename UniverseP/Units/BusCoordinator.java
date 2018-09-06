package UniverseP.Units;

import java.util.Set;
import java.util.HashSet;

/**
 * This class is used to maintain an up-to-date list of availableBuses and assignedBuses
 */
public class BusCoordinator {

    private Set<Integer> allBusesByIDs;
    private Set<Integer> availableBusesByIDs;
    private Set<Integer> assignedBusesByID;

    private BusCoordinator(Set<Integer> allBusesByIDs){
        this.allBusesByIDs = allBusesByIDs;
        this.availableBusesByIDs = new HashSet<>();
        this.assignedBusesByID  = new HashSet<>();
    }

    public BusCoordinator createBusCoordinator(Set<Integer> allBusesByIDs) {
        BusCoordinator output = new BusCoordinator(allBusesByIDs);
        output.moreAssignments(allBusesByIDs);      //on creation all buses are available
        return output;
    }

    public Set<Integer> getAvailable(){
        return availableBusesByIDs;
    }

    public Set<Integer> getAssigned(){
        return assignedBusesByID;
    }

    //updates lists when more assignments are made
    public void moreAssignments(Set<Integer> additionalAssignments) {
        for(int BusID : additionalAssignments){
            this.assignedBusesByID.add(BusID);
            this.availableBusesByIDs.remove(BusID);
        }
    }

    //updates lists when buses become available
    public void lessAssignments(Set<Integer> additionalAvailable){
        for(int BusID : additionalAvailable){
            this.assignedBusesByID.remove(BusID);
            this.availableBusesByIDs.add(BusID);
        }
    }
}
