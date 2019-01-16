package dsp.install.ui;

import dsp.install.domain.ConfigurationManager;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.io.File;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/**
* Author GQ
* Date:2019/1/6
* Time:12:29 PM
*/
public class WelcomPanel extends DspPanel {

    public WelcomPanel(){
        initUI();
    }

    private JTextField dir = new JTextField();

    private void initUI() {
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        JLabel info = new JLabel("welcome");
        JLabel des = new JLabel("安装目录：");
        dir.setEditable(false);
        JButton openBtn = new JButton("浏览");
        openBtn.addActionListener(new OpenDirAction());

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(info)
                .addComponent(des)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(dir)
                        .addComponent(openBtn)
                )
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(info)
                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(des)
                .addGroup(gl.createParallelGroup(BASELINE)
                        .addComponent(dir)
                        .addComponent(openBtn)
                )
        );
    }

    @Override
    public void showMe(){
        FileSystemView fsv = FileSystemView.getFileSystemView();
        dir.setText(fsv.getDefaultDirectory().toString());
        ConfigurationManager.getInstance().setDistDir(fsv.getDefaultDirectory().toString());
    }

    private class OpenDirAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            int result = 0;
            File file = null;
            String path = null;
            JFileChooser fileChooser = new JFileChooser();
            FileSystemView fsv = FileSystemView.getFileSystemView();
            //System.out.println(fsv.getHomeDirectory());
            fileChooser.setCurrentDirectory(fsv.getDefaultDirectory());
            fileChooser.setDialogTitle("请选择安装目录...");
            fileChooser.setApproveButtonText("确定");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            result = fileChooser.showOpenDialog(WelcomPanel.this);
            if (JFileChooser.APPROVE_OPTION == result) {
                path=fileChooser.getSelectedFile().getPath();
                dir.setText(path);
                ConfigurationManager.getInstance().setDistDir(path);
            }
        }
    }
}
