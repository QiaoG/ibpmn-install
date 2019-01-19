package dsp.install.ui;

import dsp.install.domain.ConfigurationManager;
import dsp.install.event.DspEvent;
import dsp.install.event.EventBus;
import dsp.install.event.IEventListener;

import javax.swing.*;

/**
* Author GQ
* Date:2019/1/7
* Time:4:54 PM
*/
public class ProcessPanel extends DspPanel implements IEventListener {
    public ProcessPanel() {
        initUI();
        EventBus.getInstance().registeListener(this);
    }

    private JTextArea area =new JTextArea();

    private JProgressBar progBar = new JProgressBar();

    private void initUI() {
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

//        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane spane = new JScrollPane(area);

        area.setLineWrap(true);
        area.setWrapStyleWord(true);

//        JProgressBar progBar = new JProgressBar();
        progBar.setStringPainted(true);
        JButton stopBtn = new JButton("停止");

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(spane)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(progBar)
                        .addComponent(stopBtn)));
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(spane)
                .addGroup(gl.createParallelGroup()
                        .addComponent(progBar)
                        .addComponent(stopBtn)));

    }

    @Override
    public void showMe(){
        ConfigurationManager.getInstance().initTasks();
        ConfigurationManager.getInstance().exexuteAllTask();
    }

    @Override
    public void handleEvent(DspEvent dspEvent) {
        if (dspEvent.getType() == DspEvent.TASKS_RUN_EXCEPTION) {

        }
    }
}
