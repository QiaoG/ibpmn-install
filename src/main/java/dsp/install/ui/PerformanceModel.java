package dsp.install.ui;

import lombok.Getter;

/**
* Author GQ
* Date:2018/12/31
* Time:11:40 AM
*/
public class PerformanceModel {

    private static PerformanceModel instance;

    private final int W_WIDE = 600;
    private final int W_HIGHT = 500;
    private final int TOP_HIGHT = 80;
    private final int LEFT_WIDE = 100;
    private final int BOTTOM_HIGHT = 40;

    @Getter
    private boolean windows = false;

    @Getter
    private boolean win10 = false;

    private PerformanceModel(){
        String os = System.getProperty("os.name");
        if("Windows 10".equals(os)){
            win10 = true;
        }
        if (os.contains("Windows")) {
            windows = true;
        }
    }

    public int getFrameWidth(){
        return W_WIDE * (win10 ? 2 : 1);
    }

    public int getFrameHight(){
        return W_HIGHT * (win10 ? 2 : 1);
    }

    public int getTopHight(){
        return TOP_HIGHT * (win10 ? 2 : 1);
    }

    public int getBottomHight() {
        return BOTTOM_HIGHT * (win10 ? 2 : 1);
    }

    public int getLeftWidth() {
        return LEFT_WIDE * (win10 ? 2 : 1);
    }

    public static PerformanceModel getInstance() {
        if(instance == null){
            instance = new PerformanceModel();
        }
        return instance;
    }
}
