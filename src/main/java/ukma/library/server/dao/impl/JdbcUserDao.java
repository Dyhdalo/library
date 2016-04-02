package ukma.library.server.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import ukma.library.server.dao.UserDao;
import ukma.library.server.entity.User;
import ukma.library.server.service.LibraryService;

public class JdbcUserDao implements UserDao {

    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement statement;

    private static final String USER_TABLE_NAME = " acsm_b775c39c99325aa.User ";
    private static final String USER_ROLE_TABLE_NAME = " acsm_b775c39c99325aa.User_Role ";

    public JdbcUserDao() {
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(LibraryService.MYSQL_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM" + USER_TABLE_NAME + "INNER JOIN" + USER_ROLE_TABLE_NAME
                + "on User.user_role=User_Role.id_role";
        return selectUserList(sql);
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM" + USER_TABLE_NAME + "INNER JOIN" + USER_ROLE_TABLE_NAME
                + "on User.user_role=User_Role.id_role WHERE User.id_user = " + id;
        List<User> users = selectUserList(sql);
        return users == null ? null : users.get(0);
    }

    public List<User> getUserByRole(int roleId) {
        String sql = "SELECT * FROM" + USER_TABLE_NAME + "INNER JOIN" + USER_ROLE_TABLE_NAME
                + "on User.user_role=User_Role.id_role WHERE User_Role.id_role = " + roleId;
        return selectUserList(sql);
    }

    @Override
    public User getUserByName(String name) {
        String sql = "SELECT * FROM" + USER_TABLE_NAME + "INNER JOIN" + USER_ROLE_TABLE_NAME
                + "on User.user_role=User_Role.id_role WHERE User.name = " + name;
        List<User> users = selectUserList(sql);
        return users == null ? null : users.get(0);
    }

    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO " + USER_TABLE_NAME + "(name, phone, password, role) " +
                "VALUES (?, ?, ?, ?)";
        try {
            connection = createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRoleId());
            statement .executeUpdate();
        } catch (SQLException e) {
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    private List<User> selectUserList(String sql) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            connection = createConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int row = 0;
            while (resultSet.next()) {
                users.add(userMapper.mapRow(resultSet, row++));
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e);
        } finally {
            closeConnection();
        }
        return users;
    }

    private static final RowMapper<User> userMapper = new RowMapper<User>() {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id_user"));
            user.setName(resultSet.getString("name"));
            user.setPhone(resultSet.getString("phone"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            return user;
        }
    };


}
