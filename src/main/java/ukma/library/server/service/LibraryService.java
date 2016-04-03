package ukma.library.server.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ukma.library.server.entity.*;

public interface LibraryService extends Remote{
	
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_URL = "jdbc:mysql://eu-cdbr-azure-west-d.cloudapp.net:3306/acsm_b775c39c99325aa?"
            + "user=b964a4c4dda69d&password=bc9ee508";
	
	public List<User> getAllUsers() throws RemoteException;
	
	public List<Book> getAllBooks() throws RemoteException;

	public boolean addBook(Book book)throws RemoteException ;

	public boolean addUser(User user)throws RemoteException ;

	public boolean addOrder(Order order)throws RemoteException ;

	public boolean addQueue(Queue queue)throws RemoteException ;

	public User getUserByName(String name) throws RemoteException;

	public Copy getFreeCopy(Book book)throws RemoteException ;

	public List<Queue> getActiveQueue()throws RemoteException ;

	public List<Queue> getQueueForBook(Book book)throws RemoteException ;

	public String getUserNameById(int id) throws RemoteException;

	public List<Book> getAllToQueueBooks() throws RemoteException;

	public Integer getUserIdByLogin(String login) throws RemoteException;

	public Book getBookById(Integer id) throws RemoteException;
}
