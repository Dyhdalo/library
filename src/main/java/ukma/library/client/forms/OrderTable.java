package ukma.library.client.forms;

import ukma.library.server.entity.Copy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderTable extends JFrame{
    private JLabel headerLabel;
    private JLabel errorLabel;

    private JLabel bookIdLabel;
    private JTextField bookIdField;
    private JLabel userIdLabel;
    private JTextField userIdField;


    private JPanel controlPanel;
    private JPanel mainPanel;

    public OrderTable(){
        super("Зарезервувати примірник");
        prepareGUI();
    }

    public static void main(String[] args){
        OrderTable swingControlDemo = new OrderTable();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI(){
        this.setSize(350,300);
        this.setLayout(new GridLayout(4, 1));

        headerLabel = new JLabel("Зарезервувати примірник", JLabel.CENTER );
        errorLabel = new JLabel("", JLabel.CENTER );
        errorLabel.setVisible(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2));

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        this.add(headerLabel);
        this.add(errorLabel);
        this.add(mainPanel);

        this.add(controlPanel);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    void showEventDemo(){
        bookIdLabel = new JLabel("Айді книги: ", JLabel.RIGHT );
        userIdLabel = new JLabel("Логін користувача: ", JLabel.RIGHT );

        bookIdField = new JTextField(70);
        userIdField = new JTextField(70);


        JButton addButton = new JButton("Зарезервувати");
        JButton backButton = new JButton("Повернутися назад");

        addButton.addActionListener(new AddButtonClickListener());
        backButton.addActionListener(new BackButtonClickListener());

        mainPanel.add(bookIdLabel);
        mainPanel.add(bookIdField);
        mainPanel.add(userIdLabel);
        mainPanel.add(userIdField);

        controlPanel.add(addButton);
        controlPanel.add(backButton);

        this.setVisible(true);
    }


    private void back(){
        System.out.println("Back");
    }

    private class AddButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            errorLabel.setVisible(false);
            // TODO: Validation and reservation
        }
    }

    private class BackButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            back();
        }
    }
}
