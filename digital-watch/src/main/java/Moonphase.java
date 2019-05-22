/*
1~2 0 삭
3~9 1 초승달
10 2 반달
11~15 오른쪽 3
16 4 보름달
17~23 왼쪽 3
24 2 반달
25~29.5 1
2010 1월 1일 17
 */
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Moonphase {
    private String time1="2010 01 01 00 00 0";
    private String time2="2019 05 22 00 00 0";
    public void showMoonphase() {

    }

    public void calculateMoonphase() {
        try {
        SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd kk mm ss");
            Date FirstDate = format.parse(time1);
            Date SecondDate = format.parse(time2);

            long calDate = FirstDate.getTime() - SecondDate.getTime();

            long calDateDays = calDate / (24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}