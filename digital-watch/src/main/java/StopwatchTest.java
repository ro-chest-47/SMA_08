import javafx.scene.paint.Stop;
import org.junit.Test;

import static org.junit.Assert.*;

public class StopwatchTest {

    @Test
    public void getInstance() {
        Stopwatch stopwatch=Stopwatch.getInstance();

        Stopwatch.deleteInstance();

        Stopwatch stopwatch1=Stopwatch.getInstance();

        assertNotEquals(stopwatch, stopwatch1);
    }

    @Test
    public void deleteInstance() {
        Stopwatch stopwatch=Stopwatch.getInstance();

        Stopwatch.deleteInstance();

        Stopwatch stopwatch1=Stopwatch.getInstance();

        assertNotEquals(stopwatch, stopwatch1);
    }

    @Test
    public void getRunState() {
        Stopwatch stopwatch =Stopwatch.getInstance();

        assertEquals(0, stopwatch.getRunState());
    }

    @Test
    public void getZeroSate() {
        Stopwatch stopwatch=Stopwatch.getInstance();

        assertEquals(0, stopwatch.getZeroSate() );
    }

    @Test
    public void getTime() {
        Stopwatch stopwatch=Stopwatch.getInstance();

        stopwatch.setStopwatch("11 1 2 3");

        String tmp=stopwatch.getTime();

        assertEquals("11 1 2 3", tmp);
    }

    @Test
    public void setStopwatch() {
        Stopwatch stopwatch=Stopwatch.getInstance();

        stopwatch.setStopwatch("11 1 2 3");

        String tmp=stopwatch.getTime();

        assertEquals("11 1 2 3", tmp);
    }

    @Test
    public void startStopwatch() {
        Stopwatch stopwatch=Stopwatch.getInstance();

        stopwatch.startStopwatch();
        int tmp=stopwatch.getRunState();

        assertEquals(tmp, 1);
    }

    @Test
    public void recordStopwatch() {
        Stopwatch stopwatch = Stopwatch.getInstance();

        stopwatch.setStopwatch("10 2 12 23");

        String tmp=stopwatch.recordStopwatch();

        assertEquals(tmp, "10 2 12 23");
    }

    @Test
    public void pauseStopwatch() {
        Stopwatch stopwatch=Stopwatch.getInstance();
        stopwatch.startStopwatch();
        stopwatch.pauseStopwatch();

        int runState = stopwatch.getRunState();

        assertEquals(runState, 0);
    }

    @Test
    public void resetStopwatch() {
        Stopwatch stopwatch=Stopwatch.getInstance();

        stopwatch.startStopwatch();
        stopwatch.pauseStopwatch();
        stopwatch.resetStopwatch();

        assertEquals(1, stopwatch.getZeroSate());
    }

    @Test
    public void updateTime() {
        Stopwatch stopwatch=Stopwatch.getInstance();

        stopwatch.setStopwatch("11 11 11 1");
        stopwatch.updateTime();
        String tmp=stopwatch.recordStopwatch();

        assertEquals(tmp, "11 11 11 2");
    }

}