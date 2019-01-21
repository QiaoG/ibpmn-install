package dsp.install.domain;

import dsp.install.utils.ZipCipherUtil;
import dsp.install.utils.ZipUtil;

import java.io.File;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/18
 * Time:23:03
 */
public class TomcatCopyTask extends InstallTask{
    public TomcatCopyTask(String distDir){
        super(distDir);
        setName("部署WEB服务器");
    }

    @Override
    public void execute() throws Exception {
        ZipUtil zipUtil = new ZipUtil();
        ZipCipherUtil zcu = new ZipCipherUtil();
        String name = "server.dsp";
        File tomcat = new File(name);
        if(!tomcat.exists()){
            name = "..\\"+name;
        }
        zcu.decryptUnzip(name,this.getDistDir(),"6u5fBBcJOVyZnd9CjbGBslUypGZFBp");

    }
}
