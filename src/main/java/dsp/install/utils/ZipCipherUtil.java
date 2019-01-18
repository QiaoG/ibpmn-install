package dsp.install.utils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.UUID;

/**
 * Author:GQ
 * Author Mail:gq_200508@126.com
 * Date:2019/1/16
 * Time:11:00
 *
 * -Xss2M
 */
public class ZipCipherUtil {

    private CipherUtil cipherUtil = new CipherUtil();

    private ZipUtil zipUtil = new ZipUtil();

    public void encryptZip(String srcFile, String destfile, String keyStr) throws Exception {
        File temp = new File(UUID.randomUUID().toString() + ".zip");
        temp.deleteOnExit();
        zipUtil.zip(srcFile, temp.getAbsolutePath());
        cipherUtil.encrypt(temp.getAbsolutePath(), destfile, keyStr);
        temp.delete();
    }

    public void decryptUnzip(String srcfile, String destfile, String keyStr) throws Exception {
        File temp = new File(UUID.randomUUID().toString() + ".zip");
        temp.deleteOnExit();

        cipherUtil.decrypt(srcfile, temp.getAbsolutePath(), keyStr);

        zipUtil.unZip(temp.getAbsolutePath(),destfile);
        temp.delete();
    }

    public static void main(String[] args) throws Exception {
        long l1 = System.currentTimeMillis();


        ZipCipherUtil util = new ZipCipherUtil();

//        util.encryptZip("F:\\work\\HiBPM\\dsp-resource\\ROOT1", "F:\\work\\HiBPM\\dsp-resource\\install1.dsp", "6u5fBBcJOVyZnd9CjbGBslUypGZFBp");
//        long l2 = System.currentTimeMillis();
//        System.out.println("1 complete "+((l2 - l1) / 1000));
//        l1 = System.currentTimeMillis();
//        util.encryptZip("F:\\work\\HiBPM\\dsp-resource\\ROOT2", "F:\\work\\HiBPM\\dsp-resource\\install2.dsp", "6u5fBBcJOVyZnd9CjbGBslUypGZFBp");
//        l2 = System.currentTimeMillis();
//        System.out.println("2 complete "+((l2 - l1) / 1000));
//        l1 = System.currentTimeMillis();
//        util.encryptZip("F:\\work\\HiBPM\\dsp-resource\\ROOT3", "F:\\work\\HiBPM\\dsp-resource\\install3.dsp", "6u5fBBcJOVyZnd9CjbGBslUypGZFBp");
//        l2 = System.currentTimeMillis();
//        System.out.println("3 complete "+((l2 - l1) / 1000));
//        l1 = System.currentTimeMillis();
//        util.encryptZip("F:\\work\\HiBPM\\dsp-resource\\ROOT4", "F:\\work\\HiBPM\\dsp-resource\\install4.dsp", "6u5fBBcJOVyZnd9CjbGBslUypGZFBp");
//        l2 = System.currentTimeMillis();
//        System.out.println("4 complete "+((l2 - l1) / 1000));
//        l1 = System.currentTimeMillis();
//        util.encryptZip("F:\\work\\HiBPM\\dsp-resource\\ROOT5", "F:\\work\\HiBPM\\dsp-resource\\install5.dsp", "6u5fBBcJOVyZnd9CjbGBslUypGZFBp");
//        l2 = System.currentTimeMillis();
//        System.out.println("5 complete "+((l2 - l1) / 1000));
        //解密
        util.decryptUnzip("F:\\work\\HiBPM\\dsp-resource\\install1.dsp", "F:\\work\\HiBPM\\dsp-resource\\temp\\ROOT", "6u5fBBcJOVyZnd9CjbGBslUypGZFBp");
        long l2 = System.currentTimeMillis();
        System.out.println("1 complete "+((l2 - l1) / 1000));
        l1 = System.currentTimeMillis();

        util.decryptUnzip("F:\\work\\HiBPM\\dsp-resource\\install2.dsp", "F:\\work\\HiBPM\\dsp-resource\\temp\\ROOT", "6u5fBBcJOVyZnd9CjbGBslUypGZFBp");
        l2 = System.currentTimeMillis();
        System.out.println("2 complete "+((l2 - l1) / 1000));
        l1 = System.currentTimeMillis();

        util.decryptUnzip("F:\\work\\HiBPM\\dsp-resource\\install3.dsp", "F:\\work\\HiBPM\\dsp-resource\\temp\\ROOT", "6u5fBBcJOVyZnd9CjbGBslUypGZFBp");
        l2 = System.currentTimeMillis();
        System.out.println("3 complete "+((l2 - l1) / 1000));
        l1 = System.currentTimeMillis();

        util.decryptUnzip("F:\\work\\HiBPM\\dsp-resource\\install4.dsp", "F:\\work\\HiBPM\\dsp-resource\\temp\\ROOT", "6u5fBBcJOVyZnd9CjbGBslUypGZFBp");
        l2 = System.currentTimeMillis();
        System.out.println("4 complete "+((l2 - l1) / 1000));
        l1 = System.currentTimeMillis();

        util.decryptUnzip("F:\\work\\HiBPM\\dsp-resource\\install5.dsp", "F:\\work\\HiBPM\\dsp-resource\\temp\\ROOT", "6u5fBBcJOVyZnd9CjbGBslUypGZFBp");
        l2 = System.currentTimeMillis();
        System.out.println("5 complete "+((l2 - l1) / 1000));
        l1 = System.currentTimeMillis();

//        long l2 = System.currentTimeMillis();
//        System.out.println((l2 - l1) + "毫秒.");
//        System.out.println(((l2 - l1) / 1000) + "秒.");

    }
}
