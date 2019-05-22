import java.util.ArrayList;

//현재 선택된 모드정보들을 갖고 있음
public class ModeSelector {
//    private String[] settingModeList;
//    private String[] createList;
//    private String[] deleteList;
    //String[]에서 arraylist로 수정
    private ArrayList<String> settingModeList= new ArrayList<>(); //현재 선택된 모드들의 list(최대 4개)
    private ArrayList<String> createList = new ArrayList<>(); //생성해야하는 리스트들
    private ArrayList<String> deleteList = new ArrayList<>(); //삭제해야 하는 리스트들



    /*
    새로 추가된 필드
     */
    //create및 delete모드
    private CreateMode createMode;
    private DeleteMode deleteMode;
//    private ArrayList<String> defaultModeList = new ArrayList<>();


    public ModeSelector(){
        //디폴트 모드 6개를 세팅
//        defaultModeList.add("TimeKeeping");
//        defaultModeList.add("Alarm");
//        defaultModeList.add("Timer");
//        defaultModeList.add("Stopwatch");
//        defaultModeList.add("Tide");
//        defaultModeList.add("Moonphase");

        createMode= new CreateMode();
        deleteMode=new DeleteMode();
    }

    public ModeSelector(String mode1, String mode2, String mode3, String mode4){
        settingModeList.add(mode1);
        settingModeList.add(mode2);
        settingModeList.add(mode3);
        settingModeList.add(mode4);
    }

    //setSettingModeLIst로 이름 바꿈 이름의 통일성을 위해

//    public void setModeList(String[] settingModeList){
//        this.settingModeList=settingModeList;
//    }
    //현재 ModeSelector에 ui쪽에서 선택한 모드들을 list로 저장시킴
    //Sring[]에서 ArrayList로 변경했음
    public void setSettingModeList(ArrayList<String> settingModeList){
        this.settingModeList=settingModeList;
        createMode.setCreateList(createList);
        deleteMode.setDeleteList(deleteList);
    }

    //getSettingModeList로 바꾸고 arrayList로  수정
//    public String[] getModeList(){
//        return this.settingModeList;
//    }
    public ArrayList<String> getSettingModeList(){
        return this.settingModeList;
    }

    public String getNextMode() {
        return null;
    }

    /*
    임시로
    클래스다이어그램에 없는 새로 추가된 메서드들
     */

    //System에서 현재 모드를 주면 모드셀렉터에서는 다음모드를 돌려줌 6개 전부다 실행
    public String getDefaultNextMode(String currentMode){
        String returnString=null;

        switch (currentMode){
            case "TimeKeeping":
                returnString="Timer";
                break;
            case "Timer":
                returnString="Alarm";
                break;
            case "Alarm":
                returnString="Stopwatch";
                break;
            case "Stopwatch":
                returnString="Tide";
                break;
            case "Tide":
                returnString="Moonphase";
                break;
            case "Moonphase":
                returnString="TimeKeeping";
                break;
        }

        return returnString;
    }

    public void setCreateList(ArrayList<String> createList){
        this.createList=createList;
    }

    public void setDeleteList(ArrayList<String> deleteList){
        this.deleteList=deleteList;
    }
}
