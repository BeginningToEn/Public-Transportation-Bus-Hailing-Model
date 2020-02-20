package Strategies.ZoneStrategy;

import UniverseP.Units.Location;

/**
 * Created by EG OLIVER RC on 2/17/2020.
 */
public class Zone {

    private int minX;
    private int maxX;

    private int minY;
    private int maxY;

    private Location centralStation;

    public Zone(int minX, int maxX, int minY, int maxY, Location centralStation) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.centralStation = centralStation;
    }

    public boolean isInZone(Location myLocation){
        if(myLocation.getX() < minX) return false;
        if(myLocation.getX() > maxX) return false;
        if(myLocation.getX() < minY) return false;
        if(myLocation.getX() > maxY) return false;

        return true;
    }

    public Location getCentralStation() {
        return centralStation;
    }
}
