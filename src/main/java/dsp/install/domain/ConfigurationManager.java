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
    private int profile = 0;

    private final int RESOURCE_SEPARATE_NUM = 5;

    @Getter
    private static ConfigurationManager instance;

    private final String TOMCAT_DIR_NAME = "apache-tomcat-8.5.37";

//    @Getter
//    private ConfigurationOfOracle oracleConfig = new ConfigurationOfOracle();

    private ConfigurationManager(){

    }

    public ConfigurationOfJDBC getJDBCConfiguraion(boolean oracle){
        ConfigurationOfJDBC config = oracle ? new ConfigurationOfOracle() : new ConfigurationOfMysql();
        config.setPort(oracle?"1521":"3306");
        if(profile == 0){
            config.setUrl(oracle ? "@dev.dsprun.com" : "localhost");
            config.setName(oracle ? "ORCL" : "nms");
            config.setUser(oracle ? "system" : "root");
            config.setPassword(oracle ? "password" : "root");
        }

        return config;
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

    public int getTaskNum(){
        return tasks.size();
    }

    public void initTasks(){
        TomcatCopyTask tcTask = new TomcatCopyTask(distDir);
        tasks.add(tcTask);

        String rootPath = distDir+"\\"+TOMCAT_DIR_NAME+"\\webapps\\ROOT";
        WebappsClearTask clearRootTask = new WebappsClearTask(rootPath);
        tasks.add(clearRootTask);

        ResourceUnzipTask ru = null;
        for(int i = 1; i <= RESOURCE_SEPARATE_NUM; i++){
            ru = new ResourceUnzipTask("install"+i+".dsp",rootPath);
            tasks.add(ru);
        }

        JdbcConfigTask jdbcTask = new JdbcConfigTask(rootPath+"\\WEB-INFO\\classes");
        tasks.add(jdbcTask);
    }

    public void exexuteAllTask(){
        for (int i = 0; i < tasks.size(); i++) {
            try {
                tasks.get(i).execute();
                System.out.println(tasks.get(i).getName()+" complete!");
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        ConfigurationManager.getInstance().setDistDir("F:\\work\\HiBPM\\dsp-resource\\tomcat");
        ConfigurationManager.getInstance().initTasks();
        ConfigurationManager.getInstance().exexuteAllTask();
    }
}
