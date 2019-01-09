package dsp.install.ui;

import javax.swing.*;

/*
* Author GQ
* Date:2019/1/6
* Time:12:29 PM
*/
public class WelcomPanel extends DspPanel {

    public WelcomPanel(){
        initUI();
    }

    private void initUI() {
        this.add(new JLabel("welcome!"));
    }

    @Override
    public void showMe(){

    }
}
