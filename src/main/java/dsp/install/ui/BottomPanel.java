package dsp.install.ui;

import dsp.install.domain.ConfigurationManager;
import dsp.install.domain.StatusModel;
import dsp.install.event.DspEvent;
import dsp.install.event.EventBus;
import dsp.install.event.IEventListener;
import dsp.install.exception.DspException;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/**
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

    private int status;

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
            if(status == 0) {
                StatusModel.getInstance().next();
            }else{
                nextButton.setEnabled(false);
                EventBus.getInstance().fireListeners(new DspEvent(DspEvent.DO_CONNECT_DB,null));
            }
        });

        closeButton.addActionListener((ActionEvent event) -> {
            if(ConfigurationManager.getInstance().getProfile() == 0){
                System.exit(0);
            }else {
                int option = JOptionPane.showConfirmDialog(null, "确定退出系统? ", "提示 ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public void handleEvent(DspEvent dspEvent) {
        if(dspEvent.getType() == DspEvent.LICENCE) {
            nextButton.setEnabled((Boolean) dspEvent.getValue());
        }
        if(dspEvent.getType() == DspEvent.SHOW_DB_CONFIG || dspEvent.getType() == DspEvent.CONNECT_DB_FAIL){
            nextButton.setText("测试连接");
            nextButton.setEnabled(true);
            this.status = 1;
        }
        if(dspEvent.getType() == DspEvent.CONNECT_DB_SUCCESS){
            nextButton.setText("下一页");
            nextButton.setEnabled(true);
            this.status = 0;
        }
        if(dspEvent.getType() == DspEvent.TASKS_RUN){
            this.preButton.setEnabled(false);
            this.nextButton.setEnabled(false);
        }
        if (dspEvent.getType() == DspEvent.TASKS_RUN_COMPLETE) {
            this.nextButton.setEnabled(true);
        }
    }
}
