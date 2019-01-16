package dsp.install.utils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.UUID;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/16
 * Time:11:00
 */
public class ZipCipherUtil {

    private CipherUtil cipherUtil = new CipherUtil();

    private ZipUtil zipUtil = new ZipUtil();

    public void encryptZip(String srcFile, String destfile, String keyStr) throws Exception {
        File temp = new File(UUID.randomUUID().toString() + ".zip");
        temp.deleteOnExit();
        // 先压缩文件
        zipUtil.zip(srcFile, temp.getAbsolutePath());
        // 对文件加密
        cipherUtil.encrypt(temp.getAbsolutePath(), destfile, keyStr);
        temp.delete();
    }

    public void decryptUnzip(String srcfile, String destfile, String keyStr) throws Exception {
        File temp = new File(UUID.randomUUID().toString() + ".zip");
        temp.deleteOnExit();
        // 先对文件解密
        cipherUtil.decrypt(srcfile, temp.getAbsolutePath(), keyStr);
        // 解压缩
        zipUtil.unZip(temp.getAbsolutePath(),destfile);
        temp.delete();
    }

    public static void main(String[] args) throws Exception {
        long l1 = System.currentTimeMillis();

        //加密
      new ZipCipherUtil().encryptZip("F:\\work\\HiBPM\\dsp-resource\\ROOT", "F:\\work\\HiBPM\\dsp-resource\\install.dsp", "6u5fBBcJOVyZnd9CjbGBslUypGZFBp");
        //解密
//        new ZipCipherUtil().decryptUnzip("F:\\work\\HiBPM\\dsp-resource\\install.dsp", "F:\\work\\HiBPM\\dsp-resource\\temp\\ROOT", "12345");

//        long l2 = System.currentTimeMillis();
//        System.out.println((l2 - l1) + "毫秒.");
//        System.out.println(((l2 - l1) / 1000) + "秒.");

    }
}
