package dsp.install.domain;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/18
 * Time:11:50
 */
public class JdbcConfigTask extends InstallTask {

    private final String fileName = "jdbc.properties";

    public JdbcConfigTask(String distDir) {
        super(distDir);
        setName("配置JDBC");
    }

    @Override
    public void execute(){

    }
}
