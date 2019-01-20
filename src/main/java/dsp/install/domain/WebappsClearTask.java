package dsp.install.domain;

import java.io.File;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/17
 * Time:16:51
 */
public class WebappsClearTask extends InstallTask {

    public WebappsClearTask(String distDir){
        super(distDir);
        setName("清理部署环境");
    }

    @Override
    public void execute() {
        File distDir = new File(getDistDir());
        if(distDir.exists() && distDir.isDirectory()){
            deleteFile(distDir);
        }
        if(!distDir.exists() || distDir.isFile()){
            System.out.println("create ROOT dir.");
            distDir.mkdir();
            this.setName("清理WEB服务器");
        }
    }

    private void deleteFile(File dirFile){
        if(!dirFile.exists()){
            return;
        }
        if(dirFile.isFile()){
            dirFile.delete();
        }else{
            for (File file : dirFile.listFiles()) {
                deleteFile(file);
            }
        }
        dirFile.delete();
    }
}
