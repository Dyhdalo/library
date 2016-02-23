package ukma.library.client.forms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ukma.library.client.LibraryClient;

public class ReaderPage extends JFrame {
    JPanel panel;
    JPanel mainPanel;
    JTextField nameField;
    JTextField phoneField;
    JPasswordField passwordField;
    JComboBox role;
    JButton addButton;


    final String[] roles = {"Бібліотекар", "Читач"};

    public ReaderPage() {
        this.setSize(350,300);
        this.setLayout(new GridLayout(4, 1));

        setTitle("Додавання нового читача");

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));
        nameField = new JTextField(10);
        phoneField = new JTextField(10);
        passwordField = new JPasswordField(10);
        role = new JComboBox(roles);
        addButton = new JButton("OK");



    }
    public static void main(String[] args){
        ReaderPage swingControlDemo = new ReaderPage();
        swingControlDemo.showAddReader();
    }
    void showAddReader(){

        mainPanel.add(new JLabel("Введіть ім'я: "));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Введіть номер телефону: "));
        mainPanel.add(phoneField);
        mainPanel.add(new JLabel("Введіть пароль: "));
        mainPanel.add(passwordField);
        mainPanel.add(new JLabel("Виберіть роль: "));
        mainPanel.add(role);
        this.add(mainPanel);
        this.add(panel);
        panel.add(addButton, BorderLayout.PAGE_END);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JButton getButton(){
        return this.addButton;
    }
    public JPasswordField getPasswordField(){
        return this.passwordField;
    }
    public JTextField getNameField(){
        return this.nameField;
    }
    public JTextField getPhoneField() {return this.phoneField;}
    public JComboBox getRole(){
        return this.role;
    }



}
