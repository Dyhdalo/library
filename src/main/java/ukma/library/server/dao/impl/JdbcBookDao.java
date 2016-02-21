package ukma.library.server.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import ukma.library.server.dao.BookDao;
import ukma.library.server.entity.Book;
import ukma.library.server.service.LibraryService;

public class JdbcBookDao implements BookDao {


	public boolean addBook(String title, String author, int year, String edition) {
		return false;
	}

	public Book getBook(int id) {
		return null;
	}

	public Book getBook(String title) {
		return null;
	}

	public List<Book> getAllBooks() {
		return null;
	}

	public Book myTest() {
		
		Book book = new Book();
		
//		try (Connection conn = DriverManager.getConnection(LibraryService.MYSQL_URL)){
//
//					PreparedStatement statement = null;
//					ResultSet rs = null;
//
//					try {
//						statement = conn.prepareStatement("SELECT * from acsm_b775c39c99325aa.Book");
//						rs = statement.executeQuery();
//						book.ok();
//					} catch (SQLException se) {
//						System.out.println("SQL Error: " + se);
//					}
//				}catch (SQLException se) {
//					System.out.println("Connection failed: " + se);
//				}
//
		return book;
		
	}

	private static final RowMapper<Book> rowMapper = new RowMapper<Book>() {

		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Book();
		}
	};

}
