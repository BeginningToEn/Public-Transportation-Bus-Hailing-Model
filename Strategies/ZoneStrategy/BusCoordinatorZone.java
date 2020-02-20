package Strategies.ZoneStrategy;

/**
 * Created by EG OLIVER RC on 2/11/2020.
 */

import UniverseP.ScenarioSimulation.BusCoordinator;

import java.util.Set;
import java.util.HashSet;

public class BusCoordinatorZone {

    private BusCoordinator parentCoordinator;

    private Set<Integer> allBusesByIDs;
    private Set<Integer> availableBusesByIDs;
    private Set<Integer> availableBusesByIDsZone1;
    private Set<Integer> allBusIDsZone1;
    private Set<Integer> availableBusesByIDsZone2;
    private Set<Integer> allBusIDsZone2;
    private Set<Integer> availableBusesByIDsFixedPath;
    private Set<Integer> allBusIDsFixedPath;
    private Set<Integer> allAssignedBusesByID;

    private BusCoordinatorZone(BusCoordinator parentCoordinator, int zone1, int zone2, int fixedPathFerry){

        this.parentCoordinator = parentCoordinator;

        if(zone1 + zone2 + fixedPathFerry> parentCoordinator.size()) {
            //check validity on strategy level
            System.out.println("Invalid zone conditions");
            return;
        }

        for(int busID : allBusesByIDs){
            if(availableBusesByIDsZone1.size() < zone1){
                availableBusesByIDsZone1.add(busID);
                allBusIDsZone1.add(busID);
            } else if(availableBusesByIDsZone2.size() < zone2){
                availableBusesByIDsZone2.add(busID);
                allBusIDsZone2.add(busID);
            } else if(availableBusesByIDsFixedPath.size() < fixedPathFerry){
                availableBusesByIDsFixedPath.add(busID);
                allBusIDsFixedPath.add(busID);
            } else {
                break;
            }
        }


        this.allBusesByIDs = allBusesByIDs;
        this.availableBusesByIDs = new HashSet<>();
        this.allAssignedBusesByID = new HashSet<>();
    }

    /*public static BusCoordinatorZone createBusCoordinator(Set<Integer> allBusesByIDs) {
        BusCoordinatorZone output = new BusCoordinatorZone(allBusesByIDs);
        output.recordAvailable(allBusesByIDs);      //on creation all buses are available
        return output;
    }*/

    public Set<Integer> getAvailable(){
        return availableBusesByIDs;
    }

    public Set<Integer> getAvailableZone1() {
        return availableBusesByIDsZone1;
    }

    public Set<Integer> getAvailableZone2() {
        return availableBusesByIDsZone2;
    }

    public Set<Integer> getAvailableFixedPath() {
        return availableBusesByIDsFixedPath;
    }

    public Set<Integer> getAssigned(){
        return allAssignedBusesByID;
    }

    //updates lists when more assignments are made
    public void recordAssignments(Set<Integer> additionalAssignments) {
        for(int BusID : additionalAssignments){
            this.allAssignedBusesByID.add(BusID);
            this.availableBusesByIDs.remove(BusID);
            availableBusesByIDsZone1.remove(BusID);
            availableBusesByIDsZone2.remove(BusID);
            availableBusesByIDsFixedPath.remove(BusID);
        }
    }

    public void recordAssignments(int additionalAssigned) {
        this.allAssignedBusesByID.add(additionalAssigned);
        this.availableBusesByIDs.remove(additionalAssigned);
        availableBusesByIDsZone1.remove(additionalAssigned);
        availableBusesByIDsZone2.remove(additionalAssigned);
        availableBusesByIDsFixedPath.remove(additionalAssigned);
    }

    //updates lists when buses become available
    public void recordAvailable(Set<Integer> additionalAvailable){
        for(int BusID : additionalAvailable){

            if(allBusIDsZone1.contains(BusID)){
                availableBusesByIDsZone1.add(BusID);
            } else if(allBusIDsZone2.contains(BusID)){
                availableBusesByIDsZone2.add(BusID);
            } else if(allBusIDsFixedPath.contains(BusID)){
                availableBusesByIDsFixedPath.add(BusID);
            } else {
                parentCoordinator.recordAvailable(BusID);
            }
            
            this.allAssignedBusesByID.remove(BusID);
            this.availableBusesByIDs.add(BusID);
        }
    }

    public void recordAvailable(int additionalAvailable){
        this.allAssignedBusesByID.remove(additionalAvailable);
        this.availableBusesByIDs.add(additionalAvailable);
    }

    public boolean busAvailable(){
        return !availableBusesByIDs.isEmpty();
    }

}
