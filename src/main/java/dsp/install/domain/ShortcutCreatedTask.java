package dsp.install.domain;

/**
 * @author:GQ Author Mail:gq_200508@126.com
 * Date:2019/1/20
 * Time:11:25
 */
public class ShortcutCreatedTask extends InstallTask {
    public ShortcutCreatedTask(String distDir) {
        super(distDir);
        setName("创建桌面快捷方式");
    }

    @Override
    public void execute() throws Exception {

    }
}
