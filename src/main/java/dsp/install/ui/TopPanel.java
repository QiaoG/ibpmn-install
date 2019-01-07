package dsp.install.ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

/*
* Author GQ
* Date:2018/12/31
* Time:11:31 AM
*/
public class TopPanel extends JPanel {
    public TopPanel(){
        initUI();
    }

    private void initUI() {
        this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
//        this.setBackground(Color.yellow);
    }
}
