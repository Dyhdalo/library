package ukma.library.client.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ukma.library.client.LibraryClient;
import ukma.library.client.forms.tables.BooksTable;
import ukma.library.client.forms.tables.QueueTable;
import ukma.library.client.forms.tables.ReadersTable;
import ukma.library.server.entity.Book;
import ukma.library.server.entity.User;

public class LibrarianPage extends JFrame implements ActionListener{

	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JTable allBooks;
	JTable allReaders;
	
	QueueOfBook queueBook;

	JButton addBook;
	JButton addCopy;
	JButton changeBook;
	JButton showQueueOfBook;
	JButton showOrdersOfReader;
	JButton addReader;
	JButton changeReader;
	
	private JTextField jtfFilterBooks = new JTextField();
	private JTextField jtfFilterReaders = new JTextField();
	private TableRowSorter<TableModel> rowSorterBooks;
	private TableRowSorter<TableModel> rowSorterReaders;

	public LibrarianPage(ArrayList<Book> books, ArrayList<User> users) {
		super("Бібліотека НаУКМА");
		setLocation(200, 200);

		JTabbedPane tabby = new JTabbedPane();

		panel1 = new JPanel(new BorderLayout());

		allBooks();
		
		allBooks.setModel(new BooksTable(books));

		addBook = new JButton("Додати книгу");
		addBook.addActionListener(this);

		addCopy = new JButton("Додати примірник");
		addCopy.addActionListener(this);

		changeBook = new JButton("Редагувати книгу");
		changeBook.addActionListener(this);
		
		showQueueOfBook = new JButton("Черга на книгу");
		showQueueOfBook.addActionListener(this);

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
		newPanel.add(showQueueOfBook, BorderLayout.NORTH);
		newPanel.add(addBook, BorderLayout.WEST);
		newPanel.add(changeBook, BorderLayout.CENTER);
		newPanel.add(addCopy, BorderLayout.EAST);
		
		JPanel SearchPanel1 = new JPanel(new BorderLayout());
		SearchPanel1.add(new JLabel("Пошук: "), BorderLayout.WEST);
		SearchPanel1.add(jtfFilterBooks, BorderLayout.CENTER);
		newPanel.add(SearchPanel1, BorderLayout.AFTER_LAST_LINE);

		panel1.add(newPanel, BorderLayout.PAGE_END);

		panel2 = new JPanel(new BorderLayout());
		
		allReaders();
		
		allReaders.setModel(new ReadersTable(users));
		
		addReader = new JButton("Додати читача");
		addReader.addActionListener(this);

		changeReader = new JButton("Редагувати читача");
		changeReader.addActionListener(this);
		
		showOrdersOfReader = new JButton("Замовлення читача");
		showOrdersOfReader.addActionListener(this);
		
		rowSorterReaders = new TableRowSorter<>(allReaders.getModel());
		
		allReaders.setRowSorter(rowSorterReaders);
		
		jtfFilterReaders.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilterReaders.getText();

                if (text.trim().length() == 0) {
                	rowSorterReaders.setRowFilter(null);
                } else {
                	rowSorterReaders.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilterReaders.getText();

                if (text.trim().length() == 0) {
                	rowSorterReaders.setRowFilter(null);
                } else {
                	rowSorterReaders.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });

		JPanel newPanel2 = new JPanel(new BorderLayout());
		newPanel2.add(addReader, BorderLayout.WEST);
		newPanel2.add(changeReader, BorderLayout.CENTER);
		newPanel2.add(showOrdersOfReader, BorderLayout.EAST);
		
		JPanel SearchPanel2 = new JPanel(new BorderLayout());
		SearchPanel2.add(new JLabel("Пошук: "), BorderLayout.WEST);
		SearchPanel2.add(jtfFilterReaders, BorderLayout.CENTER);
		newPanel2.add(SearchPanel2, BorderLayout.AFTER_LAST_LINE);

		panel2.add(newPanel2, BorderLayout.PAGE_END);

		tabby.addTab("Книги", panel1);
		tabby.addTab("Читачі", panel2);
		add(tabby);
		
		tabby.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				allBooks.clearSelection();
				allReaders.clearSelection();
				}
			});
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public TableRowSorter<TableModel> getRowSorterBooks() {
		return rowSorterBooks;
	}

	public TableRowSorter<TableModel> getRowSorterReaders() {
		return rowSorterReaders;
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
	
	public QueueOfBook getQueueOfBook(){
		return this.queueBook;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBook) {
			(new BookTable(this)).showEventDemo();
		}else if (e.getSource() == addCopy) {
			int[] rows = allBooks.getSelectedRows();
			if (rows.length > 0){
				(new CopiesPage((Integer)allBooks.getValueAt(rows[0], 0))).showEventDemo();
			}else{
				JOptionPane.showMessageDialog(null, "Потрібно вибрати книгу!!!", "", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (e.getSource() == changeBook) {
			System.out.println("changeBook");
		}else if (e.getSource() == showQueueOfBook) {
			int[] rows = allBooks.getSelectedRows();
			if (rows.length > 0){
				queueBook = new QueueOfBook((Integer)allBooks.getValueAt(rows[0], 0), (String)allBooks.getValueAt(rows[0], 1));
			}else{
				JOptionPane.showMessageDialog(null, "Потрібно вибрати книгу!!!", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if (e.getSource() == addReader) {
			(new ReaderPage()).showAddReader();
		}else if (e.getSource() == changeReader) {
			int[] rows = allReaders.getSelectedRows();
			if (rows.length > 0){
				ReaderPage readerPage = new ReaderPage((Integer)allReaders.getValueAt(rows[0], 0));
			}else{
				JOptionPane.showMessageDialog(null, "Потрібно вибрати читача!!!", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if (e.getSource() == showOrdersOfReader) {
			System.out.println("showOrdersOfReader");
		}
	}
	
}
