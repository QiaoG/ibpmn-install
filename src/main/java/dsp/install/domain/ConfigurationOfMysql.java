package dsp.install.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.MessageFormat;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/18
 * Time:21:14
 */
public class ConfigurationOfMysql extends ConfigurationOfJDBC{

    @Override
    public String testConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String u = MessageFormat.format("jdbc:mysql://{0}:{1}/{2}?useUnicode=true&amp;characterEncoding=UTF-8", getUrl(), getPort(), getName());
        Connection conn = DriverManager.getConnection(u,getUser(), getPassword());
        if (conn == null) {
            return "fail";
        } else {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("close (MYSQL) connection fail!");
            }
            return OK;
        }
    }

    @Override
    public String getJdbcUrl() {
        String url = MessageFormat.format("jdbc:mysql://{0}:{1}/{2}?useUnicode=true&amp;characterEncoding=UTF-8", getUrl(), getPort(), getName());
        return url;
    }
}
