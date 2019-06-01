import org.junit.Test;

import static org.junit.Assert.*;

public class AlarmTest {

    @Test
    public void getInstance() {
        Alarm alarm = Alarm.getInstance();

        assertNotNull(alarm);
    }

    @Test
    public void deleteInstance() {
        Alarm alarm=Alarm.getInstance();

        alarm.deleteInstance();

        Alarm alarm1=Alarm.getInstance();

        assertNotEquals(alarm, alarm1);
    }

    @Test
    public void addAlarm() {
        Alarm alarm = Alarm.getInstance();

        alarm.addAlarm(1, 2, 3);

        assertEquals(alarm.getAlarmList().get(3), "1 2 0");
    }

    @Test
    public void deleteAlarm() {
        Alarm alarm = Alarm.getInstance();

        alarm.addAlarm(1, 2, 3);
        alarm.deleteAlarm(3);

        assertEquals(alarm.getAlarmList().get(3),null);

    }

    @Test
    public void stopAlarm(){
        Alarm alarm = Alarm.getInstance();

        alarm.stopAlarm();

        assertEquals(alarm.stopAlarm(), false);
    }
}