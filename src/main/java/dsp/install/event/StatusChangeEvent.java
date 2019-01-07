package dsp.install.event;

import lombok.Data;

/*
* Author GQ
* Date:2019/1/6
* Time:12:01 PM
*/
@Data
public class StatusChangeEvent {

    public StatusChangeEvent(int s){
        setStatus(s);
    }

    private int status;
}
