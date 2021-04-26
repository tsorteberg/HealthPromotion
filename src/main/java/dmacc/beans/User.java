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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private long userId;
	
	
	@Column(name = "name",nullable = false)
	@NotEmpty @NotBlank(message="Whitespaces are not allowed")
	@Size(min = 2, max = 25, message ="Full name has to be between 2 to 25 characters")
	private String name;
	
//	@Email(regexp ="/^[^ ]+@[^ ]+\\.[a-z]{2,3}$/")
	@Column(name = "email",nullable = false)
	@NotEmpty
	private String email;
	
	@NotEmpty @NotBlank(message="Whitespaces are not allowed")
	@Column(name = "userName",nullable = false)
	private String userName;
	
	@NotEmpty @NotBlank(message="Whitespaces are not allowed")
	@Column(name = "password",nullable = false)
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

	// make sure Lombok is imported @NoArgsConstructor already generates this piece of code
//	public User() {
//		// TODO Auto-generated constructor stub
//	}
	
	
	

}
