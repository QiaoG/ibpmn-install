package ui;

import event.IStatusChangeListener;
import event.StatusChangeEvent;
import pm.InstallStatus;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/*
* Author GQ
* Date:2018/12/31
* Time:11:33 AM
*/
public class RightPanel extends JPanel implements IStatusChangeListener{
    public RightPanel(){
        InstallStatus.getInstance().registeListener(this);
        initUI();
    }

    private CardLayout cardLayout;

    private int currentIndex;

    private void initUI() {
        this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        cardLayout = new CardLayout();

        this.setLayout(cardLayout);

        this.add(new WelcomPanel());
        this.add(new LicencePanel());
        this.add(new FunctionPanel());
        this.add(new DBPanel());
        this.add(new ProcessPanel());
        this.add(new CompletePanel());

    }

    @Override
    public void handle(StatusChangeEvent event) {
        if(event.getStatus() > currentIndex){
            cardLayout.next(this);
            currentIndex++;
        }else {
            cardLayout.previous(this);
            currentIndex--;
        }
    }
}
