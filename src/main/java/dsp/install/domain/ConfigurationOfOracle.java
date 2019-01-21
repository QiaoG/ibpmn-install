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
public class ConfigurationOfOracle extends ConfigurationOfJDBC{

    @Override
    public String testConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String u = MessageFormat.format("jdbc:oracle:thin:{0}:{1}:{2}", getUrl(), getPort(), getName());
        Connection conn = DriverManager.getConnection(u,getUser(), getPassword());
        if (conn == null) {
            return "fail";
        } else {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("close db connection (ORACLE) fail!");
            }
            return OK;
        }
    }

    @Override
    public String getJdbcUrl() {
        String url = MessageFormat.format("jdbc:oracle:thin:{0}:{1}:{2}", getUrl(), getPort(), getName());
        return url;
    }

}
