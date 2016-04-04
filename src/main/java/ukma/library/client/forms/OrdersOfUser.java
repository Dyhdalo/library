package ukma.library.client.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import ukma.library.client.LibraryClient;
import ukma.library.client.forms.tables.BooksTable;
import ukma.library.client.forms.tables.ReadersTable;
import ukma.library.server.entity.Book;
import ukma.library.server.entity.User;

public class OrdersOfUser extends JFrame implements ActionListener{
	
	JTable booksOfUser;
	
	JButton addOrder;
	JButton completeOrder;
	
	JPanel panel1;
	
	private int userId;
	
	public OrdersOfUser(int userId, String name){
		
        super("Замовлені книги користувача "+name);
        this.userId = userId;
        prepareGUI();
    }

	private void prepareGUI() {
		
		setSize(800, 300);
		
		panel1 = new JPanel(new BorderLayout());
		
		showOrdersOfUser();
		
		ArrayList<Book> books = null;
		try {
			books = (ArrayList<Book>) LibraryClient.library.getActiveBooksByUser(userId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		booksOfUser.setModel(new BooksTable(books));
		
		addOrder = new JButton("Додати замовлення");
		addOrder.addActionListener(this);

		completeOrder = new JButton("Завершити замовлення");
		completeOrder.addActionListener(this);
		
		JPanel newPanel = new JPanel();
		newPanel.add(addOrder);
		newPanel.add(completeOrder);
		
		panel1.add(newPanel, BorderLayout.PAGE_END);
		
		add(panel1);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
	}

	private void showOrdersOfUser() {
		booksOfUser = new JTable();
		booksOfUser.setPreferredScrollableViewportSize(new Dimension(700,100));
		booksOfUser.setSize(800, 300);
		
		JScrollPane allReadersPane = new JScrollPane(booksOfUser);
		allReadersPane.setPreferredSize(new Dimension(700,100));
		allReadersPane.setSize(800, 300);

		panel1.add(allReadersPane, BorderLayout.CENTER);
	}
	
	public JTable getBooksOfUserTable(){
		return this.booksOfUser;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addOrder){
			System.out.println("addOrder");
		}else if (e.getSource() == completeOrder){
			System.out.println("completeOrder");
		}
	}

}
