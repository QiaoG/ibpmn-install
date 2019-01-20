package dsp.install.domain;

import dsp.install.event.DspEvent;
import dsp.install.event.EventBus;
import dsp.install.exception.DspException;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
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

    @Setter
    private File desktopDir;

    @Getter
    private static ConfigurationManager instance;

    private final String TOMCAT_DIR_NAME = "apache-tomcat-8.5.37";

    private ConfigurationManager(){

    }

    @Getter
    private ConfigurationOfJDBC jdbcConfig;

    @Getter
    private boolean oracle;

    @Setter
    @Getter
    private boolean taskStop;

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
        taskStop = false;
        InstallTask task = new ResourceVerificationTask(distDir+"\\"+TOMCAT_DIR_NAME);
        task.setProgressValue(5);
        tasks.add(task);

        task = new TomcatCopyTask(distDir);
        task.setProgressValue(15);
        tasks.add(task);

        String rootPath = distDir+"\\"+TOMCAT_DIR_NAME+"\\webapps\\ROOT";
        task = new WebappsClearTask(rootPath);
        task.setProgressValue(10);
        tasks.add(task);

        for(int i = 1; i <= RESOURCE_SEPARATE_NUM; i++){
            task = new ResourceUnzipTask("install"+i+".dsp",rootPath);
            task.setProgressValue(12);
            tasks.add(task);
        }

        task = new JdbcConfigTask(rootPath+"\\WEB-INF\\classes",oracle);
        task.setProgressValue(5);
        tasks.add(task);

        task = new ShortcutCreatedTask(distDir+"\\"+TOMCAT_DIR_NAME+"\\bin");
        task.setProgressValue(5);
        tasks.add(task);
    }

    public void exexuteAllTask(){
        int flag = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if(taskStop){
                flag = 1;
                EventBus.getInstance().fireListeners(new DspEvent(DspEvent.TASKS_RUN_EXCEPTION, "安装终止!"));
                break;
            }
            try {
                EventBus.getInstance().fireListeners(new DspEvent(DspEvent.ONE_TASK_BEGIN_RUN,tasks.get(i).getName()));
                tasks.get(i).execute();
                EventBus.getInstance().fireListeners(new DspEvent(DspEvent.ONE_TASK_END_RUN,tasks.get(i)));
                System.out.println(tasks.get(i).getName()+"完成");
            } catch (Exception e) {
                flag = 2;
                e.printStackTrace();
                if(e instanceof DspException){
                    EventBus.getInstance().fireListeners(new DspEvent(DspEvent.TASKS_RUN_EXCEPTION,((DspException)e).getDescription()));
                }else{
                    EventBus.getInstance().fireListeners(new DspEvent(DspEvent.TASKS_RUN_EXCEPTION,"程序异常："+e.getMessage()));
                }
                break;
            }
        }
        if(flag == 0) {
            EventBus.getInstance().fireListeners(new DspEvent(DspEvent.TASKS_RUN_COMPLETE, ""));
        }
    }

    public static void main(String[] args) {
        ConfigurationManager.getInstance().createJDBCConfiguraion(false);
        ConfigurationManager.getInstance().setDistDir("F:\\work\\HiBPM\\dsp-resource\\tomcat");
        ConfigurationManager.getInstance().initTasks();
        ConfigurationManager.getInstance().exexuteAllTask();
    }
}
