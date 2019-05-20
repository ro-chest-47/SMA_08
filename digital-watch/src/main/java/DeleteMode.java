import java.util.ArrayList;

public class DeleteMode {
//    private String[] deleteList;
    private ArrayList<String> deleteList;

    //현재 삭제해야 하는 모드들을 저장해놓는 메서드
    public void setDeleteList(ArrayList<String> deleteList) {
        this.deleteList = deleteList;
    }

      /*
    새롭게 추가해야할 것 같으 메서드
     */
      //받아온 모드들을 조건문을 통해 삭제할 메서드
    public void deleteModeByList(){
        for(int i=0;i<deleteList.size();i++){
            //일단 String값을 비교하도록  설정
            switch (deleteList.get(i)){
                case "TimeKeeping":
                    //TimeKeeping.delete();
                    break;
                case "Timer":
                    //Timer.delete();
                    break;
                case "Alarm":
                    //Alarm.delete();
                    break;
                case "Stopwatch":
                    //Stopwatch.delete();
                    break;
                case "Tide":
                    //Tide.delete();
                    break;
                case "Moonphase":
                    //Moonphsae.delete();
                    break;
                    default:
                        break;
            }
        }
    }
}
