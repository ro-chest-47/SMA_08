import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.jws.WebParam;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import info.clearthought.layout.*;
/*
 * Created by JFormDesigner on Sat May 18 12:01:48 KST 2019
 */

/**
 * @author haesung
 */
public class System extends JFrame {
    private ModeSelector modeSelector;
    /*
    아직 클래스 적용안됨
     */
    private Alarm[] alarmList;
    private Tide[] tideList;
    private Timer timer;
    private Timekeeping timekeeping;
    private Alarm alarm;
    private Stopwatch stopwatch;
    private Tide tide;
    private Moonphase moonphase;
    private int userInput; //int값인지 불확실
    private String currentMode; //원래 int값인데 String으로 일단 수정
    private boolean buzzByAlarm;

    /*
    새롭게 필요해 보이는 필드들
     */
//    private String[] selectedModes; //원래 String[]인데 arraylist로 수정
    private ArrayList<String> selectedModes = new ArrayList<>();     //modeselector로 넘겨줄선택된 모드들을 나타냄, 그러니까 현재 선택된 모드들
    private TimeDB timeDB; //System이 TimeDB에서 값을 받아오는게 존재
    private boolean timekeepingAdjustState=false; //timekeeping에서 adjust버튼을 누를경우 state가 true로 바뀌어 시간을 조정중임을 알림
    private boolean timerAdjustState=false; //timer에서 adjust버튼을 누를경우 state가 true로 바뀌면서 timer조정가능
    private int timerRunState=0; //시퀀스다이어그램상에서 int이길래 일단 int로 설정 근데 boolean이 더 맞는것같음
    private boolean alaramAdjustState=false; //alarm에서 adjust버튼을 누를경우 state가 true로 바뀌면서 alarm조정가능
    private boolean alarmCanAddState=false; //alarm에 alarm을 더 추가시킬 수 있을경우


    public System() {

        initComponents();
        modeSelector=new ModeSelector(); //초기모드 설정을 모드셀렉터에서 진행함
    }

    /*
    새롭게 추가된 메서드들
     */

    //액션리스너의 경우 버튼에 직접 다는게 가능하긴함
    private void btnAdjustActionPerformed(ActionEvent e) {
        // TODO add your code here
        if(currentMode.equals("TimeKeeping")){
            //adjust 페이즈가 아닐경우 adjusttime으로 진행
            if(!timekeepingAdjustState) {
                //currnetMode가 timekeeping일경우 adjust버튼을 누른다면
                reqAdjustTime(); //으로 진행
            }
            //adjust페이즈 일경우 adjustbutton을 누르면 adjust페이즈를 종료
            else{
                endAdjustTime();
            }
        }
        else if(currentMode.equals("Timer")){
            //adjust페이즈가 아닐경우 adjust페이즈로 진입
            if(!timerAdjustState){
                reqSetTimer();
            }
            //adjust페이즈에서 빠져나올때
            else{
                endSetTimer();
            }
        }
        else if(currentMode.equals("Alarm")){
            //alarm모드에서 알람을 조정중이지 않은 경우
            if(!alaramAdjustState) {
                //adjsut버튼을 누를경우 addAlarm실행
                reqAddAlarm();
            }
            //alamr모드에서 알람을 조정중인데 빠져나오려고 할 경우
            else{
                endAddAlarm();
            }
        }
    }

    private void btnResetActionPerformed(ActionEvent e) {
        // TODO add your code here
        if(currentMode.equals("TimeKeeping")){
            //뭔가 들어갈게 있겠지
        }
        else if(currentMode.equals("Timer")){
            //Timer모드이긴한데 현재 타이머가 돌아가는중일경우 reset버튼을 누르면 일단 타이머를 멈춤
            if(timerRunState==1){
                //timer.pauseTimer(); pauseTimer에서 현재 system의 timerRunState=0;으로 만들어주는게 필요
            }
            //timer모드이고 현재타이머의 스테이트가 1 이 아니면(0이면: a.k.a timer가 정지해있는경우) reset가능
            else{
                reqResetTimer();
            }
        }
        else if(currentMode.equals("Alarm")){
            //alarm모드인데 현재 알람 조정상태가 아닌경우 reset버튼을 누르면 설정된 알람을 제거
            if(!alaramAdjustState){
                reqDeleteAlarm();
            }
            /*
            현재 바로 아래의 else부분의 usecase가 정의되어 있지 않음
             */
            //alarm모드인데 현재 알람 조정상태일경우 reset버튼을 누르면 현재 설정하려고하는 알람을 00:00:00으로 초기화???
            else{
                //(대충 알람을 시간을 0으로 초기화한다는 내용)
            }
        }
    }

    private void btnModeActionPerformed(ActionEvent e) {
        // TODO add your code here
        if(currentMode.equals("TimeKeeping")){
            //TimeKeeping모드이고 adjust time일때 mode버튼을 누르면 cursor가 바뀜
            if(timekeepingAdjustState) {
                //changeCurosr 실행시 커서의 위치를 변경시켜주는것도 필요
                changeCursor();
            }
            //timekeeping모드이고 adjusttime이 아닐때 mode버튼 누르면 다음 모드로 감
            else{
                //(대충 다음 모드로 간다는 코드)
            }
        }
        else if(currentMode.equals("Timer")){
            //Timer모드이고 timer를 adjust중일때 mode버튼을 누르면 cursor가 바뀜
            if(timerAdjustState) {
                //changeCurosr 실행시 커서의 위치를 변경시켜주는것도 필요
                changeCursor();
            }
            //timer모드이고 settimer모드가 아닐때 mode버튼을 누르면 다음모드로 감
            else{
                //(대충 다음 모드로 간다는 코드)
            }
        }
        else if(currentMode.equals("Alarm")){
            //alarm모드이고 alarm이 설정가능상태일경우 mode버튼을 누르면 cursor가 바뀜
            if(alaramAdjustState){
                changeCursor();
            }
            //alarm모드이고 alarm설정상태가 아닐때 mode버튼을 누르면 다음 모드로
            else {
                //(대충 다음 모드로 간다는 코드)
            }
        }
    }

    private void btnStartActionPerformed(ActionEvent e) {
        // TODO add your code here
        if(currentMode.equals("TimeKeeping") && timekeepingAdjustState){
            //현재 커서의 위치가 어디인지를 알수있는 방법 필요
            increaseTime(); // TimeKeeping모드이고 adjust모드일때 현재 커서위치의 값을 증가시킴
        }
        else if(currentMode.equals("Timer")){
            //Timer모드이고 adjust모드일때 start버튼을 누르면 현재 커서 위치의 값 증가
            if(timerAdjustState && timerRunState==0) {
                //현재 커서의 위치가 어디인지를 알수있는 방법 필요
                increaseTime();
            }
            /* 중요?
            전제 조건 = Timer상태이고 adjust모드가 아니여야 timer의 start가 가능하다
             */
            //Timer모드이고 adjust모드가 아닐때 start버튼을 누르면 타이머가 동작
            else if(!timerAdjustState && timerRunState==0){ //tiemerAdjustState가 항상 true라는 경고문, 일단 참고
                reqStartTimer();
            }
            //Timer모드이고 adjust모드가 아니고 Timer가 동작중일때 start버튼을 누르면 타이머가 멈춤
            else if(!timerAdjustState && timerRunState==1){
                reqPauseTimer();
            }
        }
        else if(currentMode.equals("Alarm")){
            //alarm모드이고 alarm 설정상태가 아닐경우 start버튼을 누르면 다음알람을 출력
            if(!alaramAdjustState){
                reqNextAlarm();
            }
            //alarm상태이고 alarm설정상태일경우 start버튼을 누르면 설정하는 알람의 현재 커서의 위치값 증가
            else if(alaramAdjustState){
                increaseTime();
            }

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - haesung
        btnAdjust = new JButton();
        btnMode = new JButton();
        btnReset = new JButton();
        btnStart = new JButton();
        panel1 = new JPanel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- btnAdjust ----
        btnAdjust.setText("ADJUST");
        btnAdjust.addActionListener(e -> btnAdjustActionPerformed(e));
        contentPane.add(btnAdjust);
        btnAdjust.setBounds(new Rectangle(new Point(35, 40), btnAdjust.getPreferredSize()));

        //---- btnMode ----
        btnMode.setText("MODE");
        btnMode.addActionListener(e -> btnModeActionPerformed(e));
        contentPane.add(btnMode);
        btnMode.setBounds(new Rectangle(new Point(35, 260), btnMode.getPreferredSize()));

        //---- btnReset ----
        btnReset.setText("RESET");
        btnReset.addActionListener(e -> btnResetActionPerformed(e));
        contentPane.add(btnReset);
        btnReset.setBounds(new Rectangle(new Point(490, 40), btnReset.getPreferredSize()));

        //---- btnStart ----
        btnStart.setText("START");
        btnStart.addActionListener(e -> btnStartActionPerformed(e));
        contentPane.add(btnStart);
        btnStart.setBounds(new Rectangle(new Point(490, 260), btnStart.getPreferredSize()));

        //======== panel1 ========
        {
            panel1.setBorder(LineBorder.createBlackLineBorder());

            // JFormDesigner evaluation mark
            panel1.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            panel1.setLayout(null);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(110, 40, 380, 250);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - haesung
    private JButton btnAdjust;
    private JButton btnMode;
    private JButton btnReset;
    private JButton btnStart;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String args[]){
        new System();
    }

    /*
    시스템 메서드
     */

    public void showTime(){

    }

    //현재 커서 위치에 있는 값을 증가시킴
    //사용하는 모드들 = TimeKeeping, Timer
    public void increaseTime(){
        //현재커서위치의 값
        //currentNumber++;
    }

    public void showTimer(){

    }

    public void reqSetTimer(){
        //기능들 필요

        this.timerAdjustState=true;
    }

    public void increaseTimer(){

    }

    private void changeCursor(){

    }

    private void endSetTimer(){
        this.timerAdjustState=false;
    }

    private void reqAdjustTime(){
        //timekeeping모드에서 adjust버튼을 누르면 이쪽으로

        //timeDB.getTime()메서드 실행

        //Adjsuttime으로 들어왔다면 timekeepingAdajsutState를 true로 바꾸고
        if(!timekeepingAdjustState){
            this.timekeepingAdjustState=true;
        }

    }

    //현재 adjust페이즈일경우 그걸 종료시키는것
    private void endAdjustTime(){
        if(timekeepingAdjustState){
            this.timekeepingAdjustState=false;
        }
    }

    private void reqStartTimer(){
        //Timer를 가동시켜주는 코드가 필요
    }

    //reset을 시켜주면 타이머의 시간을 0으로 초기화
    //timer의 시간이 00:00:00이여도 상관없이 0으로 << 이게 편함
    private void reqResetTimer(){
        //timer의 시간을 0으로
    }

    public void showAlarm(){

    }

    public void showAlarmSetting(){

    }

    private void reqNextAlarm(){
        //나중에 구현해야함
    }

    //alarm화면에서  adjust버튼을 누르면 실행
    private void reqAddAlarm(){
        //만약 알람리스트에 알람이 가득 차있을경우
        if(alarmList.length>=4){
            //알람을 더이상 추가할수없다는 메세지 출력하기
            alarmCanAddState=false;
            alaramAdjustState=false;
        }
        //만약 알람리스트에 있는 알람이 4개가 아닐경우(알람을 더 넣을수 있을경우)
        else{
            alarmCanAddState=true;
            alaramAdjustState=true;
        }
    }

    private void endAddAlarm(){
        this.alaramAdjustState=false;
        alarmCanAddState=false;
    }

    private void reqDeleteAlarm(){
        //(대충 현재 설정된 알람을 제거한다는 코드)
    }

    public void reqStopAlarm(){

    }

    public void showStopwatch(){

    }

    public void reqStartStopwatch(){

    }

    public void reqRecordStopwatch(){

    }

    public void reqPauseStopwatch(){

    }

    public void reqResestStopwatch(){

    }

    public void reqNextTide(){

    }

    public void showMoonphase(){

    }

    //현재모드 말고 다른 모드를 선택하고 싶을때 실행
    //mode select화면으로 진입하는 메서드
    public void reqModeSelect(){

    }

    //mode select화면에서 모드 셀렉트를 종료하고 빠져나올때 쓰는 메서드
    //modeslect화면을 종료할때 modeselect에 바뀐 모드들을 적용하기 위해 modeselect에 현재 선택된 모드들을 보냄
    public void endSelectMode(){
        modeSelector.setSettingModeList(selectedModes);
    }


    /*
    클래스 다이어그래에 없던 메서드들 추가
     */

    //mode select 페이즈에서 원하는 모드를 선택한 경우 그 모드가 존재하는경우 넣고 존재하지 않으면 패스
    private void addModetoList(String mode){
        //리스트의 현재 사이즈만큼 반복을 하는데
        for(int i=0;i<selectedModes.size();i++){
            //현재 선택하려고 하는 모드가 리스트안에 존재 하지 않는다면 if문 수행
            if(!selectedModes.get(i).equals(mode)){
                //리스트 안에 존재하지 않는다면 선택한 모드에 현재 선택한 모드 추가
                selectedModes.add(mode);
            }
        }
    }

    //mode select 페이즈에서 모드를 선택해제 한경우 그 모드가 존재하는 경우 삭제하고 존재하지 않으면 break;
    private void deleteModefromList(String mode){
        //리스트의 현재 사이즈만큼 반복을 하는데
        for(int i=0;i<selectedModes.size();i++){
            //현재 선택하려고하는 모드가 리스트 안에 존재한다면 if문 수행
            if(selectedModes.get(i).equals(mode)){
                //리스트 안에 존재하는 선택해제한 모드를 제거함
                selectedModes.remove(i);
                break;
            }
        }
    }

    //시퀀스에는 있는데 클래스다이어그램에는 없는건가?
    //동작중인 Tiemer를 멈추는 역할
    private void reqPauseTimer(){

    }
}
