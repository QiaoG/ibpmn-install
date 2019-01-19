package dsp.install.domain;

import dsp.install.event.DspEvent;
import dsp.install.event.EventBus;
import dsp.install.exception.DspException;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
* @author:GQ
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

    private ConfigurationManager(){

    }

    @Getter
    private ConfigurationOfJDBC jdbcConfig;

    @Getter
    private boolean oracle;

    public ConfigurationOfJDBC createJDBCConfiguraion(boolean oracle){
        ConfigurationOfJDBC config = oracle ? new ConfigurationOfOracle() : new ConfigurationOfMysql();
        config.setPort(oracle?"1521":"3306");
        if(profile == 0){
            config.setUrl(oracle ? "@dev.dsprun.com" : "localhost");
            config.setName(oracle ? "ORCL" : "nms");
            config.setUser(oracle ? "system" : "root");
            config.setPassword(oracle ? "password" : "root");
        }
        jdbcConfig = config;
        this.oracle = oracle;
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
        InstallTask task = new ResourceVerificationTask(null);
        tasks.add(task);

        task = new TomcatCopyTask(distDir);
        //tasks.add(task);

        String rootPath = distDir+"\\"+TOMCAT_DIR_NAME+"\\webapps\\ROOT";
        task = new WebappsClearTask(rootPath);
        //tasks.add(task);

        for(int i = 1; i <= RESOURCE_SEPARATE_NUM; i++){
            task = new ResourceUnzipTask("install"+i+".dsp",rootPath);
            //tasks.add(task);
        }

        task = new JdbcConfigTask(rootPath+"\\WEB-INF\\classes",oracle);
        tasks.add(task);
    }

    public void exexuteAllTask(){
        for (int i = 0; i < tasks.size(); i++) {
            try {
                EventBus.getInstance().fireListeners(new DspEvent(DspEvent.ONE_TASK_BEGIN_RUN,tasks.get(i).getName()));
                tasks.get(i).execute();
                EventBus.getInstance().fireListeners(new DspEvent(DspEvent.ONE_TASK_END_RUN,tasks.get(i).getName()));
                System.out.println(tasks.get(i).getName()+"完成");
            } catch (Exception e) {
                e.printStackTrace();
                if(e instanceof DspException){
                    EventBus.getInstance().fireListeners(new DspEvent(DspEvent.TASKS_RUN_EXCEPTION,((DspException)e).getDescription()));
                }else{
                    EventBus.getInstance().fireListeners(new DspEvent(DspEvent.TASKS_RUN_EXCEPTION,null));
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        ConfigurationManager.getInstance().createJDBCConfiguraion(false);
        ConfigurationManager.getInstance().setDistDir("F:\\work\\HiBPM\\dsp-resource\\tomcat");
        ConfigurationManager.getInstance().initTasks();
        ConfigurationManager.getInstance().exexuteAllTask();
    }
}
