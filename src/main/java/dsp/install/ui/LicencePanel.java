package dsp.install.ui;

import dsp.install.event.Event;
import dsp.install.event.EventBus;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

/*
* Author GQ
* Date:2019/1/7
* Time:4:51 PM
*/
public class LicencePanel extends DspPanel {

    public LicencePanel() {
        initUI();
    }

    ButtonGroup bg;

    private void initUI() {
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        JLabel title = new JLabel("产品安装许可协议");
        title.setFont(new Font("Serif", Font.PLAIN, 18));
        JLabel describe = new JLabel("请您在安装前仔细阅读协议，要继续安装您必须同意此协议的全部条款。");

        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane spane = new JScrollPane(area);

        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        bg = new ButtonGroup();
        JRadioButton accept = new JRadioButton("我接受该许可协议的条款");
        accept.setActionCommand("ok");
        accept.addChangeListener((ChangeEvent event) -> {
            LicencePanel.this.changeLicence();
        });
        JRadioButton notaccept = new JRadioButton("我不接受该许可协议的条款",true);
        notaccept.setActionCommand("not");
        notaccept.addChangeListener((ChangeEvent event) -> {
            LicencePanel.this.changeLicence();
        });
        bg.add(accept);
        bg.add(notaccept);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(title)
                .addComponent(describe)
                .addComponent(spane)
                .addComponent(accept)
                .addComponent(notaccept)
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(title)
                .addComponent(describe)
                .addComponent(spane)
                .addComponent(accept)
                .addComponent(notaccept)
        );

    }

    public void changeLicence(){
        EventBus.getInstance().fireListeners(new Event(0,"ok".equals(bg.getSelection().getActionCommand())));
    }

    @Override
    public void showMe(){
        changeLicence();
    }
}
