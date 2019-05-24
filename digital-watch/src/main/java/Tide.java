import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tide {
    private String currtime="2019 5 25 00 00 00"; //현재시간
    private String y = currtime.substring(0,4);
    private String m = currtime.substring(5,7);
    private String d = currtime.substring(8,10);
    private int tide;
    private int estide;
    private int wtide;
    private int year, month, date;
    private int lunarYear, lunarMonth, lunarDate;
    private boolean leap;
    private int lunarMonthDay[] = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String tidegraphic0 = ("../../../tidegraphic/t6.jpg"); //1물의 그래픽 정보
    private String tidegraphic1 = ("../../../tidegraphic/t7.jpg"); //2물의 그래픽 정보
    private String tidegraphic2 = ("../../../tidegraphic/t8.jpg"); //3물의 그래픽 정보
    private String tidegraphic3 = ("../../../tidegraphic/t9.jpg"); //4물의 그래픽 정보
    private String tidegraphic4 = ("../../../tidegraphic/t10.jpg"); //5물의 그래픽 정보
    private String tidegraphic5 = ("../../../tidegraphic/t11.jpg");; //6물의 그래픽 정보
    private String tidegraphic6 = ("../../../tidegraphic/t12.jpg"); //7물의 그래픽 정보
    private String tidegraphic7 = ("../../../tidegraphic/t13.jpg"); //8물의 그래픽 정보
    private String tidegraphic8 = ("../../../tidegraphic/t0.jpg"); //9물의 그래픽 정보
    private String tidegraphic9 = ("../../../tidegraphic/t1.jpg"); //10물의 그래픽 정보
    private String tidegraphic10 = ("../../../tidegraphic/t2.jpg"); //11물의 그래픽 정보
    private String tidegraphic11 = ("../../../tidegraphic/t3.jpg"); //12물의 그래픽 정보
    private String tidegraphic12 = ("../../../tidegraphic/t4.jpg"); //13물의 그래픽 정보
    private String tidegraphic13 = ("../../../tidegraphic/t5.jpg"); //동해 남해는 14물과 조금 서해는 조금과 무시의 그래픽 정보
    public final int SOLAR_YEAR = 1;
    public final int SOLAR_MONTH = 2;
    public final int SOLAR_DATE = 3;
    public final int LUNAR_YEAR = 4;
    public final int LUNAR_MONTH = 5;
    public final int LUNAR_DATE = 6;
    public final int IS_YUNDAL = 7;

    private final int[][] matchTable = {
            // 1881
            {1, 2, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0},
            {1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0},
            {2, 1, 1, 2, 1, 3, 2, 1, 2, 2, 1, 2, 2},
            {2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0},
            {2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0},
            {2, 2, 1, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2},
            {2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0},
            {2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0},
            {1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2},
            // 1891
            {1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0},
            {1, 1, 2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 2},
            {1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0},
            {1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0},
            {2, 1, 2, 1, 2, 3, 1, 2, 1, 2, 1, 2, 1},
            {2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0},
            {1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0},
            {2, 1, 2, 3, 2, 2, 1, 2, 1, 2, 1, 2, 1},
            {2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 0},
            {1, 2, 1, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2},
            // 1901
            {1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0},
            {2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0},
            {1, 2, 1, 2, 1, 3, 2, 1, 1, 2, 2, 1, 2},
            {2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0},
            {2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2, 0},
            {1, 2, 2, 1, 4, 1, 2, 1, 2, 1, 2, 1, 2},
            {1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0},
            {2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0},
            {1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2},
            {1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0},
            // 1911
            {2, 1, 2, 1, 1, 2, 3, 1, 2, 2, 1, 2, 2},
            {2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0},
            {2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0},
            {2, 2, 1, 2, 2, 3, 1, 2, 1, 2, 1, 1, 2},
            {2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0},
            {1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0},
            {2, 1, 3, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1},
            {2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 0},
            {1, 2, 1, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2},
            {1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0},
            // 1921
            {2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0},
            {2, 1, 2, 2, 1, 3, 2, 1, 1, 2, 1, 2, 2},
            {1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 0},
            {2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 0},
            {2, 1, 2, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2},
            {1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0},
            {2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0},
            {1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2},
            {1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 0},
            {1, 2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 1},
            // 1931
            {2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 0},
            {2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0},
            {1, 2, 2, 1, 2, 4, 1, 2, 1, 2, 1, 1, 2},
            {1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 0},
            {1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0},
            {2, 1, 1, 4, 1, 2, 1, 2, 1, 2, 2, 2, 1},
            {2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 0},
            {2, 2, 1, 1, 2, 1, 1, 4, 1, 2, 2, 1, 2},
            {2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0},
            {2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0},
            // 1941
            {2, 2, 1, 2, 2, 1, 4, 1, 1, 2, 1, 2, 1},
            {2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 0},
            {1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0},
            {1, 1, 2, 1, 4, 1, 2, 1, 2, 2, 1, 2, 2},
            {1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0},
            {2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0},
            {2, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2},
            {2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0},
            {2, 2, 1, 2, 1, 2, 1, 3, 2, 1, 2, 1, 2},
            {2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0},
            // 1951
            {2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0},
            {1, 2, 1, 2, 1, 4, 2, 1, 2, 1, 2, 1, 2},
            {1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 0},
            {1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0},
            {2, 1, 1, 4, 1, 1, 2, 1, 2, 1, 2, 2, 2},
            {1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0},
            {2, 1, 2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2},
            {1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0},
            {1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0},
            {2, 1, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1},
            // 1961
            {2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 0},
            {1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0},
            {2, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 2, 1},
            {2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0},
            {1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0},
            {2, 2, 2, 3, 2, 1, 1, 2, 1, 1, 2, 2, 1},
            {2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2, 0},
            {1, 2, 2, 1, 2, 1, 2, 3, 2, 1, 2, 1, 2},
            {1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0},
            {2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0},
            // 1971
            {1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 1, 2},
            {1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0},
            {2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 1, 0},
            {2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2},
            {2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0},
            {2, 2, 1, 2, 1, 2, 1, 2, 3, 2, 1, 1, 2},
            {2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 0},
            {2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0},
            {2, 1, 1, 2, 1, 2, 4, 1, 2, 2, 1, 2, 1},
            {2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0},
            // 1981
            {1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0},
            {2, 1, 2, 1, 3, 2, 1, 1, 2, 2, 1, 2, 2},
            {2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0},
            {2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 3, 2, 2},
            {1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0},
            {1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 0},
            {2, 1, 2, 2, 1, 2, 3, 2, 2, 1, 2, 1, 2},
            {1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0},
            {2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0},
            {1, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 2, 2},
            // 1991
            {1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 0},
            {1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0},
            {1, 2, 2, 3, 2, 1, 2, 1, 1, 2, 1, 2, 1},
            {2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0},
            {1, 2, 2, 1, 2, 2, 1, 2, 3, 2, 1, 1, 2},
            {1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0},
            {1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0},
            {2, 1, 1, 2, 1, 3, 2, 2, 1, 2, 2, 2, 1},
            {2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 0},
            {2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 0},
            // 2001
            {2, 2, 2, 1, 3, 2, 1, 1, 2, 1, 2, 1, 2},
            {2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0},
            {2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 0},
            {1, 2, 3, 2, 2, 1, 2, 1, 2, 2, 1, 1, 2},
            {1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0},
            {1, 1, 2, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2},
            {1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0},
            {2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0},
            {2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 1, 2, 2},
            {2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0},
            // 2011
            {2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0},
            {2, 1, 2, 4, 2, 1, 2, 1, 1, 2, 1, 2, 1},
            {2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0},
            {1, 2, 1, 2, 1, 2, 1, 2, 2, 3, 2, 1, 2},
            {1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 2, 0},
            {1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0},
            {2, 1, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 2},
            {1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0},
            {2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0},
            {2, 1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2},
            // 2021
            {1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0},
            {2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 0},
            {1, 2, 3, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2},
            {1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0},
            {2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 1},
            {2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0},
            {1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 0},
            {1, 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1},
            {2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 0},
            {1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 0},
            // 2031
            {2, 1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1},
            {2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0},
            {1, 2, 1, 1, 2, 1, 2, 3, 2, 2, 2, 1, 2},
            {1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0},
            {2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0},
            {2, 2, 1, 2, 1, 1, 4, 1, 1, 2, 1, 2, 2},
            {2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0},
            {2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 0},
            {2, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1, 1},
            {2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0},
            // 2041
            {2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0},
            {1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2},
            {1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0},
            {2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 0},
            {2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0},
            {2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0},
            {2, 1, 2, 2, 1, 4, 1, 2, 1, 1, 2, 1, 2},
            {1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 0},
            {2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 0},
            {1, 2, 1, 4, 1, 2, 1, 2, 2, 1, 2, 2, 1},
            // 2051
            {2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2, 0},
            {1, 2, 1, 1, 2, 1, 1, 2, 3, 2, 2, 2, 2},
            {1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 0},
            {1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0},
            {1, 2, 2, 1, 2, 1, 4, 1, 1, 2, 1, 2, 1},
            {2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0},
            {1, 2, 2, 1, 2, 1, 2, 2, 1, 1, 2, 1, 0},
            {2, 1, 2, 1, 4, 2, 1, 2, 1, 2, 2, 1, 1},
            {2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0},
            {2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 0},
            // 2061
            {2, 2, 3, 2, 1, 1, 2, 1, 2, 2, 2, 1, 0},
            {2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 0},
            {2, 2, 1, 2, 1, 2, 3, 2, 1, 2, 1, 2, 0},
            {2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0},
            {2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 0},
            {1, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1, 2},
            {1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0},
            {2, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 0},
            {1, 2, 1, 2, 3, 1, 2, 1, 2, 2, 2, 1, 2},
            {2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0},
            // 2071
            {2, 1, 2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2},
            {2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0},
            {2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0},
            {2, 1, 2, 2, 1, 2, 3, 2, 1, 2, 1, 2, 1},
            {2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 0},
            {1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 0},
            {2, 1, 2, 3, 2, 1, 2, 2, 2, 1, 2, 1, 0},
            {2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0},
            {1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0},
            {2, 1, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2, 2},
            // 2081
            {1, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 0},
            {1, 2, 2, 2, 1, 2, 3, 2, 1, 1, 2, 2, 0},
            {1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0},
            {2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 0},
            {1, 2, 1, 1, 2, 4, 1, 2, 2, 1, 2, 1, 2},
            {1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0},
            {2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0},
            {1, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 2, 1},
            {2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0},
            {2, 2, 2, 1, 2, 1, 1, 2, 3, 1, 2, 2, 1},
            // 2091
            {2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 0},
            {2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 0},
            {1, 2, 2, 1, 2, 1, 4, 2, 1, 2, 1, 2, 1},
            {2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0},
            {1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0},
            {2, 1, 2, 3, 2, 1, 1, 2, 2, 2, 1, 2, 0},
            {2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0},
            {2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0},
            {2, 2, 3, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2},
            {2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 0},
            {2, 2, 1, 2, 2, 1, 2, 3, 2, 1, 1, 2, 1}};

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
        this.year = Integer.parseInt(y);
        this.month = Integer.parseInt(m);
        this.date = Integer.parseInt(d);

        int[] dt = new int[221];
        int td1, td2, k11, td, td0, t1, t2, jcount, m2, m1, m0, w, ti1, tj1;
        for (int i = 0; i < matchTable.length; i++) {
            dt[i] = 0;
            for (int j = 0; j < 12; j++) {
                if (matchTable[i][j] % 2 == 1) {
                    dt[i] += 29;
                } else {
                    dt[i] += 30;
                }
            }
            if (matchTable[i][12] == 0) {
                dt[i] += 0;
            } else if ((matchTable[i][12] % 2) == 1) {
                dt[i] += 29;
            } else {
                dt[i] += 30;
            }
        }

        td1 = 1880 * 365 + (int) ((double) 1880 / (double) 4) - (int) ((double) 1880 / (double) 100) + (int) ((double) 1880 / (double) 400) + 30;
        k11 = year - 1;
        td2 = k11 * 365 + (int) ((double) k11 / (double) 4) - (int) ((double) k11 / (double) 100) + (int) ((double) k11 / (double) 400);

        if ((year % 400 == 0) || (year % 100 != 0) & (year % 4 == 0)) {
            lunarMonthDay[1] = 29;
        } else {
            lunarMonthDay[1] = 28;
        }

        for (int i = 0; i < month - 1; i++) {
            td2 += lunarMonthDay[i];
        }
        td2 += date;
        td = td2 - td1 + 1;
        td0 = dt[0];
        t1 = 0;
        for (t1 = 0; t1 < 221; t1++) {
            if (td <= td0) {
                break;
            }
            td0 += dt[t1 + 1];
        }

        lunarYear = t1 + 1881;
        td0 -= dt[t1];
        td -= td0;

        jcount = 12;
        if (matchTable[t1][12] != 0) {
            jcount = 13;
        }

        m2 = 0;
        t2 = 0;
        for (t2 = 0; t2 < jcount; t2++) {
            if (matchTable[t1][t2] <= 2) {
                m2++;
                m1 = matchTable[t1][t2] + 28;
            } else {
                m1 = matchTable[t1][t2] + 26;
            }
            if (td <= m1) {
                break;
            }
            td -= m1;
        }
        m0 = t2;
        lunarMonth = m2;
        lunarDate = td;
        w = td2 % 7;

        t1 = (int) ((td2 + 4) % 10);
        t2 = (int) ((td2 + 2) % 12);
        ti1 = (lunarYear + 6) % 10;
        tj1 = (lunarYear + 8) % 12;
        if (matchTable[lunarYear - 1881][12] > 2 & matchTable[lunarYear - 1881][m0] > 2) {
            leap = true;
        }
        int i=(lunarDate+6)%15;
        switch (i) {
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
                estide = 6;
                break;
            case 7:
                estide = 7;
                break;
            case 8:
                estide = 8;
                break;
            case 9:
                estide = 9;
                break;
            case 10:
                estide = 10;
                break;
            case 11:
                estide = 11;
                break;
            case 12:
                estide = 12;
                break;
            case 13:
                estide = 13;
                break;
            case 14:
                estide = 13;
                break;
        }
        wtide = estide - 1;
        tide = estide;
    }

    public void nextTide(){
//        if(button2==0){
//            tide=estide;
//        }
//        else if(button2==1){
//            tide=wtide;
//            button2=0;
//        }
    }
}

