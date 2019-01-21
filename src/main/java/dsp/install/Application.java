package dsp.install;

import dsp.install.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
* Author GQ
* Date:2018/12/31
* Time:11:07 AM
 * 精简jre（-verbose:class）
 * rt.jar
 * idea_rt.jar
 * charset.jar
 * ext/localedata.jar
*/
public class Application {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            JFrame ex = new MainFrame();
            ex.setVisible(true);
        });
    }
}
