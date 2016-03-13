package ukma.library.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ukma.library.server.dao.BookDao;
import ukma.library.server.dao.impl.JdbcBookDao;
import ukma.library.server.dao.impl.JdbcOrderDao;
import ukma.library.server.dao.impl.JdbcSearchDao;
import ukma.library.server.dao.impl.JdbcUserDao;
import ukma.library.server.dao.OrderDao;
import ukma.library.server.dao.SearchDao;
import ukma.library.server.dao.UserDao;
import ukma.library.server.entity.Book;
import ukma.library.server.entity.Order;
import ukma.library.server.entity.Queue;
import ukma.library.server.entity.User;

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
	public boolean addBook(Book book) {
		return bookDao.addBook(book);
	}

	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public boolean addOrder(Order order) {
		return false;
	}

	@Override
	public boolean addQueue(Queue queue) {
		return false;
	}


}

}
