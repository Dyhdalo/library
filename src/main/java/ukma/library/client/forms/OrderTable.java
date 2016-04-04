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
    private int userId;
    public OrderTable(int userId) {
        super("Зарезервувати примірник");
        this.userId = userId;
        try {
            this.books = LibraryClient.library.getAllBooks();
        } catch (Exception e) {
            this.books = new ArrayList<>();
            e.printStackTrace();
        }
        prepareGUI();
    }

    public static void main(String[] args){
        OrderTable swingControlDemo = new OrderTable(0);
        swingControlDemo.showEventDemo();
        swingControlDemo.getAllBooksTable().setModel(new BooksTable(new ArrayList<Book>()));
    }

    private void prepareGUI(){
        this.setSize(700, 200);
        this.setLayout(new BorderLayout());
        headerLabel = new JLabel("Зарезервувати примірник", JLabel.CENTER );
        errorLabel = new JLabel("", JLabel.CENTER );
        errorLabel.setVisible(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        /*topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
*/

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.setSize(new Dimension(700, 50));
        bottomPanel.setMaximumSize(new Dimension(700, 50));
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
        //userIdLabel = new JLabel("Логін користувача: ", JLabel.RIGHT );
        //userIdField = new JTextField(70);


        JButton addButton = new JButton("Зарезервувати");
        JButton backButton = new JButton("Повернутися назад");

        addButton.addActionListener(new AddButtonClickListener());
        backButton.addActionListener(new BackButtonClickListener());

        //bottomPanel.add(userIdLabel);
        //bottomPanel.add(userIdField);


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
            if(userId == 0) {
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
                System.out.println(bookId);
                System.out.println(b);
                if(b != null) {
                    Copy copy = LibraryClient.library.getFreeCopy(b);
                    if(copy != null) {
                        order.setCopyId(copy.getIsbn());
                        LibraryClient.library.addOrder(order);
                    } else {
                        JOptionPane.showMessageDialog(null, "Не має вільних примірників. Станьте в чергу або спробуйте пізніше.", "", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Внутрішня помилка. Повторіть спробу пізніше.", "", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
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
