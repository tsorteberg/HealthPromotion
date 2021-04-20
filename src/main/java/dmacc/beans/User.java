/**
 * @author Daniel - dcdelima
 * CIS 175 - Spring 2021
 * Apr 15, 2021
 */
package dmacc.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
@Table(
		name ="user",
		uniqueConstraints= {
				@UniqueConstraint(name = "user_email_unique", columnNames = "email"),
				@UniqueConstraint(name = "user_username_unique", columnNames = "userName")
			}
	  )
 
public class User {
	
	/**
	 * To do: Revise relationship with Vitals.java
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private long userId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "userId")
    private List<Vitals> vitals;

	/**
	 * @param name required string full name of user
	 * @param email required string email address of user 
	 * @param userName required string user name of user
	 * @param password required string user password
	 */
	public User(String name, String email, String userName, String password) {
		super();
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}
	
	
	

}
