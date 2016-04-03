package ukma.library.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.List;

import ukma.library.server.dao.*;
import ukma.library.server.dao.impl.*;
import ukma.library.server.entity.*;

public class LibraryServiceImpl extends UnicastRemoteObject implements LibraryService{

	private BookDao bookDao;
	private OrderDao orderDao;
	private SearchDao searchDao;
	private UserDao userDao;
	
	public LibraryServiceImpl() throws RemoteException {
		
		bookDao = new JdbcBookDao();
		orderDao = new JdbcOrderDao();
		searchDao = new JdbcSearchDao();
		userDao = new JdbcUserDao();
	}
	
	public List<User> getAllUsers() throws RemoteException{
		return userDao.getAllUsers();
	}
	
	public List<Book> getAllBooks() throws RemoteException{
		return bookDao.getAllBooks();
	}

	@Override
	public boolean addBook(Book book) throws RemoteException {
		return bookDao.addBook(book.getTitle(), book.getAuthor(), book.getYear(), book.getEdition());
	}

	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public boolean addOrder(Order order) throws RemoteException {
		return orderDao.addOrder(order);
	}

	@Override
	public boolean addQueue(Queue queue) throws RemoteException {
		return searchDao.addQueue(queue);
	}

	@Override
	public User getUserByName(String name) throws RemoteException {
		return userDao.getUserByName(name);
	}

	@Override
	public Copy getFreeCopy(Book book) throws RemoteException {
		return searchDao.getFreeCopy(book.getId());
	}

	@Override
	public List<Queue> getActiveQueue() throws RemoteException {
		return searchDao.getActiveQueue();
	}

	@Override
	public List<Queue> getQueueForBook(Book book) throws RemoteException {
		return searchDao.getQueueForBook(book);
	}

	// TODO: Add implementation
	public String getUserNameById(int id) throws RemoteException{
		return "user name";
	};

	// TODO: Add implementation
	// must return all books which has 0 copies in the library
	public List<Book> getAllToQueueBooks() throws RemoteException{
		return bookDao.getAllBooks();
	};

	// TODO: Add implementation
	// must return user id or null
	public Integer getUserIdByLogin(String login) throws RemoteException{
		return null;
	}

	// TODO: Add implementation
	// must return Book or null
	public Book getBookById(Integer id) throws RemoteException{
		return null;
	};
}
