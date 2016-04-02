package ukma.library.client.forms;

import ukma.library.client.LibraryClient;
import ukma.library.server.entity.Book;
import ukma.library.server.entity.Copy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class OrderTable extends JFrame{
    private JLabel headerLabel;
    private JLabel errorLabel;

    private JLabel bookIdLabel;
    private JTextField bookIdField;
    private JLabel userIdLabel;
    private JTextField userIdField;

    private JTable allBooks;

    private JPanel controlPanel;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bodyPanel;
    private JPanel bottomPanel;
    private List<Book> books;
    public OrderTable() {
        super("Зарезервувати примірник");
        try {
            this.books = LibraryClient.library.getAllBooks();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        prepareGUI();
    }

    public static void main(String[] args){
        OrderTable swingControlDemo = new OrderTable();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI(){
        this.setSize(350,400);
        this.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("Зарезервувати примірник", JLabel.CENTER );
        errorLabel = new JLabel("", JLabel.CENTER );
        errorLabel.setVisible(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        /*topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
*/
        bodyPanel = new JPanel();
        bodyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 2));

        //controlPanel = new JPanel();
        //controlPanel.setLayout(new FlowLayout());

        this.add(headerLabel);
        this.add(errorLabel);
        this.add(mainPanel);

        mainPanel.add(bodyPanel);
        mainPanel.add(bottomPanel);

        //this.add(controlPanel);
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

        //mainPanel.add(bookIdLabel);
        //mainPanel.add(bookIdField);
        bottomPanel.add(userIdLabel);
        bottomPanel.add(userIdField);


        allBooks = new JTable();
        allBooks.setPreferredScrollableViewportSize(new Dimension(700,100));
        allBooks.setSize(800, 300);

        JScrollPane allBooksPane = new JScrollPane(allBooks);
        allBooksPane.setPreferredSize(new Dimension(700,100));
        allBooksPane.setSize(800, 300);

        for(Book item : this.books){
            System.out.println(item);
        }
        bodyPanel.add(allBooksPane, BorderLayout.CENTER);

        bottomPanel.add(addButton);
        bottomPanel.add(backButton);

        this.setVisible(true);
    }


    private void back(){
        setVisible(false); //you can't see me!
        dispose(); //Destroy the JFrame object
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
