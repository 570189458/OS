package com.os.homework.UI;

import javax.swing.*;
import java.awt.*;

public class TimeClip extends JPanel {
    JLabel timeClipLable = new JLabel("ʱ��Ƭ��С:");
    JTextField timeClipTF = new JTextField(6);
    JButton timeClipButton = new JButton("ȷ��");
    private static TimeClip instance;
    public static TimeClip getInstance(){
        if(instance == null)

            instance = new TimeClip();
        return instance;
    }
    TimeClip(){

        this.add(timeClipLable);
        this.add(timeClipTF);
        this.add(timeClipButton);



        this.setSize(750, 300);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBorder(BorderFactory.createTitledBorder ("����ʱ��Ƭ"));
    }
}
