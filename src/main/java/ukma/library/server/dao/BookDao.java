package ukma.library.server.dao;

import ukma.library.server.entity.Book;

import java.util.List;

public interface BookDao {

	public Book myTest();

	public boolean addBook(String title, String author, int year, String edition);

	public Book getBook(int id);

	public Book getBook(String title);

	public List<Book> getAllBooks();
}
