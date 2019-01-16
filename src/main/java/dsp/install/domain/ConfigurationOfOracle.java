package dsp.install.domain;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.MessageFormat;

/**
* Author:GQ 
* Author Mail:gq_200508@126.com
* Date:2019/1/14
* Time:16:52
*/
@Data
public class ConfigurationOfOracle {

    public static final String  OK ="ok";

    private String url = "@dev.dsprun.com";

    private String port = "1521";

    private String name = "ORCL";

    private String user = "system";

    private String password = "password";

    public String testConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String u = MessageFormat.format("jdbc:oracle:thin:{0}:{1}:{2}", url, port, name);
        Connection conn = DriverManager.getConnection(u, user, password);
        if (conn == null) {
            return "fail";
        } else {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("关闭数据库连接失败！");
            }
            return OK;
        }
    }

}
