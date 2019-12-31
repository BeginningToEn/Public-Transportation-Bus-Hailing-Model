package UniverseP.Units;

/**
 * Location is the top level class
 * Actionable location is a wrapper interface around DropOff/PickUpLocation
 * DropOff/PickUpLocation are a subtype of Location and both implement the ActionableLocation Interface
 * DropOff/PickUpLocation object can be saved as type ActionableLocation or Location
 * Location or ActionableLocation cannot be saved as types DropOff/PickUpLocation
 */

public class Location {
    private int x;
    private int y;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        return y == location.y;
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }

    //Coordinates (0,0) are at the top left corner and increase going down and right
    public void moveUp(){
        this.y--;
    }

    public void moveDown(){
        this.y++;
    }

    public void moveLeft(){
        this.x--;
    }

    public void moveRight(){
        this.x++;
    }

    public static int getDistance(Location a, Location b){
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

}
