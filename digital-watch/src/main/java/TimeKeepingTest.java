import org.junit.Test;

import static org.junit.Assert.*;

public class TimeKeepingTest {

    @Test
    public void getInstance() {
        TimeKeeping timeKeeping = TimeKeeping.getInstance();

        TimeKeeping.deleteInstance();

        TimeKeeping timeKeeping1 = TimeKeeping.getInstance();

        assertNotEquals(timeKeeping, timeKeeping1);
    }

    @Test
    public void deleteInstance() {
        TimeKeeping timeKeeping = TimeKeeping.getInstance();

        TimeKeeping.deleteInstance();

        TimeKeeping timeKeeping1 = TimeKeeping.getInstance();

        assertNotEquals(timeKeeping, timeKeeping1);
    }

    @Test
    public void setTime() {
        TimeKeeping timeKeeping= TimeKeeping.getInstance();

        timeKeeping.setTime("2015 3 30 9 15 20");
        String tmp = timeKeeping.getTime();

        assertEquals(tmp, "2015 3 30 9 15 0");
    }

    @Test
    public void getTime() {
        TimeKeeping timeKeeping= TimeKeeping.getInstance();

        timeKeeping.setTime("2015 3 30 9 15 20");
        String tmp = timeKeeping.getTime();

        assertEquals(tmp, "2015 3 30 9 15 0");
    }
}