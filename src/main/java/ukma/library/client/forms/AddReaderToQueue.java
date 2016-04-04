package ukma.library.client.forms;

import ukma.library.client.LibraryClient;
import ukma.library.client.forms.tables.ReadersTable;
import ukma.library.server.entity.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddReaderToQueue extends JFrame{

    private JTable allReaders;
    private JPanel mainPanel;
    
    public int book_id;
    
    public AddReaderToQueue(int book_id) {
        super("Зарезервувати примірник");
        
        this.book_id = book_id;
        
        prepareGUI();
    }

    public static void main(String[] args){
        AddReaderToQueue swingControlDemo = new AddReaderToQueue(2);
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI(){
        this.setSize(800, 300);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setLayout(new GridLayout(3, 1));

        allReaders();
        
        ArrayList<User> users = null;
		try {
			users = (ArrayList<User>) LibraryClient.library.getAllUsers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        allReaders.setModel(new ReadersTable(users));

        user();

        this.add(mainPanel);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void user(){
        JPanel bodyPanel = new JPanel();

        JButton addButton = new JButton("Зарезервувати");
        JButton backButton = new JButton("Повернутися назад");

        addButton.addActionListener(new AddButtonClickListener());
        backButton.addActionListener(new BackButtonClickListener());
        bodyPanel.add(addButton);
        bodyPanel.add(backButton);

        mainPanel.add(bodyPanel, BorderLayout.PAGE_END);
    }
    
    private void allReaders() {
    	allReaders = new JTable();
    	allReaders.setPreferredScrollableViewportSize(new Dimension(700,300));
    	allReaders.setSize(800, 500);

        JScrollPane allBooksPane = new JScrollPane(allReaders);
        allBooksPane.setPreferredSize(new Dimension(700,300));
        allBooksPane.setSize(800, 500);


        mainPanel.add(allBooksPane, BorderLayout.CENTER);
    }

    public JTable getAllBooksTable(){
        return this.allReaders;
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
            int[] rows = allReaders.getSelectedRows();
            if (rows.length > 0){
                Integer userId = (Integer) allReaders.getValueAt(rows[0], 0);
                Queue queue = new Queue();
                queue.setBookId(book_id);
                queue.setUserId(userId);
                queue.setDate(new Date());
                try {
					LibraryClient.library.addQueue(queue);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                try {
					LibraryClient.librarianForm.getQueueOfBook().getReadersInQueueTable().setModel(new ReadersTable((ArrayList<User>) LibraryClient.library.getQueueForBook(book_id)));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }else{
                JOptionPane.showMessageDialog(null, "Потрібно вибрати книгу!!!", "", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
