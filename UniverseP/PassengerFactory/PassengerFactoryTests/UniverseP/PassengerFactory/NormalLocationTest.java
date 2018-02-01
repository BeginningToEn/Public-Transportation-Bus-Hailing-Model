package UniverseP.PassengerFactory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by EG OLIVER RC on 1/25/2018.
 */
public class NormalLocationTest {
    @Test
    public void equals() throws Exception {
        assertTrue(new NormalLocation(25, 35, 10).equals(new NormalLocation(25, 35, 10)));
        assertTrue(new NormalLocation(55, 10, 27).equals(new NormalLocation(55, 10, 27)));
        assertFalse(new NormalLocation(55, 10, 27).equals(null));
        assertFalse(new NormalLocation(55, 10, 27).equals(new NormalLocation(55, 10, 227)));
        assertFalse(new NormalLocation(55, 10, 27).equals(new NormalLocation(55, 9, 27)));
        assertFalse(new NormalLocation(55, 10, 27).equals(new NormalLocation(100, 10, 27)));
    }
}