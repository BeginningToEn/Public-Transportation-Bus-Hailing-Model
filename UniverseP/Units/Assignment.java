package UniverseP.Units;

/**
 * Created by EG OLIVER RC on 12/13/2019.
 */
public class Assignment {
    private Itinerary myItinerary;
    private int distance;
    private int busID;

    public Assignment(Itinerary myItinerary, int distance, int busID){
        this.myItinerary = myItinerary;
        this.distance = distance;
        this.busID = busID;
    }

    public Assignment(Itinerary myItinerary, int busID){
        this(myItinerary, -1, busID);
    }

    public Itinerary getItinerary(){
        return this.myItinerary;
    }

    public int getDistance(){
        return distance;
    }

    public int getBusID(){
        return this.busID;
    }
}
