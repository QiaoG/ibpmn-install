package dsp.install.ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ItemEvent;

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

    private int FIELD_WIDTH = 20;
    private int FIELD_WIDTH2 = 6;

    private void initUI() {
        String lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
        System.out.println(lookAndFeel);

        int fieldWidth = 10*1;

        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        ButtonGroup bg = new ButtonGroup();
        JRadioButton oracle = new JRadioButton("oracle",true);
        JRadioButton mysql = new JRadioButton("mysql");
        bg.add(oracle);
        bg.add(mysql);

        JPanel configPanel = new JPanel();
        configPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        CardLayout cardLayout = new CardLayout();
        configPanel.setLayout(cardLayout);

        configPanel.add(getOraclePanel());
        configPanel.add(getMysqlPanel());

        mysql.addItemListener((ItemEvent event) -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                cardLayout.last(configPanel);
            }
        });
        oracle.addItemListener((ItemEvent event) -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                cardLayout.first(configPanel);
            }
        });

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addGroup(gl.createSequentialGroup()
                        .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(oracle)
                        .addComponent(mysql)
                        .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(configPanel)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(oracle)
                        .addComponent(mysql))
                .addComponent(configPanel)
        );
    }

    private JPanel getMysqlPanel() {
        JPanel panel = getConfigPanel();
        GroupLayout gl = (GroupLayout) panel.getLayout();
        JLabel type = new JLabel("类型：");
        JLabel typeField = new JLabel("MYSQL");
        JLabel ip = new JLabel("主机名(IP)：");
        JTextField ipField = new JTextField(FIELD_WIDTH);
        JLabel port = new JLabel("端口:");
        JTextField portField = new JTextField("3306",FIELD_WIDTH2);
        JLabel name = new JLabel("数据库名称：");
        JTextField nameField = new JTextField(FIELD_WIDTH);
        JLabel login = new JLabel("认证：");
        JLabel user = new JLabel("用户名：");
        JTextField userField = new JTextField(FIELD_WIDTH);
        JLabel password = new JLabel("密码：");
        JTextField passwordFiled = new JTextField(FIELD_WIDTH);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup(TRAILING)
                        .addComponent(type)
                        .addComponent(ip)
                        .addComponent(name)
                        .addComponent(user)
                        .addComponent(password))
                .addGroup(gl.createParallelGroup()
                        .addComponent(typeField)
                        .addGroup(gl.createSequentialGroup()
                                .addComponent(ipField,-1,-1,-2)
                                .addGap(20)
                                .addComponent(port)
                                .addComponent(portField,-1,-1,-2)
                        )
                        .addComponent(nameField,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addComponent(userField,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordFiled,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(type)
                        .addComponent(typeField)
                )
                .addGap(20)
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(ip)
                        .addGroup(gl.createParallelGroup(BASELINE)
                                .addComponent(ipField)
                                .addGap(10)
                                .addComponent(port)
                                .addComponent(portField)
                        )
                )
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(name)
                        .addComponent(nameField))
                .addGap(20)
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
        JLabel type = new JLabel("类型：");
        JLabel typeField = new JLabel("ORACLE");
        JLabel ip = new JLabel("主机名(IP)：");
        JTextField ipField = new JTextField(FIELD_WIDTH);
        JLabel port = new JLabel("端口:");
        JTextField portField = new JTextField("1521",FIELD_WIDTH2);
        JLabel name = new JLabel("服务名：");
        JTextField nameField = new JTextField(FIELD_WIDTH);
        JLabel login = new JLabel("认证：");
        //JLabel user = new JLabel("账号:");
        JTextField userField = new JTextField(FIELD_WIDTH);
        JLabel password = new JLabel("口令：");
        JTextField passwordFiled = new JTextField(FIELD_WIDTH);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup(TRAILING)
                        .addComponent(type)
                        .addComponent(ip)
                        .addComponent(name)
                        .addComponent(login)
                        .addComponent(password))
                .addGroup(gl.createParallelGroup()
                        .addComponent(typeField)
                        .addGroup(gl.createSequentialGroup()
                                .addComponent(ipField,-1,-1,-2)
                                .addGap(20)
                                .addComponent(port)
                                .addComponent(portField,-1,-1,-2)
                        )
                        .addComponent(nameField,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addComponent(userField,-1,-1,-2)
                        .addComponent(passwordFiled,-1,-1,-2)
                )
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(type)
                        .addComponent(typeField))
                .addGap(20)
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(ip)
                        .addGroup(gl.createParallelGroup(BASELINE)
                                .addComponent(ipField)
                                .addComponent(port)
                                .addComponent(portField)
                        )
                )
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(name)
                        .addComponent(nameField)
                )
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, 20)
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(login)
                        .addComponent(userField)
                )
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(password)
                        .addComponent(passwordFiled)
                )
        );
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
    public void showMe() {

    }
}
