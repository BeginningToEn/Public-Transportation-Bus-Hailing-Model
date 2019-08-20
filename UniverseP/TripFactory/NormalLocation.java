package UniverseP.TripFactory;

/**
 * C
 */

public class NormalLocation {
    private int x;
    private int y;
    private int standardDeviation;

    public NormalLocation(int x, int y, int standardDeviation){
        this.x = x;
        this.y = y;
        this.standardDeviation = standardDeviation;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getStDev() { return standardDeviation; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NormalLocation location = (NormalLocation) o;

        if (x != location.x) return false;
        if (y != location.y) return false;
        return standardDeviation == location.standardDeviation;

    }
}
