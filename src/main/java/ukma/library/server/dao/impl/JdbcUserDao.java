package ukma.library.server.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ukma.library.server.dao.UserDao;
import ukma.library.server.entity.Book;
import ukma.library.server.entity.User;
import ukma.library.server.service.LibraryService;

public class JdbcUserDao implements UserDao {

	public List<User> getAllUsers(){
		
		ArrayList<User> users = new ArrayList<User>();
		
		try (Connection conn = DriverManager.getConnection(LibraryService.MYSQL_URL)){
							PreparedStatement statement = null;
							ResultSet rs = null;

							try {
									statement = conn.prepareStatement("SELECT * from acsm_b775c39c99325aa.User INNER JOIN acsm_b775c39c99325aa.User_Role on User.user_role=User_Role.id_role");
									rs = statement.executeQuery();
									while (rs.next()) {
										users.add(new User(rs.getInt("id_user"),rs.getString("name"),rs.getString("phone"),rs.getString("password"),rs.getString("role")));
									}
							} catch (SQLException se) {
									System.out.println("SQL Error: " + se);
							}
							}catch (SQLException se) {
								System.out.println("Connection failed: " + se);
							}
		
		return users;
	}
	
}
