package ukma.library.server.dao.impl;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ukma.library.server.dao.BookDao;
import ukma.library.server.entity.Book;
import ukma.library.server.service.LibraryService;

public class JdbcBookDao implements BookDao {

	private final String GET_ALL_BOOKS = "SELECT * from acsm_b775c39c99325aa.Book";

	private final String ADD_NEW_BOOK = "INSERT INTO acsm_b775c39c99325aa.Book (author, title, edition ,year) VALUES (?,?,?,?)";


	private static Connection createConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(LibraryService.MYSQL_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private static void closeConnection(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public boolean addBook(String title, String author, java.util.Date date, String edition) {
		boolean flag = true;

		Connection conn = createConnection();
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement(ADD_NEW_BOOK);
			statement.setString(1,author);
			statement.setString(2,title);
			statement.setString(3,edition);
			statement.setDate(4,new Date(date.getTime()));
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}

		closeConnection(conn);
			return flag;
	}

	public Book getBook(int id) {
		return null;
	}

	public Book getBook(String title) {
		return null;
	}

	public List<Book> getAllBooks() {
		
		ArrayList<Book> books = new ArrayList<Book>();
		Connection conn = JdbcBookDao.createConnection();

		PreparedStatement statement = null;
		ResultSet rs = null;

			try {
				statement = conn.prepareStatement(GET_ALL_BOOKS);
				rs = statement.executeQuery();
				while (rs.next()) {
					books.add(mapRow(rs));
				}
			} catch (SQLException se) {
				System.out.println("SQL Error: " + se);
			}

		JdbcBookDao.closeConnection(conn);

		return books;
	}

	private Book mapRow(ResultSet rs) throws SQLException {
		if(rs == null) return null;
		Book book = new Book();

		book.setId(rs.getInt("id_book"));
		book.setAuthor(rs.getString("author"));
		book.setTitle(rs.getString("title"));
		book.setEdition(rs.getString("edition"));
		book.setYear(rs.getInt("year"));

		return book;
	}

}
