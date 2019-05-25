import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Moonphase {
    private String stdtime="2010 01 01 00 00 00"; //기준시간
    private String currtime="2019 05 22 00 00 00"; //현재시간
    private int moon; //달 분류번호
    private String moongraphic0 = ("./moongraphic/newmoon.jpg"); //삭(달안보임)의 그래픽 정보
    private String moongraphic1 = ("./moongraphic/waxingcrescent.jpg"); //초승달의 그래픽 정보
    private String moongraphic2 = ("./moongraphic/firstquarter.jpg"); //상현달의 그래픽 정보
    private String moongraphic3 = ("./moongraphic/waxinggibbous.jpg"); //열하룻달의 그래픽정보
    private String moongraphic4 = ("./moongraphic/fullmoon.jpg"); //보름달의 그래픽정보
    private String moongraphic5 = ("./moongraphic/waninggibbous.jpg"); //열이렛달의 그래픽 정보
    private String moongraphic6 = ("./moongraphic/lastquarter.jpg"); //하현달의 그래픽 정보
    private String moongraphic7 = ("./moongraphic/waningcrescent.jpg"); //그믐달의 그래픽 정보

//    Moonphase(){
//        this.calculateMoonphase();
//        this.showMoonphase();
//    }
//
//    public static void main(String[] args) {
//        new Moonphase();
//    } 테스트용 코드 무시

    private static Moonphase instance;

    private Moonphase(){
    }

    public static Moonphase getInstance(){
        if(instance==null){
            instance=new Moonphase();
        }
        return instance;
    }

    public static void deleteInstance(){
        instance=null;
    }

    public String showMoonphase() {
        switch(moon){
            case 0: //계산한 달 분류번호가 0번이면
             return moongraphic0;
            case 1: //계산한 달번호가 1번이면
                return moongraphic1; //초승달을 그려준다
            case 2: //계산한 달번호가 2번이면
                return moongraphic2; //상현달을 그려준다
            case 3: //계산한 달번호가 3번이면
                return moongraphic3; //열하룻달을 그려준다
            case 4: //계산한 달번호가 4번이면
                return moongraphic4; //보름달을 그려준다
            case 5: //계산한 달번호가 5번이면
                return moongraphic5; //열이렛달을 그려준다
            case 6: //계산한 달번호가 6번이면
                return moongraphic6; //하현달을 그려준다
            case 7: //계산한 달번호가 7번이면
                return moongraphic7; //그믐달을 그려준다
        }
        return null;
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