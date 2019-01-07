import ui.MainFrame;

import javax.swing.*;
import java.awt.*;

/*
* Author GQ
* Date:2018/12/31
* Time:11:07 AM
*/
public class InstallApplication {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            JFrame ex = new MainFrame();
            ex.setVisible(true);
        });
    }
}
