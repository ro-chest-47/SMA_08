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
    private String tidegraphic0 = ("../../../tidegraphic/t0.jpg"); //9물의 그래픽 정보
    private String tidegraphic1 = ("../../../tidegraphic/t1.jpg"); //10물의 그래픽 정보
    private String tidegraphic2 = ("../../../tidegraphic/t2.jpg"); //11물의 그래픽 정보
    private String tidegraphic3 = ("../../../tidegraphic/t3.jpg"); //12물의 그래픽 정보
    private String tidegraphic4 = ("../../../tidegraphic/t4.jpg"); //13물의 그래픽 정보
    private String tidegraphic5 = ("../../../tidegraphic/t5.jpg");; //동해 남해는 14물과 조금 서해는 조금과 무시의 그래픽 정보
    private String tidegraphic6 = ("../../../tidegraphic/t6.jpg"); //1물의 그래픽 정보
    private String tidegraphic7 = ("../../../tidegraphic/t7.jpg"); //2물의 그래픽 정보
    private String tidegraphic8 = ("../../../tidegraphic/t8.jpg"); //3물의 그래픽 정보
    private String tidegraphic9 = ("../../../tidegraphic/t9.jpg"); //4물의 그래픽 정보
    private String tidegraphic10 = ("../../../tidegraphic/t10.jpg"); //5물의 그래픽 정보
    private String tidegraphic11 = ("../../../tidegraphic/t11.jpg"); //6물의 그래픽 정보
    private String tidegraphic12 = ("../../../tidegraphic/t12.jpg"); //7물의 그래픽 정보
    private String tidegraphic13 = ("../../../tidegraphic/t13.jpg"); //8물의 그래픽 정보

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

    public String showTide() {
        switch(tide){
            case 0:
                return tidegraphic0;
            case 1:
                return tidegraphic1;
            case 2:
                return tidegraphic2;
            case 3:
                return tidegraphic3;
            case 4:
                return tidegraphic4;
            case 5:
                return tidegraphic5;
            case 6:
                return tidegraphic6;
            case 7:
                return tidegraphic7;
            case 8:
                return tidegraphic8;
            case 9:
                return tidegraphic9;
            case 10:
                return tidegraphic10;
            case 11:
                return tidegraphic11;
            case 12:
                return tidegraphic12;
            case 13:
                return tidegraphic13;
        }
        return null;
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

