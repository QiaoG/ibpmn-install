package dsp.install.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author:GQ Author Mail:gq_200508@126.com
 * Date:2019/1/19
 * Time:10:28
 */
@AllArgsConstructor
public class DspException extends RuntimeException{

    @Getter
    private String description;
}
