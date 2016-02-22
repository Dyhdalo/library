package ukma.library.server.dao;

import java.util.List;

import ukma.library.server.entity.User;

public interface UserDao {

	public List<User> getAllUsers();
}
