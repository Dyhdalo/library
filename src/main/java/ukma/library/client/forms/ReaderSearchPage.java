package ukma.library.client.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ukma.library.client.LibraryClient;
import ukma.library.client.forms.tables.BooksTable;
import ukma.library.server.entity.Book;
import ukma.library.server.entity.Queue;

public class ReaderSearchPage extends JFrame implements ActionListener{

	JTable allBooks;
	
	JPanel panel1;
	
	JButton getInQueue;
	JButton haveCopies;
	
	private JTextField jtfFilterBooks = new JTextField();
	private TableRowSorter<TableModel> rowSorterBooks;
	
	int userId;
	
	public ReaderSearchPage(ArrayList<Book> books, int idOfUser){
		super("Бібліотека НаУКМА | Читач");
		setLocation(200, 200);
		
		userId = idOfUser;
	
		panel1 = new JPanel(new BorderLayout());
		
		allBooks();
		
		allBooks.setModel(new BooksTable(books));
		
		getInQueue = new JButton("Стати в чергу");
		getInQueue.addActionListener(this);
		
		haveCopies = new JButton("Має примірники?");
		haveCopies.addActionListener(this);
		
		rowSorterBooks = new TableRowSorter<>(allBooks.getModel());
		
		allBooks.setRowSorter(rowSorterBooks);
		
		jtfFilterBooks.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilterBooks.getText();

                if (text.trim().length() == 0) {
                	rowSorterBooks.setRowFilter(null);
                } else {
                	rowSorterBooks.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilterBooks.getText();

                if (text.trim().length() == 0) {
                	rowSorterBooks.setRowFilter(null);
                } else {
                	rowSorterBooks.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
		
		JPanel newPanel = new JPanel(new BorderLayout());
		newPanel.add(getInQueue, BorderLayout.WEST);
		newPanel.add(haveCopies, BorderLayout.EAST);
		
		JPanel SearchPanel1 = new JPanel(new BorderLayout());
		SearchPanel1.add(new JLabel("Пошук: "), BorderLayout.WEST);
		SearchPanel1.add(jtfFilterBooks, BorderLayout.CENTER);
		newPanel.add(SearchPanel1, BorderLayout.AFTER_LAST_LINE);
		
		panel1.add(newPanel, BorderLayout.PAGE_END);
		
		add(panel1);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
			int[] rows = allBooks.getSelectedRows();
			if (rows.length > 0){
				Queue queue = new Queue();
				queue.setBookId((Integer)allBooks.getValueAt(rows[0], 0));
                queue.setUserId(userId);
                queue.setDate(new Date());
                try {
					LibraryClient.library.addQueue(queue);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                JOptionPane.showMessageDialog(null, "Ви додані в чергу на цю книгу!!!", "", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "Потрібно вибрати книгу!!!", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if (e.getSource() == haveCopies) {
			System.out.println("haveCopies");
		}
	}
	
}
