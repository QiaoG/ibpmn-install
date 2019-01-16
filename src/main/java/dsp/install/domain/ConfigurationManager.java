package dsp.install.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
* Author:GQ 
* Author Mail:gq_200508@126.com
* Date:2019/1/16
* Time:8:13
*/
public class ConfigurationManager {

    @Getter
    private static ConfigurationManager instance;

    @Getter
    private ConfigurationOfOracle oracleConfig = new ConfigurationOfOracle();

    private ConfigurationManager(){

    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    @Getter
    @Setter
    private String distDir;

    private List<InstallTask> tasks = new ArrayList<>();

    public void initTasks(){
        TomcatCopeTask tcTask = new TomcatCopeTask(distDir);
        tasks.add(tcTask);
    }

    public void exexuteAllTask(){

    }
}
