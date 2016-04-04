package ukma.library.server.dao;

import java.util.List;

import ukma.library.server.entity.User;

public interface UserDao {

	public List<User> getAllUsers();

	public User getUserById(int id);

	public List<User> getUserByRole(int roleId);

	public boolean addUser(User user);

	public User getUserByName(String name);

	public List<User> getAllUsersDebtors();
}
