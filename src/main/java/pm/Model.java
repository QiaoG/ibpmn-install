package pm;

/*
* Author GQ
* Date:2018/12/31
* Time:11:40 AM
*/
public class Model {

    private static Model instance;

    public final int W_WIDE = 800;
    public final int W_HIGHT = 600;
    public final int TOP_HIGHT = 80;
    public final int LEFT_WIDE = 100;
    public final int BOTTOM_HIGHT = 40;

    private Model(){

    }

    public static Model getInstance() {
        if(instance == null){
            instance = new Model();
        }
        return instance;
    }
}
