import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private int currentMode; //int값인지 불확실
    private boolean buzzByAlarm;

    public System() {
        initComponents();
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
        contentPane.add(btnAdjust);
        btnAdjust.setBounds(new Rectangle(new Point(35, 40), btnAdjust.getPreferredSize()));
        btnAdjust.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //---- btnMode ----
        btnMode.setText("MODE");
        contentPane.add(btnMode);
        btnMode.setBounds(new Rectangle(new Point(35, 260), btnMode.getPreferredSize()));
        btnMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //---- btnReset ----
        btnReset.setText("RESET");
        contentPane.add(btnReset);
        btnReset.setBounds(new Rectangle(new Point(490, 40), btnReset.getPreferredSize()));
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //---- btnStart ----
        btnStart.setText("START");
        contentPane.add(btnStart);
        btnStart.setBounds(new Rectangle(new Point(490, 260), btnStart.getPreferredSize()));
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

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

    public void reqModeSelect(){

    }


}
