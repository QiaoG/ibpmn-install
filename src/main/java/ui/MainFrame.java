package ui;

import pm.Model;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/*
* Author GQ
* Date:2018/12/31
* Time:11:27 AM
*/
public class MainFrame extends JFrame {
    public MainFrame() {
        initUI();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "确定退出系统? ", "提示 ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    System.exit(0);
                } else {
                    //super.windowClosing(e);
                    System.out.println("no close");
                }
            }
        });
    }

    private void initUI() {
        setPreferredSize(new Dimension(Model.getInstance().W_WIDE, Model.getInstance().W_HIGHT));

        Container cpane = getContentPane();
        GroupLayout gl = new GroupLayout(cpane);
        cpane.setLayout(gl);

        gl.setAutoCreateGaps(false);

        gl.setAutoCreateContainerGaps(true);

        JPanel top = new TopPanel();
        JPanel left = new LeftPanel();
        JPanel right = new RightPanel();
        JPanel bottom = new BottomPanel();

        int toph = Model.getInstance().TOP_HIGHT;
        int leftw = Model.getInstance().LEFT_WIDE;
        int bottomh = Model.getInstance().BOTTOM_HIGHT;
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
