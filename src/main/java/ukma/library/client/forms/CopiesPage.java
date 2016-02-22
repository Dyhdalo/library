package ukma.library.client.forms;

import ukma.library.server.entity.Copy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by adobrianskiy on 22.02.16.
 */
public class CopiesPage extends JFrame {
    private JLabel headerLabel;
    private JLabel errorLabel;

    private JLabel isbnLabel;
    private JTextField isbnField;


    private JPanel controlPanel;
    private JPanel mainPanel;

    private int bookId;
    public CopiesPage(int bookId){
        super("Додати примірник");
        this.bookId = bookId;
        prepareGUI();
    }

    public static void main(String[] args){
        CopiesPage swingControlDemo = new CopiesPage(1);
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI(){
        this.setSize(350,300);
        this.setLayout(new GridLayout(4, 1));

        headerLabel = new JLabel("Додати новий примірник", JLabel.CENTER );
        errorLabel = new JLabel("", JLabel.CENTER );
        errorLabel.setVisible(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

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
        isbnLabel = new JLabel("ISBN: ", JLabel.RIGHT );

        isbnField = new JTextField(70);


        JButton addButton = new JButton("Додати");
        JButton backButton = new JButton("Повернутися назад");

        addButton.addActionListener(new AddButtonClickListener());
        backButton.addActionListener(new BackButtonClickListener());

        mainPanel.add(isbnLabel);
        mainPanel.add(isbnField);

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
            if(!Copy.validateIsbn13(isbnField.getText())) {
                errorLabel.setText("Невірний ISBN номер");
            } else {
                int isbn = Integer.parseInt(isbnField.getText());
                Copy c = new Copy(isbn, bookId);
                //TODO: Add a new copy to the database
                back();
            }
        }
    }

    private class BackButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            back();
        }
    }
}
