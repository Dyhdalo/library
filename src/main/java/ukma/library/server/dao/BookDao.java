package ukma.library.server.dao;

import ukma.library.server.entity.Book;

import java.util.Date;
import java.util.List;

public interface BookDao {

	public boolean addBook(String title, String author, int date, String edition, String keyWords);

	public Book getBook(int id);

	public Book getBook(String title);

	public List<Book> getAllBooks();

	public void updateBook(int id, String title, String author, String edition, int year );

}
