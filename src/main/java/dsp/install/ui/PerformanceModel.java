package dsp.install.ui;

/*
* Author GQ
* Date:2018/12/31
* Time:11:40 AM
*/
public class PerformanceModel {

    private static PerformanceModel instance;

    public final int W_WIDE = 600;
    public final int W_HIGHT = 500;
    public final int TOP_HIGHT = 80;
    public final int LEFT_WIDE = 100;
    public final int BOTTOM_HIGHT = 40;

    private PerformanceModel(){

    }

    public static PerformanceModel getInstance() {
        if(instance == null){
            instance = new PerformanceModel();
        }
        return instance;
    }
}
