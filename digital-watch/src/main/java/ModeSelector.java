import java.util.ArrayList;

//현재 선택된 모드정보들을 갖고 있음
public class ModeSelector {
//    private String[] settingModeList;
//    private String[] createList;
//    private String[] deleteList;
    //String[]에서 arraylist로 수정
    private ArrayList<String> settingModeList; //현재 선택된 모드들의 list(최대 4개)
    private ArrayList<String> createList; //생성해야하는 리스트들
    private ArrayList<String> deleteList; //삭제해야 하는 리스트들


    /*
    새로 추가된 필드
     */
    //create및 delete모드
    private CreateMode createMode;
    private DeleteMode deleteMode;



    public ModeSelector(){
        createMode= new CreateMode();
        deleteMode=new DeleteMode();
    }

    //setSettingModeLIst로 이름 바꿈 이름의 통일성을 위해

//    public void setModeList(String[] settingModeList){
//        this.settingModeList=settingModeList;
//    }
    //현재 ModeSelector에 ui쪽에서 선택한 모드들을 list로 저장시킴
    //Sring[]에서 ArrayList로 변경했음
    public void setSettingModeList(ArrayList<String> settingModeList){
        this.settingModeList=settingModeList;
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

    //선택한 모드들로 모드를 바꾸는 메서드
    public void runChangeMode(){
        //먼저 기존의 모드들을 전부다 지워버리고
        deleteMode.setDeleteList(deleteList);
        deleteMode.deleteModeByList();
        //새로운 모드들을 생성
        createMode.setCreateList(createList);
        createMode.createModeByList();
        //그리고 생성된 모드들을 세팅모드 리스트에 저장
        settingModeList=createList;
    }

    public void setCreateList(ArrayList<String> createList){
        this.createList=createList;
    }

    public void setDeleteList(ArrayList<String> deleteList){
        this.deleteList=deleteList;
    }
}
