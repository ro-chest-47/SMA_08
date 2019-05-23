/*
싱글톤
 */
import java.util.*;

public class Alarm  {
    private int alarmHour[]={0,0,0,0};
    private int alarmMinute[]={0,0,0,0};
    private int buzzerState;
    private int alarmH;
    private int alarmM;
    private int i=0;
    private String currtime="2019 05 24 00 00 00";
    private String currH = currtime.substring(11,13);
    private String currM = currtime.substring(14,16);
    private int currentHour=Integer.parseInt(currH);
    private int currentMinute=Integer.parseInt(currM);

    Alarm(){
        this.showAlarm();
        this.addAlarm();
        //this.nextAlarm();
    }

    public static void main(String[] args) {
        new Alarm();
    }

    public void showAlarm() {
        System.out.println(alarmHour[i]+":"+alarmMinute[i]);
    }

    public void addAlarm() {
        if ((alarmH >= 0) && (alarmH <= 24)) {
            alarmHour[i] = alarmH;
        }
        else {
            System.out.println("Error Alarm Time");
        }
        if ((alarmM >= 0) && (alarmM <= 59)) {
            alarmMinute[i] = alarmM;
        }
        else {
            System.out.println("Error Alarm Time");
        }
        i++;
    }

    public void nextAlarm() {

    }

    public void deleteAlarm() {

    }

    public void buzzAlarm() {
        for(int j=0;j<4;j++){
            if (alarmHour[j] == currentHour && alarmMinute[j] == currentMinute) {
                buzzerState = 1;
            }
        }
    }

    public void stopAlarm() {

    }
}