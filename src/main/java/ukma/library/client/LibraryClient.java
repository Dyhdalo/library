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
import javax.swing.JOptionPane;

import ukma.library.client.forms.LibrarianPage;
import ukma.library.client.forms.LoginForm;
import ukma.library.client.forms.ReaderSearchPage;
import ukma.library.client.forms.tables.BooksTable;
import ukma.library.client.forms.tables.ReadersTable;
import ukma.library.server.entity.Book;
import ukma.library.server.entity.User;
import ukma.library.server.service.LibraryService;

public class LibraryClient{

	public static final String SERVER_NAME = "Server";
	
	public static LibraryService library;
	
	private static ArrayList<Book> books;
	
	private static ArrayList<User> users;
	
	private static int idOfUser;
	private static String roleOfUser;
	
	static LibrarianPage librarianForm;
	
	public static void main(String[] args) throws RemoteException, NamingException, MalformedURLException, NotBoundException{
		
		Registry registry = LocateRegistry.getRegistry("localhost", 8888);
		
		library = (LibraryService) registry.lookup(SERVER_NAME);
		
		final LoginForm loginForm = new LoginForm();
	
		loginForm.getButton().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String login = loginForm.getLoginField().getText();
				@SuppressWarnings("deprecation")
				String password = loginForm.getPasswordField().getText();
				
				try {
					users = (ArrayList<User>) library.getAllUsers();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				idOfUser = getUserId(users, login, password);
				roleOfUser = getUserRole(users, login, password);
				
				if(roleOfUser==null) JOptionPane.showMessageDialog(null, "Неправильний логін/пароль!!!", "", JOptionPane.INFORMATION_MESSAGE);
				
				else
					if(roleOfUser.equals("Бібліотекар") && loginForm.getRole().getSelectedItem().equals("Бібліотекар")){
						
						loginForm.setVisible(false);
						
						librarianForm = new LibrarianPage();
						
						try {
							books = (ArrayList<Book>) library.getAllBooks();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						librarianForm.getAllBooksTable().setModel(new BooksTable(books));
						librarianForm.getAllReadersTable().setModel(new ReadersTable(users));
					}
				
			}

			private String getUserRole(ArrayList<User> users, String login,
					String password) {
				for (User u : users) {
					if (u.getName().equals(login) && u.getPassword().equals(password))
						return u.getRole();
				}
				return null;
			}

			private int getUserId(ArrayList<User> users, String login,
					String password) {
				for (User u : users) {
					if (u.getName().equals(login) && u.getPassword().equals(password))
						return u.getId();
				}
				return -1;
			}
			
		});
		
	}
	
}
