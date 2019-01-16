package dsp.install.ui;

import dsp.install.event.IStatusChangeListener;
import dsp.install.event.StatusChangeEvent;
import dsp.install.domain.StatusModel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemEvent;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/**
* Author GQ
* Date:2018/12/31
* Time:11:32 AM
*/
public class LeftPanel extends JPanel implements ChangeListener, IStatusChangeListener{
    public LeftPanel(){
        initUI();
        StatusModel.getInstance().registeListener(this);
    }

    private JRadioButton[] jbs = new JRadioButton[StatusModel.STEPS.length];

    private boolean lock;

    private void initUI() {
        this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        ButtonGroup bg = new ButtonGroup();
        int stepc = StatusModel.STEPS.length;
        for(int i = 0; i < stepc; i++){
            jbs[i] = new JRadioButton(StatusModel.STEPS[i]);
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
        updateState(0);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
//        System.out.println(e);
        if(lock){
            lock = false;
        }else {
            lock = true;
            jbs[StatusModel.getInstance().getCurrentStatus()].setSelected(true);
        }
    }

    //@Override
    public void itemStateChanged(ItemEvent e) {
        jbs[StatusModel.getInstance().getCurrentStatus()].setSelected(true);
    }

    @Override
    public void handleStatusChanged(StatusChangeEvent event) {
        updateState(event.getStatus());
        jbs[event.getStatus()].setSelected(true);
    }

    private void updateState(int index){
        for(int i = 0; i < jbs.length; i++){
            if(i == index){
                jbs[i].setEnabled(true);
            }else{
                jbs[i].setEnabled(false);
            }
        }
    }
}
