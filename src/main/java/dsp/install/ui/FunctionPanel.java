package dsp.install.ui;

import javax.swing.*;
import java.awt.*;

/*
* Author GQ
* Date:2019/1/7
* Time:4:53 PM
*/
public class FunctionPanel extends JPanel{

    public FunctionPanel(){
        initUI();
    }

    private void initUI() {
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        JLabel title = new JLabel("DSP产品组建安装");
        title.setFont(new Font("Serif", Font.PLAIN, 18));
        JLabel describe = new JLabel("请选择要安装的组件，选择完后点击\"下一步\"。");
    }
}
