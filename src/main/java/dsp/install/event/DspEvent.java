package dsp.install.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
* Author GQ
* Date:2019/1/8
* Time:8:44 PM
*/
@Data
@AllArgsConstructor
public class DspEvent {

    public final static int LICENCE = 0;

    public final static int SHOW_ORACLE_CONFIG=10;

    public final static int DO_CONNECT_ORACLE = 11;

    public final static int CONNECT_ORACLE_SUCCESS = 12;

    public final static int CONNECT_ORACLE_FAIL = 13;

    private int type;

    private Object value;
}
