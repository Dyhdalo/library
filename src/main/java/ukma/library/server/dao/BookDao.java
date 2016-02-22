package ukma.library.server.dao;

import ukma.library.server.entity.Book;

import java.util.Date;
import java.util.List;

public interface BookDao {

	public boolean addBook(String title, String author, Date date, String edition);

	public Book getBook(int id);

	public Book getBook(String title);

	public List<Book> getAllBooks();
}
