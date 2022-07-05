package tony.bank.app.model;

import java.util.Objects;

import tony.bank.data.structures.ArrayList;
import tony.bank.data.structures.List;

public class User {
	// User/Customer ID
	private int id;
	// Username and Password
	private String username, password, name, phone, email;
	List<Account> accounts = new ArrayList<>();
	private boolean isLoggedIn;

	public User() {
	}

	public User(String username, String password) {
		super();
		this.id = 0;
		this.username = username;
		this.password = password;
	}


	public User(int id, String username, String password, String name, String phone, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return id == other.id && Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

}
