package ukma.library.server.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ukma.library.server.dao.BookDao;
import ukma.library.server.entity.Book;
import ukma.library.server.service.LibraryService;

public class JdbcBookDao implements BookDao {

	@Override
	public Book myTest() {
		
		Book book = new Book();
		
		try (Connection conn = DriverManager.getConnection(LibraryService.MYSQL_URL)){
			
					PreparedStatement statement = null;
					ResultSet rs = null;
					
					try {
						statement = conn.prepareStatement("SELECT * from acsm_b775c39c99325aa.Book");
						rs = statement.executeQuery();
						book.ok();	
					} catch (SQLException se) {
						System.out.println("SQL Error: " + se);
					}
				}catch (SQLException se) {
					System.out.println("Connection failed: " + se);
				}
		
		return book;
		
	}

}
