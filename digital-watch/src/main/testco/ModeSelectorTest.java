import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ModeSelectorTest {


    @Test
    public void setSettingModeList() {
        ModeSelector modeSelector= new ModeSelector();

        ArrayList<String> settingModeList= new ArrayList<String>();
        settingModeList.add("TimeKeeping");
        settingModeList.add("Timer");
        settingModeList.add("Alarm");
        settingModeList.add("Stopwatch");

        modeSelector.setSettingModeList(settingModeList);
        assertEquals(modeSelector.getModeList(), settingModeList);
    }

    @Test
    public void getModeList() {
    }

    @Test
    public void getNextMode() {
        ModeSelector modeSelector = new ModeSelector("TimeKeeping", "Timer", "Alarm", "Stopwatch");

        String mode=modeSelector.getNextMode("TimeKeeping");
        assertEquals(mode, "Timer");
        mode=modeSelector.getNextMode("Timer");
        assertEquals(mode, "Alarm");
        mode=modeSelector.getNextMode("Alarm");
        assertEquals(mode, "Stopwatch");
        mode=modeSelector.getNextMode("Stopwatch");
        assertEquals(mode, "TimeKeeping");
    }

    @Test
    public void getDefaultNextMode() {
        ModeSelector modeSelector=new ModeSelector();

        String nextMode= modeSelector.getDefaultNextMode("Timer");
        assertEquals(nextMode, "Alarm");
        nextMode=modeSelector.getDefaultNextMode("Alarm");
        assertEquals(nextMode, "Stopwatch");
        nextMode=modeSelector.getDefaultNextMode("Stopwatch");
        assertEquals(nextMode, "Tide");
        nextMode=modeSelector.getDefaultNextMode("Tide");
        assertEquals(nextMode, "Moonphase");
        nextMode=modeSelector.getDefaultNextMode("Moonphase");
        assertEquals(nextMode, "TimeKeeping");
        nextMode=modeSelector.getDefaultNextMode("TimeKeeping");
        assertEquals(nextMode, "Timer");
    }

    @Test
    public void setCreateList() {

    }

    @Test
    public void setDeleteList() {
    }
}