package ui;

import pm.InstallStatus;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/*
* Author GQ
* Date:2019/1/6
* Time:5:13 PM
*/
public class BottomPanel extends JPanel {

    public BottomPanel() {
        initUI();
    }

    private void initUI() {
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        JButton preButton = new JButton("上一页");
        JButton nextButton = new JButton("下一页");
        JButton closeButton = new JButton("关闭");

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(preButton)
                .addComponent(nextButton)
                .addComponent(closeButton)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addComponent(preButton)
                        .addComponent(nextButton)
                        .addComponent(closeButton))
        );

        preButton.addActionListener((ActionEvent event) -> {
            InstallStatus.getInstance().previous();
        });

        nextButton.addActionListener((ActionEvent event) -> {
            InstallStatus.getInstance().next();
        });
    }
}
