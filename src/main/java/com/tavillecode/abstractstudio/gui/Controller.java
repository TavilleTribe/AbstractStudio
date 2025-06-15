package com.tavillecode.abstractstudio.gui;

import com.tavillecode.abstractstudio.usart.UsartTools;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/6/9 12:50
 */
public class Controller {
    private Frame frame;
    private TextField textField;

    public Controller() {
        this.init();
        this.addListener();
        this.addTextField();
        this.addButton();
    }

    private void init() {
        this.frame = new Frame();
        this.frame.setAlwaysOnTop(true);
        this.frame.setLayout(null);
        this.frame.setBounds(500,500,500,300);
        this.frame.setBackground(Color.WHITE);
        this.frame.setAlwaysOnTop(true);

        this.frame.setVisible(true);
    }

    private void addListener() {
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UsartTools.closePort();
                System.exit(0);
            }
        });
    }

    private void addTextField() {
        this.textField = new TextField();
        this.textField.setBounds(20,100,200,30);
        this.frame.add(this.textField);
    }

    private void addButton() {
        Button button = new Button("发送数据");
        button.setBounds(20,50,100,50);
        button.setFont(new Font("黑体",Font.BOLD,15));
        button.addActionListener(e -> {
            String text = textField.getText();
            text = '@'+text+'\r'+'\n';
            try {
                UsartTools.action(text);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        this.frame.add(button);
    }
}
