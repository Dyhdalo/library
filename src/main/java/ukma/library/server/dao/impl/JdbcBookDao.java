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

	private final java.lang.String GET_BOOK_WITH_TITLE = "SELECT * FROM acsm_b775c39c99325aa.book WHERE title = ?";

	private final String GET_ALL_BOOKS = "SELECT * FROM acsm_b775c39c99325aa.book";

	private final String ADD_NEW_BOOK = "INSERT INTO acsm_b775c39c99325aa.book (author, title, edition ,year) VALUES (?,?,?,?)";

	private final String GET_BOOK_WITH_ID = "SELECT * FROM acsm_b775c39c99325aa.book WHERE id_book = ?";

	private final String UPDATE_BOOK = "UPDATE acsm_b775c39c99325aa.book SET book.title = ?, book.author = ?, book.edition = ?, book.year = ? WHERE  book.id_book = ?";

	private static final String Order_TABLE_NAME = " acsm_b775c39c99325aa.Order ";
	private static final String COPY_TABLE_NAME = " acsm_b775c39c99325aa.instance ";
	private static final String BOOK_TABLE_NAME = " acsm_b775c39c99325aa.book ";
	
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


	//TODO: add impl for keyWords
	public boolean addBook(String title, String author, int date, String edition, String keyWords) {
		boolean flag = true;

		Connection conn = createConnection();
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement(ADD_NEW_BOOK);
			statement.setString(1,author);
			statement.setString(2,title);
			statement.setString(3,edition);
			statement.setInt(4, date);
			statement.executeUpdate();
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}

		closeConnection(conn);
			return flag;
	}

	public void updateBook(int id, String title, String author, String edition, int year ) {
		Connection conn = createConnection();
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement(UPDATE_BOOK);
			statement.setString(1,title);
			statement.setString(2,author);
			statement.setString(3,edition);
			statement.setInt(4,year);
			statement.setInt(5,id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection(conn);
	}

	public Book getBook(int id) {
		String sql = "SELECT * FROM acsm_b775c39c99325aa.book WHERE id_book = "+id;
		Connection conn = JdbcBookDao.createConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		ArrayList<Book> books = new ArrayList<Book>();

		try {
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			while (rs.next()) {
				books.add(mapRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JdbcBookDao.closeConnection(conn);

		return books.get(0);
	}

	public Book getBook(String title) {
		Connection conn = createConnection();
		PreparedStatement statement = null;
		Book book = null;

		try {
			statement = conn.prepareStatement(GET_BOOK_WITH_TITLE);
			statement.setString(1, title);
			book = mapRow(statement.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}
	
	public List<Book> getActiveBooksByUser(int userId){
		String sql = "SELECT * FROM "+Order_TABLE_NAME+"INNER JOIN"+COPY_TABLE_NAME+"on Order.id_instance=Instance.id_inst INNER JOIN"+BOOK_TABLE_NAME+"on Book.id_book=Instance.id_book WHERE Order.id_user = "+userId;
		
		ArrayList<Book> books = new ArrayList<Book>();
		Connection conn = JdbcBookDao.createConnection();

		PreparedStatement statement = null;
		ResultSet rs = null;

			try {
				statement = conn.prepareStatement(sql);
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
