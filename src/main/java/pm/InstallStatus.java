package pm;

import event.IStatusChangeListener;

import java.util.ArrayList;
import java.util.List;

/*
* Author GQ
* Date:2019/1/6
* Time:11:43 AM
*/
public class InstallStatus {
    private static InstallStatus instance;

    private InstallStatus(){

    }

    public static InstallStatus getInstance(){
        if(instance == null){
            instance = new InstallStatus();
        }
        return instance;
    }

    private List<IStatusChangeListener> listeners = new ArrayList<>();

    public void registeListener(IStatusChangeListener listener){
        listeners.add(listener);
    }

    public void removeListener(IStatusChangeListener listener){
        listeners.remove(listener);
    }
}
