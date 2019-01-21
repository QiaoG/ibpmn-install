package dsp.install.domain;

import dsp.install.utils.ZipCipherUtil;

import java.io.File;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/16
 * Time:23:16
 */
public class ResourceUnzipTask extends InstallTask {

    private final String SECRE_KEY = "6u5fBBcJOVyZnd9CjbGBslUypGZFBp";

    private String srcFile ;

    public ResourceUnzipTask(String dspFile,String distDir){
        super(distDir);
        this.srcFile = dspFile;
        this.setName("部署资源");
    }

    @Override
    public void execute() throws Exception {
        ZipCipherUtil util = new ZipCipherUtil();

        File r = new File(srcFile);
        if(!r.exists()){
            srcFile = "..\\"+srcFile;
        }

        util.decryptUnzip(srcFile, this.getDistDir(), SECRE_KEY);
    }

    @Override
    public String getName(){
        return super.getName()+"("+this.srcFile+")";
    }
}
