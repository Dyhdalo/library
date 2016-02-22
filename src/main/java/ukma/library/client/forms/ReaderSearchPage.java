package ukma.library.client.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ReaderSearchPage extends JFrame implements ActionListener{

	JTable allBooks;
	
	JPanel panel1;
	
	JButton getInQueue;
	JButton haveCopies;
	
	public ReaderSearchPage(){
		super("Бібліотека НаУКМА | Читач");
		setLocation(200, 200);
	
		panel1 = new JPanel(new BorderLayout());
		
		allBooks();
		
		getInQueue = new JButton("Стати в чергу");
		getInQueue.addActionListener(this);
		
		haveCopies = new JButton("Має примірники?");
		haveCopies.addActionListener(this);
		
		JPanel newPanel = new JPanel();
		newPanel.add(getInQueue);
		newPanel.add(haveCopies);
		
		panel1.add(newPanel, BorderLayout.PAGE_END);
		
		add(panel1);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getInQueue) {
			System.out.println("getInQueue");
		}else if (e.getSource() == haveCopies) {
			System.out.println("haveCopies");
		}
	}
	
}
