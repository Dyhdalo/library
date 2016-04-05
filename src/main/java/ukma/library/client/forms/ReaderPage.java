package ukma.library.client.forms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.*;

import ukma.library.client.LibraryClient;
import ukma.library.server.entity.User;

public class ReaderPage extends JFrame {
    JPanel panel;
    JPanel mainPanel;
    JTextField nameField;
    JTextField phoneField;
    JPasswordField passwordField;
    JComboBox role;
    JButton addButton;
    JButton backButton;
    JButton updateButton;

    private User user;

    final String[] roles = {"LIBRARIAN", "USER"};

    public ReaderPage() {
        showGUI();
    }

    public ReaderPage(User user){
        this.user = user;
        showGUI();
    }

    private void showGUI (){
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
        updateButton = new JButton("Зберегти");
        backButton = new JButton("Повернутися назад");

        addButton.addActionListener(new AddButtonClickListener());
        updateButton.addActionListener(new UpdateButtonClickListener());
        backButton.addActionListener(new BackButtonClickListener());

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
        panel.add(backButton);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    void showUpdateReader() {

        nameField.setText(this.user.getName());
        phoneField.setText(this.user.getPhone());
        passwordField.setText(this.user.getPassword());

        mainPanel.add(new JLabel("Введіть нове ім'я: "));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Введіть новий номер телефону: "));
        mainPanel.add(phoneField);
        mainPanel.add(new JLabel("Введіть новий пароль: "));
        mainPanel.add(passwordField);
        this.add(mainPanel);
        this.add(panel);
        panel.add(updateButton, BorderLayout.PAGE_END);
        panel.add(backButton);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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



    private void back(){
        setVisible(false);
        dispose();
    }


    private class AddButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = new User();
            user.setName(nameField.getText());
            user.setPhone(phoneField.getText());
            user.setPassword(passwordField.getText());
            System.out.println(role.getSelectedItem().toString());
            user.setRole(role.getSelectedItem().toString());


            try {
                LibraryClient.library.addUser(user);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class BackButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            back();
        }
    }

    private class UpdateButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            user.setName(nameField.getText());
            user.setPhone(phoneField.getText());
            user.setPassword(passwordField.getText());

            try {
                LibraryClient.library.updateUser(user);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }
}
