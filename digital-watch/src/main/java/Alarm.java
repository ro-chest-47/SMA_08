import java.util.*;

public class Alarm  {
    private int alarmHour[]={13,13,13,13};
    private int alarmMinute[]={60,60,60,60};
    private int buzzerState;
    private int alarmH;
    private int alarmM;
    private int i=0;
    private String currtime="2019 05 24 01 00 00";
    private String currH = currtime.substring(11,13);
    private String currM = currtime.substring(14,16);
    private int currentHour=Integer.parseInt(currH);
    private int currentMinute=Integer.parseInt(currM);

    //싱글턴위해 추가
    private static Alarm instance;

    Alarm(){
        this.showAlarm();
        this.addAlarm();
        this.nextAlarm();
        this.deleteAlarm();
        for(int j=0;j<4;j++) {
            if (alarmHour[j] == currentHour && alarmMinute[j] == currentMinute) {
                buzzerState = 1;
            }
        }
        this.buzzAlarm();
        this.stopAlarm();
    }


    //싱글턴위해 추가
    public static Alarm getInstance(){
        if(instance==null){
            instance=new Alarm();
        }
        return instance;
    }

    //싱글턴위해 추가
    public static void deleteInstance(){
        instance=null;
    }


//    public static void main(String[] args) {
//        new Alarm();
//    }

    public void showAlarm() {
        if(alarmHour[0]==13 && alarmMinute[0]==60 && alarmHour[1]==13 && alarmMinute[1]==60
                && alarmHour[2]==13 && alarmMinute[2]==60 && alarmHour[3]==13 && alarmMinute[3]==60){
            System.out.println("no alarm");
        }
        else{
            System.out.println(alarmHour[i]+":"+alarmMinute[i]);
        }
    }

    public void addAlarm() {
        if (i != 0){i++;}
        System.out.println("addAlarm");
        Scanner scan = new Scanner(System.in);
        int input1 = scan.nextInt();
        int input2 = scan.nextInt();
        alarmH=input1;
        alarmM=input2;

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
        showAlarm();
    }

    public void nextAlarm() {
        System.out.println("nextAlarm");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        i=input;
        showAlarm();
    }

    public void deleteAlarm() {
        System.out.println("deleteAlarm");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        i=input;
        alarmHour[i] = 13;
        alarmMinute[i] = 60;
    }

    public void buzzAlarm() {
        for(int j=0;j<4;j++){
            if (buzzerState == 1) {
                System.out.println("buzz");
            }
        }
        stopAlarm();
    }

    public void stopAlarm() {
        buzzerState = 0;
    }
}