package ui;

import pm.Model;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/*
* Author GQ
* Date:2018/12/31
* Time:11:27 AM
*/
public class MainFrame extends JFrame {
    public MainFrame() {
        initUI();
    }

    private void initUI() {
        setPreferredSize(new Dimension(Model.getInstance().W_WIDE, Model.getInstance().W_HIGHT));

        Container cpane = getContentPane();
        GroupLayout gl = new GroupLayout(cpane);
        cpane.setLayout(gl);

        gl.setAutoCreateGaps(true);
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
                        .addComponent(left,leftw,leftw,leftw)
                        .addComponent(right)
                )
                .addComponent(bottom)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(top,toph,toph,toph)
                .addGroup(gl.createParallelGroup()
                        .addComponent(left)
                        .addComponent(right)
                )
                .addComponent(bottom,bottomh,bottomh,bottomh)
        );


        //gl.linkSize(SwingConstants.HORIZONTAL, okButton, closeButton);

        pack();
        setResizable(false);
        setTitle("ibpmn安装");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
