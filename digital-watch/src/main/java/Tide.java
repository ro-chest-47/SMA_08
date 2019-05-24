import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tide {
    private String stdtime="2010 01 01 00 00 00"; //기준시간
    private String currtime="2010 6 01 00 00 00"; //현재시간
    private int tide;
    private int estide;
    private int wtide;
    private int button2=0;
    private int[][] tidegraphic0={
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //9물의 그래픽 정보
    private int[][] tidegraphic1={
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //10물의 그래픽 정보
    private int[][] tidegraphic2={
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //11물의 그래픽 정보
    private int[][] tidegraphic3={
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //12물의 그래픽 정보
    private int[][] tidegraphic4={
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //13물의 그래픽 정보
    private int[][] tidegraphic5={
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //동해 남해는 14물과 조금 서해는 조금과 무시의 그래픽 정보
    private int[][] tidegraphic6={
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //1물의 그래픽 정보
    private int[][] tidegraphic7={
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //2물의 그래픽 정보
    private int[][] tidegraphic8={
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //3물의 그래픽 정보
    private int[][] tidegraphic9={
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //4물의 그래픽 정보
    private int[][] tidegraphic10={
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //5물의 그래픽 정보
    private int[][] tidegraphic11={
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //6물의 그래픽 정보
    private int[][] tidegraphic12={
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //7물의 그래픽 정보
    private int[][] tidegraphic13={
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //8물의 그래픽 정보

    private static Tide instance;
//
//    private Tide(){
//        this.calculateTide();
//        this.nextTide();
//        this.showTide();
//    }
//
    public static Tide getInstance(){
        if(instance==null){
            instance=new Tide();
        }
        return instance;
    }

    public static void deleteInstance(){
        instance=null;
    }

    public void showTide() {
        switch(tide){
            case 0:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic0[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 1:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic1[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic2[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 3:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic3[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 4:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic4[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 5:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic5[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 6:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic6[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 7:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic7[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 8:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic8[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 9:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic9[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 10:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic10[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 11:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic11[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 12:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic12[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
            case 13:
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tidegraphic13[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                }
                break;
        }
    }

    public void calculateTide() {
        try { //String type을 Date type으로 캐스팅할때 생기는 예외의 예외처리
            SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd kk mm ss"); //String type으로된 날짜를 계산할 Date type 생성
            Date CurrentDate = format.parse(currtime); //현재시간을 parse()를 통해 Date형으로 변환
            Date StandardDate = format.parse(stdtime); //기준시간을 parse()를 통해 Date형으로 변환

            long calDate = CurrentDate.getTime() - StandardDate.getTime(); //기준시간에서 현재시간까지의 시간 계산
            long calDateDays = calDate / (24*60*60*1000); //계산한 시간의 단위를 일수로 변경
            int k= (((int)(calDateDays%15)+(int)Math.floor(calDateDays/58))%15)-1;

            switch (k){
                case 0:
                    estide = 0;
                    break;
                case 1:
                    estide = 1;
                    break;
                case 2:
                    estide = 2;
                    break;
                case 3:
                    estide = 3;
                    break;
                case 4:
                    estide = 4;
                    break;
                case 5:
                    estide = 5;
                    break;
                case 6:
                    estide = 5;
                    break;
                case 7:
                    estide = 6;
                    break;
                case 8:
                    estide = 7;
                    break;
                case 9:
                    estide = 8;
                    break;
                case 10:
                    estide = 9;
                    break;
                case 11:
                    estide = 10;
                    break;
                case 12:
                    estide = 11;
                    break;
                case 13:
                    estide = 12;
                    break;
                case 14:
                    estide = 13;
                    break;
            }
            wtide=estide-1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void nextTide(){
        if(button2==0){
            tide=estide;
        }
        else if(button2==1){
            tide=wtide;
            button2=0;
        }
    }
}

