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
import ukma.library.client.forms.tables.ReadersTable;
import ukma.library.server.entity.User;

public class QueueOfBook extends JFrame implements ActionListener{

	JTable readersInQueue;
	
	JButton addToQueue;
	JButton deleteFromQueue;
	
	JPanel panel1;
	
	private int bookId;

	public QueueOfBook(int bookId, String name){
		
        super("Черга на книгу "+name);
        this.bookId = bookId;
        prepareGUI();
    }

	private void prepareGUI() {
		
		setSize(800, 300);
		
		panel1 = new JPanel(new BorderLayout());
		
		showReadersInQueue();
		
		ArrayList<User> users = null;
		try {
			users = (ArrayList<User>) LibraryClient.library.getQueueForBook(bookId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		readersInQueue.setModel(new ReadersTable(users));
		
		addToQueue = new JButton("Додати до черги");
		addToQueue.addActionListener(this);

		deleteFromQueue = new JButton("Видалити з черги");
		deleteFromQueue.addActionListener(this);
		
		JPanel newPanel = new JPanel();
		newPanel.add(addToQueue);
		newPanel.add(deleteFromQueue);
		
		panel1.add(newPanel, BorderLayout.PAGE_END);
		
		add(panel1);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
	}

	private void showReadersInQueue() {
		readersInQueue = new JTable();
		readersInQueue.setPreferredScrollableViewportSize(new Dimension(700,100));
		readersInQueue.setSize(800, 300);
		
		JScrollPane allReadersPane = new JScrollPane(readersInQueue);
		allReadersPane.setPreferredSize(new Dimension(700,100));
		allReadersPane.setSize(800, 300);

		panel1.add(allReadersPane, BorderLayout.CENTER);
	}
	
	public JTable getReadersInQueueTable(){
		return this.readersInQueue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addToQueue) {
			AddReaderToQueue addQueue = new AddReaderToQueue(bookId);
		}else if (e.getSource() == deleteFromQueue){
			System.out.println("deleteFromQueue");
		}
	}
	
}
