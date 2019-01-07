package ui;

import event.IStatusChangeListener;
import event.StatusChangeEvent;
import pm.InstallStatus;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/*
* Author GQ
* Date:2018/12/31
* Time:11:32 AM
*/
public class LeftPanel extends JPanel implements ChangeListener, IStatusChangeListener{
    public LeftPanel(){
        initUI();
    }

    private JRadioButton[] jbs = new JRadioButton[InstallStatus.STEPS.length];

    private boolean lock;

    private void initUI() {
        this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        ButtonGroup bg = new ButtonGroup();
        int stepc = InstallStatus.STEPS.length;
        for(int i = 0; i < stepc; i++){
            jbs[i] = new JRadioButton(InstallStatus.STEPS[i]);
            jbs[i].addChangeListener(this);
            bg.add(jbs[i]);
        }

        GroupLayout.ParallelGroup pg = gl.createParallelGroup();
        for(int i = 0; i < stepc; i++){
            pg.addComponent(jbs[i]);
        }
        gl.setHorizontalGroup(pg);

        GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
        for(int i = 0; i < stepc; i++){
            sg.addComponent(jbs[i]);
        }
        sg.addPreferredGap(RELATED,
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        gl.setVerticalGroup(sg);

        jbs[0].setSelected(true);
        InstallStatus.getInstance().registeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
//        System.out.println(e);
        if(lock){
            lock = false;
        }else {
            lock = true;
            jbs[InstallStatus.getInstance().getCurrentStatus()].setSelected(true);
        }
    }

    //@Override
    public void itemStateChanged(ItemEvent e) {
        jbs[InstallStatus.getInstance().getCurrentStatus()].setSelected(true);
    }

    @Override
    public void handle(StatusChangeEvent event) {
        for(int i = 0; i < jbs.length; i++){
            if(i < event.getStatus()){
                jbs[i].setEnabled(false);
            }else{
                jbs[i].setEnabled(true);
            }
        }
        jbs[event.getStatus()].setSelected(true);
    }
}
