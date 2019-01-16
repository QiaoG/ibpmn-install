package dsp.install.domain;

import dsp.install.utils.ZipCipherUtil;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/16
 * Time:23:16
 */
public class ResourceUnzipTask extends InstallTask {

    private final String SECRE_KEY = "6u5fBBcJOVyZnd9CjbGBslUypGZFBp";

    @Override
    public void execute() {
        ZipCipherUtil util = new ZipCipherUtil();
        try {
            util.decryptUnzip("install.dsp", "F:\\work\\HiBPM\\dsp-resource\\temp\\ROOT", SECRE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
