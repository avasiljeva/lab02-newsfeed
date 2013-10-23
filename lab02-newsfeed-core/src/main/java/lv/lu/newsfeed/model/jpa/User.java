package lv.lu.newsfeed.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@NamedQueries({
	
    @NamedQuery(name = JPQConst.UserJpq.QUERY_GET_USER_BY_USERNAME, 
		query = "SELECT u FROM User u WHERE u.username = :username"),
		
	@NamedQuery(name = JPQConst.UserJpq.QUERY_GET_ID_BY_USERNAME, 
        query = "SELECT u.id FROM User u WHERE u.username = :username"),
        
    // get all users except one with specified username
    @NamedQuery(name = JPQConst.UserJpq.QUERY_GET_ALL_EXCLUDE_USERNAME, 
    	query = "SELECT u FROM User u WHERE u.username <> :username")
})

@Entity
public class User implements PersistentEntity, Comparable<User> {
	
	/**
	 * Persistent fields
	 */
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(name="google_id")
	private String googleId;
	
	/*
	 * Username to log into *our* web application (not Google Plus)
	 */
	private String username;
	
	/*
     * Password to log *our* web application (not Google Plus)
     */
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;

	/**
	 * Getters and setters
	 */
	
	public Long getId() {
		return id;
	}	
	
	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
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
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public String getFullName(){
    	return firstName + " " + lastName;
    }
    
    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }
}
