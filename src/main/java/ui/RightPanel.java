package ui;

import event.IStatusChangeListener;
import event.StatusChangeEvent;
import pm.InstallStatus;

import javax.swing.*;
import java.awt.*;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/*
* Author GQ
* Date:2018/12/31
* Time:11:33 AM
*/
public class RightPanel extends JPanel implements IStatusChangeListener{
    public RightPanel(){
        InstallStatus.getInstance().registeListener(this);
        initUI();
    }

    private void initUI() {
        this.setBackground(Color.BLUE);
//        GroupLayout gl = new GroupLayout(this);
//        this.setLayout(gl);
        CardLayout cl = new CardLayout();

        this.setLayout(cl);
//        gl.setAutoCreateGaps(true);
//        gl.setAutoCreateContainerGaps(true);



    }

    @Override
    public void handle(StatusChangeEvent event) {

    }
}
