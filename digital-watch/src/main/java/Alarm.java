import java.util.*;

public class Alarm  {
    List<String> alarmList = new ArrayList<String>();
    private int alarmHour[]={24,24,24,24}; //4개의 알람시를 저장할 배열
    private int alarmMinute[]={60,60,60,60}; //4개의 알람분을 저장할 배열
    private int a_state; //알람 울리는지 상태 확인
    private int alarmH; //최대 23시까지 알람 설정 가능
    private int alarmM; //최대 59분까지 알람 설정 가능
    private int i=0;
    private int currentAlarm=0;
    private String currtime="2019 05 24 01 00 00"; //테스트용 현재시간
    private String currH = currtime.substring(11,13); //String으로 받은 현재시간에서 시 추출
    private String currM = currtime.substring(14,16); //String으로 받은 현재시간에서 분 추출
    private int currentHour=Integer.parseInt(currH); //추출한 시를 int로 형변환
    private int currentMinute=Integer.parseInt(currM); //추출한 분을 int로 형변환

    //싱글턴위해 추가
    private static Alarm instance;
/*
    Alarm(){
        this.showAlarm();
        this.addAlarm();
        this.nextAlarm();
        //this.deleteAlarm();
        this.buzzAlarm();
        this.stopAlarm();
    }
*/

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

    /* 테스트용 코드
    public static void main(String[] args) {
        new Alarm();
    }
     */

    public void showAlarm() {
        if(alarmHour[0]==24 && alarmMinute[0]==60 && alarmHour[1]==24 && alarmMinute[1]==60
                && alarmHour[2]==24 && alarmMinute[2]==60 && alarmHour[3]==24 && alarmMinute[3]==60){
            System.out.println("NO ALARM");
        } //알람 배열에 설정된 알람이 없으면 알람 없음을 출력
        else{
            System.out.println(alarmHour[currentAlarm]+":"+alarmMinute[currentAlarm]);
        } //알람이 있으면 현재 알람을 출력
    }

    public void addAlarm() {
        Scanner scan = new Scanner(System.in);
        int input1 = scan.nextInt();
        int input2 = scan.nextInt();
        alarmH=input1;
        alarmM=input2;
        for(int j=0;j<4;j++) {
            if ((alarmH != alarmHour[j])&&(alarmM != alarmMinute[j]))  {
                alarmHour[i] = alarmH;
                alarmMinute[i] = alarmM;
            }  //같은 시각의 알람을 설정하지 않았으면 알람 설정
            else {
                System.out.println("ERROR ALARM");
            } //같은 시각의 알람을 설정한 경우 에러메시지 출력
        }
        i++; //다음 알람리스트 가리키게 설정
        nextAlarm();
    }

    public void nextAlarm() {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        currentAlarm=input; //입력한 알람번호의 알람을 현재 알람으로 설정
        showAlarm();
    }

    public void deleteAlarm() {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        currentAlarm=input;
        alarmHour[currentAlarm] = 24;
        alarmMinute[currentAlarm] = 60;
    } //입력한 알람번호의 알람을 지운다

    public void buzzAlarm() {
        for(int j=0;j<4;j++) {
            if (alarmHour[j] == currentHour && alarmMinute[j] == currentMinute) {
                a_state = 1;
            }
        } //알람리스트의 알람과 현재시간이 동일해지면 a_state를 1로 바꾸고
        if (a_state == 1) {
                System.out.println("BUZZ!");
            } //a_state가 1이면 버저를 울린다
        stopAlarm();
    }

    public void stopAlarm() {
        a_state = 0; //a_state가 0으로 변경한다
        buzzAlarm();
    }
}