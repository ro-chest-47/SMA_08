import java.sql.Time;
import java.util.ArrayList;

public class CreateMode {
//    private String[] createList;
    private ArrayList<String> createList;

    //현재 생성해야하는 모드들이 createList에 저장
    public void setCreateList(ArrayList<String> createList) {
        this.createList = createList;
        createModeByList();
    }

    /*
    새롭게 추가해야할 것 같으 메서드
     */
    //받아온 모드들을 기반으로 필요한 모드들을 조건문을 통해 생성
    //객체를 cretaeMode에서 생성한다면 cretaeMode에서 받아온 객체를 다시 modeslect에 돌려줘야함 << 귀찮음
    //그렇다면 모드를 생성하라고 전달할때 차라리 각 모드들을 싱글톤방식으로 만들어서 어떤 객체에서도 사용가능할 수있게 하는게 어떨지
    private void createModeByList(){
        for(int i=0;i<createList.size();i++){
            //일단 String값으로 비교를 하도록 설정했는데 나중에 고민이 좀더 필요
            switch (createList.get(i)){
                case "TimeKeeping":
                    TimeKeeping.getInstance();
                    break;
                case "Timer":
                    Timer.getInstance();
                    break;
                case "Alarm":
                    //Alarm.getInstance;
                    break;
                case "Stopwatch":
                    Stopwatch.getInstance();
                    break;
                case "Tide":
                    Tide.getInstance();
                    break;
                case "Moonphase":
                    Moonphase.getInstance();
                    break;
                    default:
                        break;
            }
        }
    }
}
