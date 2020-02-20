package Strategies.ZoneStrategy;

import UniverseP.Units.Location;

/**
 * Created by EG OLIVER RC on 2/17/2020.
 */
public class PartialZoneDefinition {
    private int outsideBusCount;
    private int assemblyBusCount;
    private int ferryBusCount;
    private int distributeBusCount;

    private Zone assemblyZone;
    private Zone distributeZone;

    public PartialZoneDefinition(int outsideBusCount, int assemblyBusCount, int ferryBusCount,
                                 int distributeBusCount, Zone assemblyZone, Zone distributeZone) {
        this.outsideBusCount = outsideBusCount;
        this.assemblyBusCount = assemblyBusCount;
        this.ferryBusCount = ferryBusCount;
        this.distributeBusCount = distributeBusCount;
        this.assemblyZone = assemblyZone;
        this.distributeZone = distributeZone;
    }

    public int getOutsideBusCount() {
        return outsideBusCount;
    }

    public int getAssemblyBusCount() {
        return assemblyBusCount;
    }

    public int getFerryBusCount() {
        return ferryBusCount;
    }

    public int getDistributeBusCount() {
        return distributeBusCount;
    }

    public Zone getAssemblyZone() {
        return assemblyZone;
    }

    public Zone getDistributeZone() {
        return distributeZone;
    }
}
