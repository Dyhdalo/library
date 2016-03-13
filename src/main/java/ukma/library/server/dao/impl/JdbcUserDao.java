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
        if (connection == null)
            try {
                connection = DriverManager.getConnection(LibraryService.MYSQL_URL);
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e);
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
    public boolean addUser(User user) {
        String sql = "INSERT INTO " + USER_TABLE_NAME + "(name, phone, password, role) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPhone());
        preparedStatement.setString(3, user.getPassword();
        preparedStatement.setInt(4, user.getRoleId());
// execute insert SQL stetement
        preparedStatement .executeUpdate();

        return users == null ? null : users.get(0);
    }

    private List<User> selectUserList(String sql) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int row = 0;
            while (resultSet.next()) {
                users.add(userMapper.mapRow(resultSet, row++));
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e);
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
