package dsp.install.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/16
 * Time:22:45
 */
public abstract class InstallTask {

    @Getter
    @Setter
    private String name;

    @Getter
    private String distDir;

    public InstallTask(String distDir){
        this.distDir = distDir;
    }

    public abstract void execute() throws Exception ;
}
