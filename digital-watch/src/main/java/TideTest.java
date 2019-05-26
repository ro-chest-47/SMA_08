import org.junit.Test;

import static org.junit.Assert.*;

public class TideTest {

    @Test
    public void getInstance() {
        Tide tide = Tide.getInstance();

        Tide.deleteInstance();

        Tide tide1=Tide.getInstance();

        assertNotEquals(tide, tide1);
    }

    @Test
    public void deleteInstance() {
        Tide tide = Tide.getInstance();

        Tide.deleteInstance();

        Tide tide1=Tide.getInstance();

        assertNotEquals(tide, tide1);
    }

    @Test
    public void showTide() {
    }

    @Test
    public void getNextTide() {
    }

    @Test
    public void getTideList() {
    }
}