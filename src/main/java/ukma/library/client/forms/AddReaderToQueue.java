package ukma.library.client.forms;

import ukma.library.client.LibraryClient;
import ukma.library.server.entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

public class AddReaderToQueue extends JFrame{
    private JLabel headerLabel;
    private JLabel userIdLabel;

    private JTextField userIdField;

    private JTable allBooks;
    private JPanel mainPanel;
    public AddReaderToQueue() {
        super("Зарезервувати примірник");
        prepareGUI();
    }

    public static void main(String[] args){
        AddReaderToQueue swingControlDemo = new AddReaderToQueue();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI(){
        this.setSize(800, 300);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("Зарезервувати примірник", JLabel.CENTER );
        mainPanel.add(headerLabel);
        allBooks();

        user();


        this.add(mainPanel);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    private void user(){
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(2, 2));

        userIdLabel = new JLabel("Логін користувача: ", JLabel.RIGHT );
        userIdField = new JTextField(70);

        JButton addButton = new JButton("Зарезервувати");
        JButton backButton = new JButton("Повернутися назад");

        addButton.addActionListener(new AddButtonClickListener());
        backButton.addActionListener(new BackButtonClickListener());
        bodyPanel.add(userIdLabel);
        bodyPanel.add(userIdField);
        bodyPanel.add(addButton);
        bodyPanel.add(backButton);

        mainPanel.add(bodyPanel);
    }
    private void allBooks() {
        allBooks = new JTable();
        allBooks.setPreferredScrollableViewportSize(new Dimension(700,300));
        allBooks.setSize(800, 500);

        JScrollPane allBooksPane = new JScrollPane(allBooks);
        allBooksPane.setPreferredSize(new Dimension(700,300));
        allBooksPane.setSize(800, 500);


        mainPanel.add(allBooksPane, BorderLayout.CENTER);
    }

    public JTable getAllBooksTable(){
        return this.allBooks;
    }
    void showEventDemo(){
        this.setVisible(true);
    }


    private void back(){
        setVisible(false); //you can't see me!
        dispose(); //Destroy the JFrame object
    }

    private class BackButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            back();
        }
    }
    private class AddButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            int[] rows = allBooks.getSelectedRows();
            if (rows.length > 0){
                String userLogin = userIdField.getText();
                Integer bookId = (Integer) allBooks.getValueAt(rows[0], 0);
                Queue queue = new Queue();
                queue.setBookId(bookId);
                try {
                    Integer userId = LibraryClient.library.getUserIdByLogin(userLogin);
                    queue.setUserId(userId);
                    if(userId == null) {
                        JOptionPane.showMessageDialog(null, "Неправильний логін користувача!!!", "", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        LibraryClient.library.addQueue(queue);
                    }
                } catch (RemoteException e1) {
                    JOptionPane.showMessageDialog(null, "Сталася внутрішня помилка. Повторіть операцію пізніше.", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Потрібно вибрати книгу!!!", "", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
