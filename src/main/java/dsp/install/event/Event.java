package dsp.install.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
* Author GQ
* Date:2019/1/8
* Time:8:44 PM
*/
@Data
@AllArgsConstructor
public class Event {

    /*
    * 0:licence
     */
    private int type;

    private Object value;
}
