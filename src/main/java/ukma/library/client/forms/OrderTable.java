package ukma.library.client.forms;

import ukma.library.client.LibraryClient;
import ukma.library.client.forms.tables.BooksTable;
import ukma.library.server.entity.Book;
import ukma.library.server.entity.Copy;
import ukma.library.server.entity.Order;

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
    private JPanel bottomPanel;
    private List<Book> books;
    public OrderTable() {
        super("Зарезервувати примірник");
        try {
            this.books = LibraryClient.library.getAllBooks();
        } catch (Exception e) {
            this.books = new ArrayList<>();
            e.printStackTrace();
        }
        prepareGUI();
    }

    public static void main(String[] args){
        OrderTable swingControlDemo = new OrderTable();
        swingControlDemo.showEventDemo();
        swingControlDemo.getAllBooksTable().setModel(new BooksTable(new ArrayList<Book>()));
    }

    private void prepareGUI(){
        this.setSize(800, 300);
        this.setLayout(new BorderLayout());
        headerLabel = new JLabel("Зарезервувати примірник", JLabel.CENTER );
        errorLabel = new JLabel("", JLabel.CENTER );
        errorLabel.setVisible(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));

        /*topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
*/

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 2));

        //controlPanel = new JPanel();
        //controlPanel.setLayout(new FlowLayout());

        mainPanel.add(headerLabel);
        mainPanel.add(errorLabel);
        this.add(mainPanel);

        allBooks = new JTable();
        allBooks.setPreferredScrollableViewportSize(new Dimension(700,100));
        allBooks.setSize(700, 100);

        JScrollPane allBooksPane = new JScrollPane(allBooks);
        allBooksPane.setPreferredSize(new Dimension(700, 100));
        allBooksPane.setSize(700, 100);
        mainPanel.add(allBooksPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel);

        //this.add(controlPanel);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public JTable getAllBooksTable(){
        return this.allBooks;
    }

    void showEventDemo(){
        userIdLabel = new JLabel("Логін користувача: ", JLabel.RIGHT );
        userIdField = new JTextField(70);


        JButton addButton = new JButton("Зарезервувати");
        JButton backButton = new JButton("Повернутися назад");

        addButton.addActionListener(new AddButtonClickListener());
        backButton.addActionListener(new BackButtonClickListener());

        bottomPanel.add(userIdLabel);
        bottomPanel.add(userIdField);


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
            String userLogin = userIdField.getText();
            Integer userId = null;
            try {
                 userId = LibraryClient.library.getUserIdByLogin(userLogin);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            if(userId == null) {
                JOptionPane.showMessageDialog(null, "Не існує такого користувача в системі", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int[] rows = allBooks.getSelectedRows();
            if (rows.length < 1){
                JOptionPane.showMessageDialog(null, "Потрібно вибрати книгу!!!", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Integer bookId = (Integer) allBooks.getValueAt(rows[0], 0);
            Order order = new Order();
            order.setUserId(userId);

            try {
                Book b = LibraryClient.library.getBookById(bookId);
                Copy copy = LibraryClient.library.getFreeCopy(b);
                order.setCopyId(copy.getIsbn());
                LibraryClient.library.addOrder(order);
            } catch (RemoteException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Внутрішня помилка. Повторіть спробу пізніше.", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            back();
        }
    }

    private class BackButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            back();
        }
    }
}
