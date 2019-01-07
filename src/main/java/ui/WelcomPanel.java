package ui;

import javax.swing.*;

/*
* Author GQ
* Date:2019/1/6
* Time:12:29 PM
*/
public class WelcomPanel extends JPanel {

    public WelcomPanel(){
        initUI();
    }

    private void initUI() {
        this.add(new JLabel("welcome!"));
    }
}
