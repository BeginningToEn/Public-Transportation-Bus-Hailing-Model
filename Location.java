/**
 * Created by EG OLIVER RC on 8/24/2017.
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

}
