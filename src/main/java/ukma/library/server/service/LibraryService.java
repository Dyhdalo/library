package ukma.library.server.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ukma.library.server.entity.Book;
import ukma.library.server.entity.Order;
import ukma.library.server.entity.Queue;
import ukma.library.server.entity.User;

public interface LibraryService extends Remote{
	
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_URL = "jdbc:mysql://eu-cdbr-azure-west-d.cloudapp.net:3306/acsm_b775c39c99325aa?"
            + "user=b964a4c4dda69d&password=bc9ee508";
	
	public List<User> getAllUsers() throws RemoteException;
	
	public List<Book> getAllBooks() throws RemoteException;

	public boolean addBook(Book book);

	public boolean addUser(User user);

	public boolean addOrder(Order order);

	public boolean addQueue(Queue queue);

	public User getUserByName(String name);


}
