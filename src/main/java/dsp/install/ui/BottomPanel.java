package dsp.install.ui;

import dsp.install.domain.StatusModel;
import dsp.install.event.Event;
import dsp.install.event.EventBus;
import dsp.install.event.IEventListener;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/*
* Author GQ
* Date:2019/1/6
* Time:5:13 PM
*/
public class BottomPanel extends JPanel implements IEventListener{

    public BottomPanel() {
        initUI();
        EventBus.getInstance().registeListener(this);
    }

    private JButton preButton,nextButton;

    private void initUI() {
//        this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(false);
        gl.setAutoCreateContainerGaps(true);

        preButton = new JButton("上一页");
        nextButton = new JButton("下一页");
        JButton closeButton = new JButton("关闭");

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(preButton)
                .addComponent(nextButton)
                .addComponent(closeButton)
        );

        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(preButton)
                .addComponent(nextButton)
                .addComponent(closeButton)
        );

        preButton.addActionListener((ActionEvent event) -> {
            StatusModel.getInstance().previous();
        });

        nextButton.addActionListener((ActionEvent event) -> {
            StatusModel.getInstance().next();
        });

        closeButton.addActionListener((ActionEvent event) -> {
            int option = JOptionPane.showConfirmDialog(null, "确定退出系统? ", "提示 ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
    }

    @Override
    public void handleEvent(Event event) {
        if(event.getType() != 0) return;
        nextButton.setEnabled((Boolean) event.getValue());
    }
}
