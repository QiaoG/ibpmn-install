package dsp.install.domain;

import dsp.install.utils.ZipUtil;

import java.io.File;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/16
 * Time:22:47
 */
public class TomcatCopeTask extends InstallTask {

    public TomcatCopeTask(String distDir){
        this.setDistDir(distDir);
    }

    @Override
    public void execute(){
        ZipUtil zipUtil = new ZipUtil();
        zipUtil.unZip("apache-tomcat-8.5.37-windows-x64.zip",this.getDistDir());
    }

    public static void main(String[] args) {
        TomcatCopeTask task =  new TomcatCopeTask("F:\\work\\HiBPM\\dsp-resource\\temp");
        task.execute();
    }
}
