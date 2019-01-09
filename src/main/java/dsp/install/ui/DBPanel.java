package dsp.install.ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.TRAILING;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/*
* Author GQ
* Date:2019/1/7
* Time:4:52 PM
*/
public class DBPanel extends DspPanel {
    public DBPanel() {
        initUI();
    }

    private void initUI() {
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        ButtonGroup bg = new ButtonGroup();
        JRadioButton mysql = new JRadioButton("mysql", true);
        JRadioButton oracle = new JRadioButton("oracle");
        bg.add(mysql);
        bg.add(oracle);

        JPanel configPanel = new JPanel();
        configPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        CardLayout cardLayout = new CardLayout();
        configPanel.setLayout(cardLayout);
        configPanel.add(getMysqlPanel());
        configPanel.add(getOraclePanel());

        mysql.addChangeListener((ChangeEvent event) -> {
            cardLayout.first(configPanel);
        });
        oracle.addChangeListener((ChangeEvent event) -> {
            cardLayout.last(configPanel);
        });

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addGroup(gl.createSequentialGroup()
                        .addPreferredGap(RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addComponent(mysql)
                        .addComponent(oracle)
                        .addPreferredGap(RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))
                .addComponent(configPanel)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(mysql)
                        .addComponent(oracle))
                .addComponent(configPanel)
        );
    }

    private JPanel getMysqlPanel() {
        JPanel panel = getConfigPanel();
        GroupLayout gl = (GroupLayout) panel.getLayout();
        JLabel type = new JLabel("数据库类型：");
        JTextField typeField = new JTextField(10);
        JLabel ip = new JLabel("数据库IP地址：");
        JTextField ipField = new JTextField(10);
        JLabel name = new JLabel("数据库名称：");
        JTextField nameField = new JTextField(10);
        JLabel user = new JLabel("用户名：");
        JTextField userField = new JTextField(10);
        JLabel password = new JLabel("用户密码：");
        JTextField passwordFiled = new JTextField(10);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup(TRAILING)
                        .addComponent(type)
                        .addComponent(ip)
                        .addComponent(name)
                        .addComponent(user)
                        .addComponent(password))
                .addGroup(gl.createParallelGroup()
                        .addComponent(typeField)
                        .addComponent(ipField)
                        .addComponent(nameField)
                        .addComponent(userField)
                        .addComponent(passwordFiled))
                .addPreferredGap(RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(type)
                        .addComponent(typeField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(ip)
                        .addComponent(ipField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(name)
                        .addComponent(nameField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(user)
                        .addComponent(userField))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(password)
                        .addComponent(passwordFiled))
        );
        return panel;
    }

    private JPanel getOraclePanel() {
        JPanel panel = getConfigPanel();
        GroupLayout gl = (GroupLayout) panel.getLayout();
        return panel;
    }

    private JPanel getConfigPanel() {
        JPanel panel = new JPanel();
        GroupLayout gl = new GroupLayout(panel);
        panel.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);
        return panel;
    }

    @Override
    public void showMe(){

    }
}
