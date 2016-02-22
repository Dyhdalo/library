package ukma.library.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.swing.JFrame;

import ukma.library.client.forms.LibrarianPage;
import ukma.library.client.forms.LoginForm;
import ukma.library.client.forms.ReaderSearchPage;
import ukma.library.client.forms.tables.BooksTable;
import ukma.library.server.entity.Book;
import ukma.library.server.service.LibraryService;

public class LibraryClient {

	public static final String SERVER_NAME = "Server";
	
	static ArrayList<Book> allBooks = new ArrayList<Book>();
	
	static LibrarianPage librarianForm;
	
	public static void main(String[] args) throws RemoteException, NamingException, MalformedURLException, NotBoundException{
		
		/*Registry registry = LocateRegistry.getRegistry("localhost", 8888);
		
		final LibraryService library = (LibraryService) registry.lookup(SERVER_NAME);*/
		
		allBooks.add(new Book(1,"asd","qw","123",1994));
		allBooks.add(new Book(2,"asdasd","er","534",2012));
		allBooks.add(new Book(3,"ertg","gsd","897",2001));
		
		LoginForm loginForm = new LoginForm();
	
		loginForm.getButton().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				loginForm.setVisible(false);
				
				librarianForm = new LibrarianPage();
				
				librarianForm.getAllBooksTable().setModel(new BooksTable(allBooks));
			}
			
		});
	
		//System.out.println(titleBook.test);
		
	}
	
}
