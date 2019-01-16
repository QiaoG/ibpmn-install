package dsp.install.domain;

import dsp.install.event.IStatusChangeListener;
import dsp.install.event.StatusChangeEvent;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
* Author GQ
* Date:2019/1/6
* Time:11:43 AM
*/
@Data
public class StatusModel {
    @Getter
    private static StatusModel instance;

    //"安装选项",
    public static String[] STEPS = {"欢迎使用","许可协议","数据配置","安装进度","安装完成"};

    private StatusModel(){

    }

    public static StatusModel getInstance(){
        if(instance == null){
            instance = new StatusModel();
        }
        return instance;
    }

    private int currentStatus = 0;

    public void previous(){
        if(getCurrentStatus() == 0){
            return;
        }
        currentStatus--;
        fireListener();
    }

    public void next(){
        if(getCurrentStatus() == STEPS.length - 1){
            return;
        }
        currentStatus++;
        fireListener();
    }

    private List<IStatusChangeListener> listeners = new ArrayList<>();

    public void fireListener(){
        for(IStatusChangeListener listener : listeners){
            listener.handleStatusChanged(new StatusChangeEvent(getCurrentStatus()));
        }
    }

    public void registeListener(IStatusChangeListener listener){
        listeners.add(listener);
    }

    public void removeListener(IStatusChangeListener listener){
        listeners.remove(listener);
    }
}
