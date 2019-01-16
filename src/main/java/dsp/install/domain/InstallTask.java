package dsp.install.domain;

import lombok.Data;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/16
 * Time:22:45
 */
@Data
public abstract class InstallTask {

    private String distDir;

    public void execute() {

    }
}
