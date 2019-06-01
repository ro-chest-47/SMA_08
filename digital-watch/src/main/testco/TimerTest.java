import org.junit.Test;

import static org.junit.Assert.*;

public class TimerTest {

    @Test
    public void getInstance() {
        Timer timer=Timer.getInstance();
        Timer.deleteInstance();

        Timer timer1=Timer.getInstance();
        assertNotEquals(timer, timer1);
    }

    @Test
    public void deleteInstance() {
        Timer timer=Timer.getInstance();
        Timer.deleteInstance();

        Timer timer1=Timer.getInstance();
        assertNotEquals(timer, timer1);
    }

    @Test
    public void getRunState() {
    }

    @Test
    public void getZeroState() {
        Timer timer=Timer.getInstance();
        assertEquals(0, timer.getZeroState());
    }

    @Test
    public void getTime() {
        Timer timer=Timer.getInstance();
        timer.setTimer("11 9 5");
        String tmp=timer.getTime();
        assertEquals("11 9 5", tmp);
    }

    @Test
    public void pauseTimer() {
        Timer timer=Timer.getInstance();
        timer.startTimer();
        timer.pauseTimer();

        int runState = timer.getRunState();

        assertEquals(runState, 0);
    }

    @Test
    public void setTimer() {
        Timer timer=Timer.getInstance();
        timer.setTimer("11 9 5");
        String tmp=timer.getTime();
        assertEquals("11 9 5", tmp);
    }

    @Test
    public void startTimer() {
        Timer timer=Timer.getInstance();

        timer.startTimer();
        int tmp=timer.getRunState();

        assertEquals(tmp, 1);
    }

    @Test
    public void resetTimer() {
        Timer timer=Timer.getInstance();
        timer.startTimer();
        timer.pauseTimer();
        timer.resetTimer();
        assertEquals(1, timer.getZeroState());
    }

    @Test
    public void buzzTimer() {
    }

    @Test
    public void updateTime() {
        Timer timer=Timer.getInstance();

        timer.setTimer("11 11 11");
        for(int i=0;i<10;i++)
            timer.updateTime();
        String tmp=timer.getTime();

        assertEquals(tmp, "11 11 10");
    }
}