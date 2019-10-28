package Analysis;

import Memory.ScenarioMemory;
import Memory.TripMemory;

/**
 * Created by EG OLIVER RC on 10/22/2019.
 */
public class TripAnalysis {

    private ScenarioMemory myLog;

    private double completionRate;

    private double avgTTAssignment;
    private double avgTTPickUp;
    private double avgTTDropOff;
    private double avgTTCompletion;

    private int totalTrips;

    private int assignedCt;
    private int totalTTAssignment;
    private int notAssignedCt;

    private int pickedUpCt;
    private int totalTTPickUp;
    private int notPickedUpCt;

    private int droppedOffCt;
    private int totalTTDropOff;
    private int notDroppedOffCt;

    private int completitionCt;
    private int totalTTCompletion;
    private int notCompletedCt;

    public TripAnalysis(ScenarioMemory myLog){
        this.myLog = myLog;
        sumData();
        calcPercentages();
    }

    public void sumData(){
        for(TripMemory log : myLog.getTrips().values()){
            if(log.getTimeAssigned().isPresent()){
                assignedCt++;
                totalTTAssignment += log.getTimeAssigned().get() - log.getTimeRequested();
            } else {
                notAssignedCt++;
                continue;
            }

            if(log.getTimePickedUp().isPresent()){
                pickedUpCt++;
                totalTTPickUp += log.getTimePickedUp().get() - log.getTimeAssigned().get();
            } else {
                notPickedUpCt++;
                continue;
            }

            if(log.getTimeDroppedOff().isPresent()){
                droppedOffCt++;
                totalTTDropOff += log.getTimeDroppedOff().get() - log.getTimePickedUp().get();
            } else {
                notDroppedOffCt++;
                continue;
            }

            completitionCt++;
            totalTTCompletion += log.getTimeDroppedOff().get() - log.getTimeRequested();
        }
    }

    public void calcPercentages(){
        avgTTAssignment = totalTTAssignment / assignedCt;
        avgTTPickUp = totalTTPickUp / pickedUpCt;
        avgTTDropOff = totalTTDropOff / droppedOffCt;
        avgTTCompletion = totalTTCompletion / completitionCt;
    }
}
