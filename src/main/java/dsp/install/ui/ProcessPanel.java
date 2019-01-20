package dsp.install.ui;

import dsp.install.domain.ConfigurationManager;
import dsp.install.domain.InstallTask;
import dsp.install.event.DspEvent;
import dsp.install.event.EventBus;
import dsp.install.event.IEventListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.*;

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

    private JButton stopBtn;

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
        stopBtn = new JButton("停止");
        stopBtn.addActionListener((ActionEvent event) -> {
            ConfigurationManager.getInstance().setTaskStop(true);
            stopBtn.setText("停止中");
        });
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
        stopBtn.setText("停止");
        this.progBar.setValue(0);
        ConfigurationManager.getInstance().initTasks();
        this.progBar.setMinimum(0);
        this.progBar.setMaximum(100);
        area.setText("正在安装...");
        EventBus.getInstance().fireListeners(new DspEvent(DspEvent.TASKS_RUN,null));
        ExecutorService pool = newFixedThreadPool(1);
        pool.execute(() ->{
            ConfigurationManager.getInstance().exexuteAllTask();
        });

    }

    private void addMessage(String message) {
        area.append("\n"+message);
        area.setCaretPosition(area.getDocument().getLength());
    }

    @Override
    public void handleEvent(DspEvent dspEvent) {
        if (dspEvent.getType() == DspEvent.TASKS_RUN_EXCEPTION) {
            addMessage(dspEvent.getValue().toString());
            addMessage("安装失败。");
            stopBtn.setText("停止");
            this.progBar.setValue(0);
        }
        if(dspEvent.getType() == DspEvent.ONE_TASK_BEGIN_RUN){
            addMessage("正在"+dspEvent.getValue().toString()+"...");
        }
        if(dspEvent.getType() == DspEvent.ONE_TASK_END_RUN){
            addMessage(((InstallTask)dspEvent.getValue()).getName()+"成功！");
            this.progBar.setValue(this.progBar.getValue()+((InstallTask)dspEvent.getValue()).getProgressValue());
        }
        if(dspEvent.getType() == DspEvent.TASKS_RUN_COMPLETE){
            addMessage("程序安装成功！");
            this.progBar.setValue(0);
        }
    }
}
