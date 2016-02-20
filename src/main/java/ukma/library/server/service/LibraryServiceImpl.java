package ukma.library.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ukma.library.server.dao.BookDao;
import ukma.library.server.dao.JdbcBookDao;
import ukma.library.server.dao.JdbcOrderDao;
import ukma.library.server.dao.JdbcSearchDao;
import ukma.library.server.dao.JdbcUserDao;
import ukma.library.server.dao.OrderDao;
import ukma.library.server.dao.SearchDao;
import ukma.library.server.dao.UserDao;
import ukma.library.server.entity.Book;

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
	
	public Book myTest() throws RemoteException{
		return bookDao.myTest();
	}

}
