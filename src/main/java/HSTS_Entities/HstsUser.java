package HSTS_Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class HstsUser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	String userId;
	String userPassword;
	int userType; // 1.Student 2.Teacher 3.Principal
	
	public HstsUser(String userId, String userPassword, int userType) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userType = userType;
	}
	
	public HstsUser(String userId, String userPassword) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userType = 0;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
	
}