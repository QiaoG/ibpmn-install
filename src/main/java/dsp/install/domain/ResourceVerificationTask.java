package dsp.install.domain;

import dsp.install.exception.DspException;

import java.io.File;

/**
 * @author:GQ
 * author Mail:gq_200508@126.com
 * Date:2019/1/19
 * Time:8:06
 */
public class ResourceVerificationTask extends InstallTask {

    private final String[] RESOURCE_FILES = {"install1.dsp","install2.dsp","install3.dsp","install4.dsp","install5.dsp","server.dsp"};

    public ResourceVerificationTask(String distDir) {
        super(distDir);
        setName("验证资源");
    }

    @Override
    public void execute() throws Exception{
        File resource = null;
        for (String file : RESOURCE_FILES) {
            resource = new File(file);
            if(!resource.exists() || !resource.isFile()){
                throw new DspException(file+"不存在！");
            }
        }
    }
}
