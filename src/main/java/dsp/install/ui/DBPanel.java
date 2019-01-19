package dsp.install.ui;

import dsp.install.domain.ConfigurationManager;
import dsp.install.domain.ConfigurationOfJDBC;
import dsp.install.domain.ConfigurationOfOracle;
import dsp.install.event.DspEvent;
import dsp.install.event.EventBus;
import dsp.install.event.IEventListener;
import dsp.install.exception.DspException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.TRAILING;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/**
* @author GQ
* Date:2019/1/7
* Time:4:52 PM
*/
public class DBPanel extends DspPanel implements IEventListener {
    public DBPanel() {
        configs[0] = ConfigurationManager.getInstance().createJDBCConfiguraion(true);
        configs[1] = ConfigurationManager.getInstance().createJDBCConfiguraion(false);
        EventBus.getInstance().registeListener(this);
        initUI();
    }

    private int FIELD_WIDTH = 20;
    private int FIELD_WIDTH2 = 6;

    private ConfigurationOfJDBC[] configs = new ConfigurationOfJDBC[2];

    private JLabel orcleTX, mysqlTX;

    private int dbIndex = 0;

    private JTextField[] ipField = new JTextField[2];
    private JTextField[] portField = new JTextField[2];
    private JTextField[] nameField = new JTextField[2];
    private JTextField[] userField = new JTextField[2];
    private JTextField[] passwordField = new JTextField[2];

    private void initUI() {

        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        ButtonGroup bg = new ButtonGroup();
        JRadioButton oracle = new JRadioButton("oracle", true);
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
                dbIndex = 1;
            }
        });
        oracle.addItemListener((ItemEvent event) -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                cardLayout.first(configPanel);
                dbIndex = 0;
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
                .addGap(20)
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
        ipField[1] = new JTextField(configs[1].getUrl(),FIELD_WIDTH);
        addFocusListener(ipField[1],false);
        JLabel port = new JLabel("端口:");
        portField[1] = new JTextField(configs[1].getPort(), FIELD_WIDTH2);
        addFocusListener(portField[1], false);
        JLabel name = new JLabel("数据库名称：");
        nameField[1] = new JTextField(configs[1].getName(),FIELD_WIDTH);
        addFocusListener(nameField[1],false);
        JLabel login = new JLabel("认证：");
        JLabel user = new JLabel("用户名：");
        userField[1] = new JTextField(configs[1].getUser(),FIELD_WIDTH);
        addFocusListener(userField[1], false);
        JLabel password = new JLabel("密码：");
        passwordField[1] = new JTextField(configs[1].getPassword(),FIELD_WIDTH);
        addFocusListener(passwordField[1], false);
        mysqlTX = new JLabel("连接成功");

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addGroup(gl.createSequentialGroup()
                                .addGroup(gl.createParallelGroup(TRAILING)
                                        .addComponent(type)
                                        .addComponent(ip)
                                        .addComponent(name)
                                        .addComponent(user)
                                        .addComponent(password))
                                .addGroup(gl.createParallelGroup()
                                        .addComponent(typeField)
                                        .addGroup(gl.createSequentialGroup()
                                                .addComponent(ipField[1], -1, -1, -2)
                                                .addGap(20)
                                                .addComponent(port)
                                                .addComponent(portField[1], -1, -1, -2)
                                        )
                                        .addComponent(nameField[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userField[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordField[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                        )
                        .addComponent(mysqlTX)
                )
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
                                .addComponent(ipField[1])
                                .addGap(10)
                                .addComponent(port)
                                .addComponent(portField[1])
                        )
                )
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(name)
                        .addComponent(nameField[1]))
                .addGap(20)
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(user)
                        .addComponent(userField[1]))
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(password)
                        .addComponent(passwordField[1])
                )
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mysqlTX)
                .addGap(30)
        );
        return panel;
    }

    private void addFocusListener(JTextField field,boolean orcle){
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().trim().length() == 0) {
                    if (orcle) {
                        orcleTX.setVisible(false);
                    } else {
                        mysqlTX.setVisible(false);
                    }
                }
            }
        });
    }

    private JPanel getOraclePanel() {
        JPanel panel = getConfigPanel();
        GroupLayout gl = (GroupLayout) panel.getLayout();
        JLabel type = new JLabel("类型：");
        JLabel typeField = new JLabel("ORACLE");
        JLabel ip = new JLabel("主机名(IP)：");
        ipField[0] = new JTextField(configs[0].getUrl(),FIELD_WIDTH);
        addFocusListener(ipField[0],true);
        JLabel port = new JLabel("端口:");
        portField[0] = new JTextField(configs[0].getPort(), FIELD_WIDTH2);
        addFocusListener(portField[0],true);
        JLabel name = new JLabel("服务名：");
        nameField[0] = new JTextField(configs[0].getName(),FIELD_WIDTH);
        addFocusListener(nameField[0],true);
        JLabel login = new JLabel("认证：");
        //JLabel user = new JLabel("账号:");
        userField[0] = new JTextField(configs[0].getUser(),FIELD_WIDTH);
        addFocusListener(userField[0],true);
        JLabel password = new JLabel("口令：");
        passwordField[0] = new JPasswordField(configs[0].getPassword(),FIELD_WIDTH);
        addFocusListener(passwordField[0],true);
        orcleTX = new JLabel("连接成功");

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addGroup(gl.createSequentialGroup()
                                .addGroup(gl.createParallelGroup(TRAILING)
                                        .addComponent(type)
                                        .addComponent(ip)
                                        .addComponent(name)
                                        .addComponent(login)
                                        .addComponent(password))
                                .addGroup(gl.createParallelGroup()
                                        .addComponent(typeField)
                                        .addGroup(gl.createSequentialGroup()
                                                .addComponent(ipField[0], -1, -1, -2)
                                                .addGap(20)
                                                .addComponent(port)
                                                .addComponent(portField[0], -1, -1, -2)
                                        )
                                        .addComponent(nameField[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userField[0], -1, -1, -2)
                                        .addComponent(passwordField[0], -1, -1, -2)
                                )
                        )
                        .addComponent(orcleTX)
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
                                .addComponent(ipField[0])
                                .addComponent(port)
                                .addComponent(portField[0])
                        )
                )
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(name)
                        .addComponent(nameField[0])
                )
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, 20)
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(login)
                        .addComponent(userField[0])
                )
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(password)
                        .addComponent(passwordField[0])
                )
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(orcleTX)
                .addGap(30)
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
        DspEvent dspEvent = new DspEvent(10, null);
        orcleTX.setVisible(false);
        mysqlTX.setVisible(false);
        EventBus.getInstance().fireListeners(dspEvent);
    }

    private String getFieldText(JTextField field, String info) {
        if (field.getText().trim().length() == 0) {
            throw new DspException("请输入"+info);
        }
        return field.getText().trim();
    }

    private void configJdbc() throws DspException {
        ConfigurationOfJDBC jdbc = configs[dbIndex];
        jdbc.setUrl(getFieldText(ipField[dbIndex],"主机名(IP)"));
        jdbc.setPort(getFieldText(portField[dbIndex], "端口号"));
        jdbc.setName(getFieldText(nameField[dbIndex], dbIndex == 0 ? "服务名" : "数据库名称"));
        jdbc.setUser(getFieldText(userField[dbIndex], "用户名"));
        jdbc.setPassword(getFieldText(passwordField[dbIndex],"口令"));
    }

    @Override
    public void handleEvent(DspEvent dspEvent) {

        if (dspEvent.getType() == DspEvent.DO_CONNECT_DB) {
            String[] db = {"ORACLE","MYSQL"};
            JLabel label = dbIndex == 0 ? orcleTX : mysqlTX;
            String result = "";
            label.setVisible(true);
            try {
                configJdbc();
                result = configs[dbIndex].testConnection();
            } catch (Exception e) {
                e.printStackTrace();
                if(e instanceof DspException){
                    label.setForeground(Color.red);
                    result = ((DspException) e).getDescription();
                }else {
                    result = db[dbIndex] + "连接失败！";
                }
            }

            if (ConfigurationOfOracle.OK.equals(result)) {
                label.setText(db[dbIndex]+"连接成功");
                label.setForeground(Color.blue);

                EventBus.getInstance().fireListeners(new DspEvent(DspEvent.CONNECT_DB_SUCCESS, null));
            }else{
                label.setForeground(Color.red);
                label.setText(result);
                EventBus.getInstance().fireListeners(new DspEvent(DspEvent.CONNECT_DB_FAIL, null));
            }
        }

    }
}
