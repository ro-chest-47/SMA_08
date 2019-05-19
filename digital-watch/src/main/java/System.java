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
        //currnetMode가 timekeeping일경우 adjust버튼을 누른다면
        reqAdjustTime(); //으로 진행

    }

    private void btnResetActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void btnModeActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void btnStartActionPerformed(ActionEvent e) {
        // TODO add your code here
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

    public void showTimer(){

    }

    public void reqSetTimer(){

    }

    public void increaseTimer(){

    }

    public void changeCursor(){

    }

    public void endSetTimer(){

    }

    public void reqAdjustTime(){
        //timekeeping모드에서 adjust버튼을 누르면 이쪽으로

        //timeDB.getTime()메서드 실행
    }

    public void endAdjustTime(){

    }

    public void reqStartTimer(){

    }

    public void reqResetTimer(){

    }

    public void showAlarm(){

    }

    public void showAlarmSetting(){

    }

    public void reqNextAlarm(){

    }

    public void reqAddAlarm(){

    }

    public void endAddAlarm(){

    }

    public void reqDeleteAlarm(){

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

}
