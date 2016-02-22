package ukma.library.client.forms;

import javafx.scene.control.Spinner;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

import javax.swing.*;

public class BookTable extends JFrame{

   // private JFrame mainFrame;

    private JLabel headerLabel;
    private JLabel errorLabel;

    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel editionLabel;
    private JLabel yearLabel;
    private JLabel keyWordsLabel;

    private JTextField titleField;
    private JTextField authorField;
    private JTextField keyWordsField;
    private JTextField editorField;
    private JSpinner yearSpinner;


    private JPanel controlPanel;
    private JPanel mainPanel;

    public BookTable(){
    	super("Add book");
        prepareGUI();
    }

    public static void main(String[] args){
        BookTable swingControlDemo = new BookTable();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI(){
       // mainFrame = new JFrame("Add book");
        this.setSize(400,400);
        this.setLayout(new GridLayout(4, 1));

        headerLabel = new JLabel("Add a new Book", JLabel.CENTER );
        errorLabel = new JLabel("Title", JLabel.CENTER );
        errorLabel.setVisible(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));

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
        titleLabel = new JLabel("Title", JLabel.RIGHT );
        authorLabel = new JLabel("Author", JLabel.RIGHT );
        editionLabel = new JLabel("Edition", JLabel.RIGHT );
        yearLabel = new JLabel("Year", JLabel.RIGHT );
        keyWordsLabel = new JLabel("Key Words", JLabel.RIGHT );

        titleField = new JTextField(70);
        authorField = new JTextField(70);
        editorField = new JTextField(50);

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SpinnerModel yearModel = new SpinnerNumberModel(currentYear, //initial value
                currentYear - 600, //min
                currentYear, //max
                1);                //step
        yearSpinner = new JSpinner(yearModel);


        keyWordsField = new JTextField(120);

        JButton addButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        addButton.addActionListener(new AddButtonClickListener());
        backButton.addActionListener(new BackButtonClickListener());

        mainPanel.add(titleLabel);
        mainPanel.add(titleField);
        mainPanel.add(authorLabel);
        mainPanel.add(authorField);
        mainPanel.add(editionLabel);
        mainPanel.add(editorField);
        mainPanel.add(yearLabel);
        mainPanel.add(yearSpinner);
        mainPanel.add(keyWordsLabel);
        mainPanel.add(keyWordsField);

        controlPanel.add(addButton);
        controlPanel.add(backButton);

        this.setVisible(true);
    }


    private void back(){
        System.out.println("Back");
    }
    private boolean validateTextField(String field, int minLength, String error) {
        if(field.length() >= minLength) {
            return true;
        } else {
            errorLabel.setVisible(true);
            errorLabel.setText(error);
            return false;
        }
    }
    private class AddButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            errorLabel.setVisible(false);
            if(!validateTextField(titleField.getText(), 5, "Title length must be greater than 5")) return;
            if(!validateTextField(authorField.getText(), 5, "Author length must be greater than 5")) return;
            if(!validateTextField(editorField.getText(), 1, "Edition length must be greater than 0")) return;
            if(!validateTextField(keyWordsField.getText(), 20, "Key words length must be greater than 0")) return;

            //TODO: Add a new book to the database

            back();
        }
    }

    private class BackButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            back();
        }
    }
}
