/*
1~2 0 삭->0
3~9 1 초승달->1
10 2 오른반달->2
11~15 오른쪽 3->3
16 4 보름달->4
17~23 왼쪽 3->5
24 2 왼쪽반달->6
25~29.5 1->7
2010 1월 1일 17
 */
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Moonphase {
    private String stdtime="2010 01 01 00 00 0";
    private String currtime="2019 05 05 00 00 0";
    private int moon;
    Moonphase(){
        this.calculateMoonphase();
    }

    public static void main(String[] args) {
        new Moonphase();
    }

    public void showMoonphase() {

    }

    public void calculateMoonphase() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd kk mm ss"); //
            Date CurrentDate = format.parse(currtime);
            Date StandardDate = format.parse(stdtime);

            long calDate = CurrentDate.getTime() - StandardDate.getTime();
            long calDateDays = calDate / (24*60*60*1000);

            int i= (int) (calDateDays%29.5);
            switch (i){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    moon = 5;
                    break;
                case 7:
                    moon = 6;
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    moon =7;
                    break;
                case 14:
                case 15:
                    moon =0;
                    break;
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                    moon=1;
                    break;
                case 23:
                    moon=2;
                    break;
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                    moon=3;
                    break;
                case 29:
                    moon=4;
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}