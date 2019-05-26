import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeleteModeTest {

    @Test
    public void setDeleteList() {

        DeleteMode deleteMode = new DeleteMode();
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Stopwatch");
        arrayList.add("Alarm");

        Stopwatch stopwatch=Stopwatch.getInstance();
        Alarm alarm=Alarm.getInstance();

        deleteMode.setDeleteList(arrayList);

        Stopwatch stopwatch1 =Stopwatch.getInstance();
        Alarm alarm1=Alarm.getInstance();

        assertNotEquals(stopwatch,stopwatch1);
        assertNotEquals(alarm, alarm1);
    }
}