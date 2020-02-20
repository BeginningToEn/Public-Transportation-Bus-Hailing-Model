package Strategies.ZoneStrategy;

import UniverseP.Units.Bus;

import java.util.HashMap;
import java.util.Map;

/**
 * Splits one hashmap of buses into four different hashmaps
 * such that each of the four groups can be assigned specific responsibilities
 */
public class BusSplit {

    private Map<Integer, Bus> outsideZoneBuses;
    private Map<Integer, Bus> assemblyBuses;
    private Map<Integer, Bus> ferryBuses;
    private Map<Integer, Bus> distributeBuses;

    public BusSplit(Map<Integer, Bus> allBuses, PartialZoneDefinition myZoneDefinition){
        this.outsideZoneBuses = new HashMap<>();
        this.assemblyBuses = new HashMap<>();
        this.ferryBuses = new HashMap<>();
        this.distributeBuses = new HashMap<>();

        setUpBusMaps(allBuses, myZoneDefinition);
    }

    private void setUpBusMaps(Map<Integer, Bus> allBuses, PartialZoneDefinition myZoneDef){

        int count = 0;

        for(Map.Entry<Integer, Bus> myEntry : allBuses.entrySet()){

            if(count < myZoneDef.getOutsideBusCount()){
                this.outsideZoneBuses.put(myEntry.getKey(), myEntry.getValue());

            } else if(count < myZoneDef.getOutsideBusCount() + myZoneDef.getAssemblyBusCount()){
                this.assemblyBuses.put(myEntry.getKey(), myEntry.getValue());

            } else if(count < myZoneDef.getOutsideBusCount()
                    + myZoneDef.getAssemblyBusCount()
                    + myZoneDef.getFerryBusCount()){
                this.ferryBuses.put(myEntry.getKey(), myEntry.getValue());

            } else if(count < myZoneDef.getOutsideBusCount()
                    + myZoneDef.getAssemblyBusCount()
                    + myZoneDef.getFerryBusCount()
                    + myZoneDef.getDistributeBusCount()){
                this.distributeBuses.put(myEntry.getKey(), myEntry.getValue());

            } else {
                break;
            }

            count++;
        }
    }

    public Map<Integer, Bus> getOutsideZoneBuses() {
        return outsideZoneBuses;
    }

    public Map<Integer, Bus> getAssemblyBuses() {
        return assemblyBuses;
    }

    public Map<Integer, Bus> getFerryBuses() {
        return ferryBuses;
    }

    public Map<Integer, Bus> getDistributeBuses() {
        return distributeBuses;
    }
}
