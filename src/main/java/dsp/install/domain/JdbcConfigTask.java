package dsp.install.domain;

import dsp.install.utils.DspProperties;

import java.io.*;
import java.util.Properties;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/18
 * Time:11:50
 */
public class JdbcConfigTask extends InstallTask {

    private final String fileName = "jdbc.properties";

    private boolean oracle;

    public JdbcConfigTask(String distDir,boolean oracle) {
        super(distDir);
        setName("配置JDBC");
        this.oracle = oracle;
    }

    @Override
    public void execute() throws Exception {
        String properties = this.getDistDir()+"\\"+fileName;
        ConfigurationOfJDBC jdbc = ConfigurationManager.getInstance().getJdbcConfig();
        try (FileInputStream fis = new FileInputStream(properties);FileOutputStream fos = new FileOutputStream(properties)){
            DspProperties props=new DspProperties();
            props.load(fis);
            if(oracle){
                props.setProperty("jdbc.dbtype", "oracle");
            }
            props.setProperty("jdbc.driverClassName", oracle?"oracle.jdbc.driver.OracleDriver":"com.mysql.jdbc.Driver");
            props.setProperty("jdbc.url", jdbc.getJdbcUrl());
            props.setProperty("jdbc.dialect", oracle?"org.hibernate.dialect.Oracle9Dialect":"org.hibernate.dialect.MySQLDialect");
            props.setProperty("jdbc.default_schema", oracle?jdbc.getUser():jdbc.getName());
            props.setProperty("jdbc.username", jdbc.getUser());
            props.setProperty("jdbc.password", jdbc.getPassword());

            if(oracle){
                props.setProperty("jdbc.maxSession", "200");
                props.setProperty("jdbc.pool.init", "30");
                props.setProperty("jdbc.debug", "off");
            }

            props.store(fos, "Update value");
        } catch (IOException e) {
            //e.printStackTrace();
            throw e;
        }
    }
}
