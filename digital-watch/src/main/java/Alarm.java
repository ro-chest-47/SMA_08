import java.util.*;

public class Alarm  {
    List<String> alarmList = new ArrayList<String>();
    private TimeDB timeDB;
    private String alarm; //알람 울리는지 상태 확인
    private int alarmHour; //최대 23시까지 알람 설정 가능
    private int alarmMinute; //최대 59분까지 알람 설정 가능
    private int currentAlarm=0;
    //private String currtime="2019 05 24 01 00 00"; //테스트용 현재시간
    private boolean buzzByAlarm = false; //알람 부저 스테이트

    //싱글턴위해 추가
    private static Alarm instance;

//    Alarm(){
//        this.showAlarm();
//        this.nextAlarm();
//        this.deleteAlarm();
//        this.buzzAlarm();
//        this.stopAlarm();
//    }

    private Alarm(){

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

//     테스트용 코드
//    public static void main(String[] args) {
//        new Alarm();
//    }


    public void showAlarm() {
        if(alarmList.size()<1){
            System.out.println("NO ALARM");
        } //알람 배열에 설정된 알람이 없으면 알람 없음을 출력
        else{
            System.out.println(alarmList.get(currentAlarm));
        } //알람이 있으면 현재 알람을 출력
    }

    public void addAlarm(int alarmHour, int alarmMinute) {
        alarm=(alarmHour+" "+alarmMinute);
        for(int i=0;i<alarmList.size();i++) {
            if (alarm.equals(alarmList.get(i)))  {
                System.out.println("ERROR ALARM");
            }   //같은 시각의 알람을 설정한 경우 에러메시지 출력
            else {
                alarmList.add(alarmHour+" "+alarmMinute);
            } //같은 시각의 알람을 설정하지 않았으면 알람 설정
        }
        nextAlarm();
    }

    public void nextAlarm() {
        //currentAlarm=input; //입력한 알람번호의 알람을 현재 알람으로 설정
        showAlarm();
    }

    public void deleteAlarm() {
        //alarmHour[currentAlarm] = 24;
        //alarmMinute[currentAlarm] = 60;
    } //입력한 알람번호의 알람을 지운다

    public void buzzAlarm() {
        timeDB=TimeDB.getInstance();
        String currtime = timeDB.getTime();
        String currHM = currtime.substring(11,16); //String으로 받은 현재시간에서 시분만 추출
        for(int i=0;i<alarmList.size();i++) {
            if (currHM.equals(alarmList.get(i))) {
                buzzByAlarm = true;
            }
        } //알람리스트의 알람과 현재시간이 동일해지면 buzzByAlarm를 true로 바꾸고
        if (buzzByAlarm = true) {
                System.out.println("BUZZ!");
            } //buzzByAlarm가 true이면 버저를 울린다
        stopAlarm();
    }

    public void stopAlarm() {
        buzzByAlarm = false; //buzzByAlarm가 false로 변경한다
        buzzAlarm();
    }

    public List<String> getAlarmList(){
        return alarmList;
    }

}
