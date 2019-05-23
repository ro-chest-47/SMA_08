public class Tide {

    private static Tide instance;

    private Tide(){

    }

    public static Tide getInstance(){
        if(instance==null){
            instance=new Tide();
        }
        return instance;
    }

    public static void deleteInstance(){
        instance=null;
    }
}
