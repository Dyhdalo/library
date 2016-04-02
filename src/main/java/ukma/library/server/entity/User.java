package ukma.library.server.entity;

import java.io.Serializable;

public class User implements Serializable{

	private int id;

	private String name;

	private String phone;

	private String password;

	private String role;

	private enum UserRole{
		USER ("Читач"),
		LIBRARIAN("Бібліотекар");

		private String name;

		UserRole(String name) {
			this.name = name;
		}
		public int getRoleId(){
			if (this.equals(USER)) return 2;
			return 1;
		}
	}

	public User() {
	}

	public User(int id, String name, String phone, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", role="
				+ role + "]";
	}

	public int getRoleId() {
		return UserRole.valueOf(role).getRoleId();
	}
}
