package dsp.install.ui;

import dsp.install.event.IStatusChangeListener;
import dsp.install.event.StatusChangeEvent;
import dsp.install.domain.StatusModel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
* Author GQ
* Date:2018/12/31
* Time:11:33 AM
*/
public class RightPanel extends JPanel implements IStatusChangeListener{
    public RightPanel(){
        initUI();
        StatusModel.getInstance().registeListener(this);
    }

    private CardLayout cardLayout;

    private int currentIndex;

    private void initUI() {
        this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        cardLayout = new CardLayout();

        this.setLayout(cardLayout);

        this.add(new WelcomPanel());
        this.add(new LicencePanel());
        //this.add(new FunctionPanel());
        this.add(new DBPanel());
        this.add(new ProcessPanel());
        this.add(new CompletePanel());

    }

    @Override
    public void handleStatusChanged(StatusChangeEvent event) {
        if(event.getStatus() > currentIndex){
            cardLayout.next(this);
            currentIndex++;
        }else {
            cardLayout.previous(this);
            currentIndex--;
        }
//        if (currentIndex == 1) ((LicencePanel) this.getComponent(1)).changeLicence();
        ((DspPanel)this.getComponent(currentIndex)).showMe();
    }
}
