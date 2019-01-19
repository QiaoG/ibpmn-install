package dsp.install.domain;

import lombok.Data;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/18
 * Time:21:14
 */
@Data
public abstract class ConfigurationOfJDBC {
    public static final String  OK ="ok";

    private String url = "";

    private String port = "";

    private String name = "";

    private String user = "";

    private String password = "";

    public abstract String testConnection() throws Exception;

    public abstract String getJdbcUrl();
}
