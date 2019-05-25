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
//         createMode.setCreateList(createList);
//         deleteMode.setDeleteList(deleteList);
    }

    //ArrayList로 수정함
    //근데 시스템쪽에서 현재설정된 모드를 계속 유지하는상태<< selectedModeList라는 이름으로
    //이게 굳이 필요한지 잘 모르겠음 << 일단 왔다갔다 하는게 그럴듯하긴함
    public ArrayList<String> getModeList(){
        return this.settingModeList;
    }

    public String getNextMode(String currentMode) {
        //설정된 모드리스트 안에서 현재선택한모드의 위치를 찾음
        int index=settingModeList.indexOf(currentMode);
        //인덱스의 위치에 +1을 더하고
        index++;

        //근데 만약 인덱스가 3보다 커져버리면 0으로 옮기기
        //0 -> 1 -> 2 -> 3 -> 0 으로 되게
        if(index>3){
            index=0;
        }

        //그리고 인덱스 위치의 현재 모드정보를 가져와
        //String으로 리턴시켜줌
        return settingModeList.get(index);
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
