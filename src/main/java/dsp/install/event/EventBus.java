package dsp.install.event;

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
}
