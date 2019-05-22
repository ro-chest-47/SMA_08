/*
싱글톤
 */
/*
public class Alarm  {
    public int alarmHour[]={0,0,0,0};
    public int alarmMinute[]={0,0,0,0};
    public int alarmSecond[]={0,0,0,0};
    public int i = 0;
    public int buzzerState;

    /*public Alarm() {
        alarmHour = 0;
        alarmMinute = 0;
        alarmSecond = 0;
    }

    public Alarm(int alarmH, int alarmM, int alarmS) {
        addAlarm(alarmH,alarmM,alarmS);
    }

    public void showAlarm() {

    }

    public void addAlarm(int alarmH,int alarmM,int alarmS) {
        if ((alarmH >= 0) && (alarmH <= 24))
            alarmHour[i] = alarmH;
        else
            System.out.println("Error Alarm Time");

        if ((alarmM >= 0) && (alarmM <= 59))
            alarmMinute[i] = alarmM;
        else
            System.out.println("Error Alarm Time");

        if ((alarmS >= 0) && (alarmS <= 59))
            alarmSecond[i] = alarmS;
        else
            System.out.println("Error Alarm Time");
        i++;
    }

    public void nextAlarm() {

    }

    public void deleteAlarm() {

    }

    public void buzzAlarm() {
        if (alarmHour == hour && alarmMinute == minute && alarmSecond == second) {
            buzzerState=1;
        }
        while (buzzerState==1){
            System.out.println("buzz");
        }
    }

    public void stopAlarm() {

    }
}
*/