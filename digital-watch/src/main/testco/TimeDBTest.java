import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TimeDBTest {

    @Test
    public void getInstance() {
        TimeDB timeDB = TimeDB.getInstance();

        assertNotNull(timeDB);
    }

    @Test
    public void setTime() {
        TimeDB timeDB = TimeDB.getInstance();

        String time = "2015 11 20 2 15";
        timeDB.setTime(time);
        String tmp=timeDB.getTime();

        time= time+ " 0";
        assertEquals(tmp, time);
    }

    @Test
    public void setMonthMap() {
        TimeDB timeDB = TimeDB.getInstance();

        timeDB.setMonthMap(2019);

        HashMap<Integer, Integer> tmpMap = new HashMap<>();

        tmpMap=timeDB.getMonthMap();

        tmpMap.get(2);
        assertEquals(28+"", tmpMap.get(2)+"");
    }

    @Test
    public void getTime() {
        TimeDB timeDB= TimeDB.getInstance();

        String time="2015 3 30 9 30";
        timeDB.setTime("2015 3 30 09 30");

        time=time+" 0";
        assertEquals(time, timeDB.getTime());
    }

    @Test
    public void getMonthMap() {
        TimeDB timeDB=TimeDB.getInstance();

        timeDB.setMonthMap(2019);
        HashMap<Integer, Integer> tmpMap = new HashMap<>();

        tmpMap=timeDB.getMonthMap();
        assertEquals(tmpMap.get(2)+"", 28+"");
    }

    @Test
    public void updateTime() {
        TimeDB timeDB=TimeDB.getInstance();

        String time = "2015 3 30 9 30 10";
        timeDB.setTime(time);

        timeDB.updateTime();
        String tmp=timeDB.getTime();

        String upTime="2015 3 30 9 30 0";
        assertEquals(upTime, tmp);
    }

    @Test
    public void startUpdateTime() {
    }

    @Test
    public void pauseTimeDB() {
    }

    @Test
    public void run() {
    }
}