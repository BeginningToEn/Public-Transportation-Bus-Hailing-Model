/**
 * Assigns passengers and sends their location to buses
 * Assigns passengers based on different strategies
 */

import java.lang.Math;

public class Dispatch {
    //needs to maintain an up to date available buses list for UberStrategy;

    private Strategy myUberStrategy;

    public Dispatch(){

    }

    public static int getDistance(int x_1, int y_1, int x_2, int y_2){
        return Math.abs( x_1 - x_2 ) + Math.abs( y_1 - y_2 );
    }
}
