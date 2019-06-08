import java.awt.*;
import java.util.*;
import java.util.List;

public class Alarm extends Thread  {
    private List<String> alarmList = new ArrayList<String>();
    private TimeDB timeDB;
    private String alarm; //알람 울리는지 상태 확인
    private String[] time_array;
    private int alarmHour; //최대 23시까지 알람 설정 가능
    private int alarmMinute; //최대 59분까지 알람 설정 가능
    private int currentAlarm=0;
    //private String currtime="2019 05 24 01 00 00"; //테스트용 현재시간
    private int currHour;
    private int currMinute;
    private int currSecond;
    private static boolean alarmState = false; //알람 부저 스테이트

    //싱글턴위해 추가
    private static Alarm instance;

    private Thread thread;

//    Alarm(){
//        this.showAlarm();
//        this.nextAlarm();
//        this.deleteAlarm();
//        this.buzzAlarm();
//        this.stopAlarm();
//    }

    private Alarm(){
        alarmList = new ArrayList<String>();
        alarmList.add(0,null);
        alarmList.add(1,null);
        alarmList.add(2,null);
        alarmList.add(3,null);
        alarm="";
        time_array=null;
        alarmHour=0; //최대 23시까지 알람 설정 가능
        alarmMinute=0; //최대 59분까지 알람 설정 가능
        currentAlarm=0;
        //private String currtime="2019 05 24 01 00 00"; //테스트용 현재시간
        currHour=0;
        currMinute=0;
        alarmState = false; //알람 부저 스테이트

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

    public void addAlarm(int alarmHour, int alarmMinute, int index) {
        //alarm=(alarmHour+" "+alarmMinute);

        alarmList.set(index,alarmHour+" "+alarmMinute+" "+"0");

        //buzzAlarm();
        //알람 설정하면 바로 알람 울리는지 확인
    }


    public void deleteAlarm(int index) {
        alarmList.set(index,null);

    } //입력한 알람번호의 알람을 지운다

    public boolean buzzAlarm() {
        timeDB=TimeDB.getInstance();
        String currtime = timeDB.getTime();
        time_array = currtime.split(" ");
        currHour = Integer.parseInt(time_array[3]);
        currMinute = Integer.parseInt(time_array[4]);
        currSecond = Integer.parseInt(time_array[5]);
        String currHM = (currHour+" "+currMinute+" "+currSecond); //String으로 받은 현재시간에서 시분초 추출
        if(alarmState==false) {
            for (int i = 0; i < 4; i++) {
                if (currHM.equals(alarmList.get(i))) {
                    alarmState = true; //현재 알람과 현재시간이 동일하면 알람상태를 true로 바꾸고
                    //System.out.println(alarmState);
                    //System.out.println(i + " 알람이지롱");
                }
            }
        }
        return alarmState; //알람상태를 리턴
    }

    public void startAlarm(){
        thread = new Alarm();
        thread.start();
    }

    public boolean stopAlarm() {

        alarmState = false; //alarmState가 false로 변경한다
        return alarmState;
        //buzzAlarm();
    }

    public List<String> getAlarmList(){
        return alarmList;
    }

    public void run(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        while(!Thread.currentThread().isInterrupted()){

            try {
                buzzAlarm();
                //System.out.println(alarmState);
                if(alarmState == true) {
                    toolkit.beep();
                }
                Thread.sleep(500);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }

        }
    }

}
