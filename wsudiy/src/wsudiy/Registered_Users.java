package wsudiy;

public class Registered_Users {
	
	protected String first_name;
	protected String last_name;
	protected String birthday;
	protected String gender;
	protected String username;
	protected String password;
	
	public Registered_Users() {
		
	}
	
	public Registered_Users(String username) {
		this.username = username;
	}
	
	public Registered_Users(String first_name, String last_name, 
			String birthday, String gender, String password) {
		
	}
	
	public Registered_Users(String first_name, String last_name, 
			String birthday, String gender, String username, String password) {
		this(first_name, last_name, birthday, gender, password);
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.birthday = birthday;
		this.gender = gender;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstName() {
		return first_name;
	}
	
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLastName() {
		return last_name;
	}
	
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
