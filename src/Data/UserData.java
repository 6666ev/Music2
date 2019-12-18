package Data;

public class UserData {
	String UserName;
	String Email;
	String Password;
	public UserData(String username,String email,String password)
	{
		UserName= username;
		Email = email;
		Password = password;
	}
	public UserData()
	{
		
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
	
	
	
	
}
