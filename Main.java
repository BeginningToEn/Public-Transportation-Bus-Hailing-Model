import Memory.MemoryPrinter;
import Memory.UniverseMemory;

/**
 * Created by EG OLIVER RC on 8/17/2017.
 */
public class Main {
    public static void Main(String[] args) {
        run();
    }

    public static void run(){
        UniverseMemory myMemory= simUniverse();
        printResults(myMemory);
    }

    public static UniverseMemory simUniverse(){
        return null;
    }

    public static void printResults(UniverseMemory myMemory){
        new MemoryPrinter(myMemory).printMemory();
    }
}
