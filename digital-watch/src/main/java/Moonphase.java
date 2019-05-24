import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Moonphase {
    private String stdtime="2010 01 01 00 00 00"; //기준시간
    private String currtime="2019 05 22 00 00 00"; //현재시간
    private int moon; //달 분류번호
    private int[][] moongraphic0={
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    }; //삭(달안보임)의 그래픽 정보
    private int[][] moongraphic1={
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
    }; //초승달의 그래픽 정보
    private int[][] moongraphic2={
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
    }; //상현달의 그래픽 정보
    private int[][] moongraphic3={
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
    }; //열하룻달의 그래픽정보
    private int[][] moongraphic4={
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
    }; //보름달의 그래픽정보
    private int[][] moongraphic5={
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //열이렛달의 그래픽 정보
    private int[][] moongraphic6={
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //하현달의 그래픽 정보
    private int[][] moongraphic7={
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
    }; //그믐달의 그래픽 정보

//    Moonphase(){
//        this.calculateMoonphase();
//        this.showMoonphase();
//    }
//
//    public static void main(String[] args) {
//        new Moonphase();
//    } 테스트용 코드 무시

    public void showMoonphase() {
        switch(moon){
            case 0: //계산한 달 분류번호가 0번이면
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(moongraphic0[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                } //삭을 그려준다
                break;
            case 1: //계산한 달번호가 1번이면
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(moongraphic1[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                } //초승달을 그려준다
                break;
            case 2: //계산한 달번호가 2번이면
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(moongraphic2[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                } //상현달을 그려준다
                break;
            case 3: //계산한 달번호가 3번이면
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(moongraphic3[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                } //열하룻달을 그려준다
                break;
            case 4: //계산한 달번호가 4번이면
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(moongraphic4[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                } //보름달을 그려본다
                break;
            case 5: //계산한 달번호가 5번이면
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(moongraphic5[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                } //열이렛달을 그려준다
                break;
            case 6: //계산한 달번호가 6번이면
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(moongraphic6[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                } //하현달을 그려준다
                break;
            case 7: //계산한 달번호가 7번이면
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(moongraphic7[i][j] == 1) {
                            System.out.print(" * ");
                        }
                        else {
                            System.out.print("   ");
                        }
                    }
                    System.out.print("\n");
                } //그믐달을 그려준다
                break;
        }
    }

    public void calculateMoonphase() {
        try { //String type을 Date type으로 캐스팅할때 생기는 예외의 예외처리
            SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd kk mm ss"); //String type으로된 날짜를 계산할 Date type 생성
            Date CurrentDate = format.parse(currtime); //현재시간을 parse()를 통해 Date형으로 변환
            Date StandardDate = format.parse(stdtime); //기준시간을 parse()를 통해 Date형으로 변환

            long calDate = CurrentDate.getTime() - StandardDate.getTime(); //기준시간에서 현재시간까지의 시간 계산
            long calDateDays = calDate / (24*60*60*1000); //계산한 시간의 단위를 일수로 변경
            int k= (int) (calDateDays%29.5); //달의 공전주기인 29.5를 나누어 나머지를 계산해 2010년 1월 1일이 음력 17일이라 열이렛달이란걸 기준으로 현재시간 달모양을 계산한다
            switch (k){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    moon = 5; //현재 달모양을 열이렛달로 설정
                    break;
                case 7:
                    moon = 6; //현재 달모양을 하현달로 설정
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    moon =7; //현재 달모양을 그믐달로 설정
                    break;
                case 14:
                case 15:
                    moon =0; //현재 달모양을 삭로 설정
                    break;
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                    moon=1; //현재 달모양을 초승달로 설정
                    break;
                case 23:
                    moon=2; //현재 달모양을 상현달로 설정
                    break;
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                    moon=3; //현재 달모양을 열하룻달로 설정
                    break;
                case 29:
                    moon=4; //현재 달모양을 반달로 설정
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}