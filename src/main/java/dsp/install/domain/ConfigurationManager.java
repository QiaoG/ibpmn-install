package dsp.install.domain;

import lombok.Data;
import lombok.Getter;

/**
* Author:GQ 
* Author Mail:gq_200508@126.com
* Date:2019/1/16
* Time:8:13
*/
@Data
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
}
