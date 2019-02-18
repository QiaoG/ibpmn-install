package dsp.install.ui;

import dsp.install.domain.ConfigurationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
* Author GQ
* Date:2018/12/31
* Time:11:27 AM
*/
public class MainFrame extends JFrame {
    public MainFrame() {

        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        String lookAndFeel2 = UIManager.getCrossPlatformLookAndFeelClassName();

        if(PerformanceModel.getInstance().isWindows()) {
            try {
                UIManager.setLookAndFeel(lookAndFeel);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (UnsupportedLookAndFeelException e) {
                try {
                    UIManager.setLookAndFeel(lookAndFeel2);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }
            }
        }
        //UIManager.setLookAndFeel(lookAndFeel);
        initUI();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(ConfigurationManager.getInstance().getProfile() == 0) {
                    System.exit(0);

                }else{
                    int option = JOptionPane.showConfirmDialog(null, "确定退出系统? ", "提示 ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (option == JOptionPane.OK_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });
    }

    private void initUI() {
        setPreferredSize(new Dimension(PerformanceModel.getInstance().getFrameWidth(), PerformanceModel.getInstance().getFrameHight()));

        Container cpane = getContentPane();
        GroupLayout gl = new GroupLayout(cpane);
        cpane.setLayout(gl);

        gl.setAutoCreateGaps(false);

        gl.setAutoCreateContainerGaps(true);

        JPanel top = new TopPanel();
        JPanel left = new LeftPanel();
        JPanel right = new RightPanel();
        JPanel bottom = new BottomPanel();

        int toph = PerformanceModel.getInstance().getTopHight();
        int leftw = PerformanceModel.getInstance().getLeftWidth();
        int bottomh = PerformanceModel.getInstance().getBottomHight();
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(top)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(left, leftw, leftw, leftw)
                        .addComponent(right)
                )
                .addComponent(bottom)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(top, toph, toph, toph)
                .addGroup(gl.createParallelGroup()
                        .addComponent(left)
                        .addComponent(right)
                )
                .addComponent(bottom, bottomh, bottomh, bottomh)
        );


        //gl.linkSize(SwingConstants.HORIZONTAL, okButton, closeButton);

        pack();
        setResizable(false);
        setTitle("dps安装");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

}
