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
		return bookDao.addBook(book.getTitle(), book.getAuthor(), book.getYear(), book.getEdition(), book.getKeyWords());
	}
	
	@Override
	public List<Book> getActiveBooksByUser(int userId)throws RemoteException{
		return bookDao.getActiveBooksByUser(userId);
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
	public boolean deleteQueue(int id_book, int id_user)throws RemoteException{
		return searchDao.deleteOueue(id_book, id_user);
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
	public List<User> getQueueForBook(int book) throws RemoteException {
		return searchDao.getQueueForBook(book);
	}

	@Override
	public String getUserNameById(int id) throws RemoteException{
		User user = userDao.getUserById(id);
		return user==null? null: user.getName();
	}

	@Override
	// must return all books which has 0 copies in the library
	public List<Book> getAllToQueueBooks() throws RemoteException{
		return bookDao.getAllBooksWithoutCopies();
	}

	// must return user id or null
	public Integer getUserIdByLogin(String login) throws RemoteException{
		User user = userDao.getUserByName(login);
		return user==null? null:user.getId();
	}

	// must return Book or null
	public Book getBookById(Integer id) throws RemoteException{
		return bookDao.getBook(id);
	}

	public void updateBook(Book b) throws RemoteException{
		bookDao.updateBook(b.getId(), b.getTitle(), b.getAuthor(), b.getEdition(), b.getYear());
	}

	@Override
	public List<User> getAllUsersDebtors() throws RemoteException{
		return userDao.getAllUsersDebtors();
	}

	public void addCopy(Copy c) throws RemoteException{
		searchDao.addCopy(c);
	}

	@Override
	public void updateUser(User user) throws RemoteException {
		userDao.updateUser(user);
	}

	@Override
	public User getUserById(Integer id) throws RemoteException {
		return userDao.getUserById(id);
	}
}
