package dsp.install.event;

import java.util.ArrayList;
import java.util.List;

/*
* Author GQ
* Date:2018/12/31
* Time:11:50 AM
*/
public class EventBus {
    private static EventBus instance;

    private EventBus(){

    }

    public static EventBus getInstance() {
        if(instance == null){
            instance = new EventBus();
        }
        return instance;
    }

    private List<IEventListener> listeners = new ArrayList<>();

    public void fireListeners(Event event){
        for (IEventListener listener : listeners) {
            listener.handleEvent(event);
        }
    }

    public void registeListener(IEventListener listener){
        if(!listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    public void removeListener(IEventListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

}
