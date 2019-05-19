public class ModeSelector {
    private String[] settingModeList; //현재 선택된 모드들의 list(최대 4개)
    private String[] createList; //생성해야하는 모드의 list
    private String[] deleteList; //삭제해야하는 모드의 list

    public void setModeList(String[] settingModeList){
        this.settingModeList=settingModeList;
    }

    public String[] getModeList(){
        return this.settingModeList;
    }

    public String getNextMode(){
        return null;
    }
}
