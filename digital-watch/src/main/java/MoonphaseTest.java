import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class MoonphaseTest {

    @Test
    public void getInstance() {
        Moonphase moonphase=Moonphase.getInstance();

        Moonphase.deleteInstance();

        Moonphase moonphase1=Moonphase.getInstance();

        assertNotEquals(moonphase, moonphase1);
    }

    @Test
    public void deleteInstance() {
        Moonphase moonphase=Moonphase.getInstance();

        Moonphase.deleteInstance();

        Moonphase moonphase1=Moonphase.getInstance();

        assertNotEquals(moonphase, moonphase1);
    }

    @Test
    public void showMoonphase() {
    }

    @Test
    private void calculateMoonphase() {
    }
}