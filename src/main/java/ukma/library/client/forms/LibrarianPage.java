package ukma.library.client.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ukma.library.client.LibraryClient;

public class LibrarianPage extends JFrame implements ActionListener{

	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JTable allBooks;
	JTable allReaders;
	JTable allQueues;
	JTable allOrders;

	JButton addBook;
	JButton addCopy;
	JButton changeBook;
	JButton addReader;
	JButton changeReader;
	JButton addReaderToQueue;
	JButton addOrder;
	JButton closeOrder;

	public LibrarianPage() {
		super("Бібліотека НаУКМА");
		setLocation(200, 200);

		JTabbedPane tabby = new JTabbedPane();

		panel1 = new JPanel(new BorderLayout());

		allBooks();

		addBook = new JButton("Додати книгу");
		addBook.addActionListener(this);

		addCopy = new JButton("Додати примірник");

		changeBook = new JButton("Редагувати книгу");

		JPanel newPanel = new JPanel();
		newPanel.add(addBook);
		newPanel.add(addCopy);
		newPanel.add(changeBook);

		panel1.add(newPanel, BorderLayout.PAGE_END);

		panel2 = new JPanel(new BorderLayout());
		
		allReaders();
		
		addReader = new JButton("Додати читача");

		changeReader = new JButton("Редагувати читача");

		JPanel newPanel2 = new JPanel();
		newPanel2.add(addReader);
		newPanel2.add(changeReader);

		panel2.add(newPanel2, BorderLayout.PAGE_END);

		panel3 = new JPanel(new BorderLayout());
		
		allQueues();
		
		addReaderToQueue = new JButton("Додати читача до черги");

		JPanel newPanel3 = new JPanel();
		newPanel3.add(addReaderToQueue);

		panel3.add(newPanel3, BorderLayout.PAGE_END);

		panel4 = new JPanel(new BorderLayout());

		allOrders();
		
		addOrder = new JButton("Додати замовлення");

		closeOrder = new JButton("Закрити замовлення");

		JPanel newPanel4 = new JPanel();
		newPanel4.add(addOrder);
		newPanel4.add(closeOrder);

		panel4.add(newPanel4, BorderLayout.PAGE_END);

		tabby.addTab("Книги", panel1);
		tabby.addTab("Читачі", panel2);
		tabby.addTab("Черги на літературу", panel3);
		tabby.addTab("Замовлення", panel4);
		add(tabby);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void allOrders() {
		allOrders = new JTable();
		allOrders.setPreferredScrollableViewportSize(new Dimension(700,100));
		allOrders.setSize(800, 300);
		
		JScrollPane allOrdersPane = new JScrollPane(allOrders);
		allOrdersPane.setPreferredSize(new Dimension(700,100));
		allOrdersPane.setSize(800, 300);

		panel4.add(allOrdersPane, BorderLayout.CENTER);
	}

	private void allQueues() {
		allQueues = new JTable();
		allQueues.setPreferredScrollableViewportSize(new Dimension(700,100));
		allQueues.setSize(800, 300);
		
		JScrollPane allQueuesPane = new JScrollPane(allQueues);
		allQueuesPane.setPreferredSize(new Dimension(700,100));
		allQueuesPane.setSize(800, 300);

		panel3.add(allQueuesPane, BorderLayout.CENTER);
	}

	private void allReaders() {
		allReaders = new JTable();
		allReaders.setPreferredScrollableViewportSize(new Dimension(700,100));
		allReaders.setSize(800, 300);
		
		JScrollPane allReadersPane = new JScrollPane(allReaders);
		allReadersPane.setPreferredSize(new Dimension(700,100));
		allReadersPane.setSize(800, 300);

		panel2.add(allReadersPane, BorderLayout.CENTER);
	}

	private void allBooks() {
		allBooks = new JTable();
		allBooks.setPreferredScrollableViewportSize(new Dimension(700,100));
		allBooks.setSize(800, 300);
		
		JScrollPane allBooksPane = new JScrollPane(allBooks);
		allBooksPane.setPreferredSize(new Dimension(700,100));
		allBooksPane.setSize(800, 300);

		panel1.add(allBooksPane, BorderLayout.CENTER);
	}
	
	public JTable getAllBooksTable(){
		return this.allBooks;
	}
	
	public JTable getAllReadersTable(){
		return this.allReaders;
	}
	
	public JTable getAllQueuesTable(){
		return this.allQueues;
	}
	
	public JTable getAllOrdersTable(){
		return this.allOrders;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBook) {
			(new BookTable()).showEventDemo();
		} 
	}
	
}
