/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Apr 13, 2021
 */
package dmacc.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="uservitals")
public class Vitals {
	
	// Instance variable declaration.
	// Id instance.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userVitalId")
	private long userVitalId;
	// Other instance variables.
	private long userId;
	private String age;
	private String weight;
	private String height;
	private String bloodPressureSystolic;
	private String bloodPressureDiastolic;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateTime;
	
	/**
	 * Default constructor.
	 */
	public Vitals()
	{
		super();
	}

	/**
	 * Primary constructor.
	 * @param userVitalId: Required long.
	 * @param userId: Required long.
	 * @param age: Required String.
	 * @param weight: Required String.
	 * @param height: Required String.
	 * @param bloodPressureSystolic: Required String.
	 * @param bloodPressureDiastolic: Required String.
	 * @param dateTime: Required String.
	 */
	public Vitals(long userVitalId, long userId, String age, String weight, String height, String bloodPressureSystolic, String bloodPressureDiaStolic, LocalDate dateTime) {
		super();
		this.userVitalId = userVitalId;
		this.userId = userId;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.bloodPressureSystolic = bloodPressureSystolic;
		this.bloodPressureSystolic = bloodPressureDiaStolic;
		this.dateTime = dateTime;
	}

	/**
	 * Override constructor.
	 * @param userVitalId: Required long.
	 * @param age: Required String.
	 * @param weight: Required String.
	 * @param height: Required String.
	 * @param bloodPressureSystolic: Required String.
	 * @param bloodPressureDiastolic: Required String.
	 * @param dateTime: Required LocalDate.
	 */
	public Vitals(long userId, String age, String weight, String height, String bloodPressureSystolic, String bloodPressureDiastolic, LocalDate dateTime) {
		super();
		this.userId = userId;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.bloodPressureSystolic = bloodPressureSystolic;
		this.bloodPressureDiastolic = bloodPressureDiastolic;
		this.dateTime = dateTime;
	}
	
	/**
	 * Get method for userVitalId instance.
	 * @return: long.
	 */
	public long getUserVitalId() {
		return userVitalId;
	}
	
	/**
	 * Set method for userVitalID instance.
	 * @param userVitalId: Required long.
	 */
	public void setUserVitalId(long userVitalId) {
		this.userVitalId = userVitalId;
	}

	/**
	 * Get method for userId instance.
	 * @return: long.
	 */
	public long getUserId() {
		return userId;
	}
	
	/**
	 * Set method for userId instance.
	 * @param userId: Required long.
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	/**
	 * Get method for age instance.
	 * @return: String.
	 */
	public String getAge() {
		return age;
	}
	
	/**
	 * Set method for age instance.
	 * @param age: Required String.
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
	/**
	 * Get method for weight instance.
	 * @return: String.
	 */
	public String getWeight() {
		return weight;
	}
	
	/**
	 * Set method for weight instance.
	 * @param weight: Required String.
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	/** 
	 * Get method for height instance.
	 * @return: String.
	 */
	public String getHeight() {
		return height;
	}
	
	/**
	 * Set method for height instance.
	 * @param height: Required String.
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	
	/**
	 * Get method for bloodPressureSystolic instance.
	 * @return: String.
	 */
	public String getBloodPressureSystolic() {
		return bloodPressureSystolic;
	}
	
	/**
	 * Set method for bloodPressureSystolic instance.
	 * @param bloodPressureSystolic: Required string.
	 */
	public void setBloodPressureSystolic(String bloodPressureSystolic) {
		this.bloodPressureSystolic = bloodPressureSystolic;
	}
	
	/**
	 * Get method for bloodPressurediastolic instance.
	 * @return: String.
	 */
	public String getBloodPressureDiastolic() {
		return bloodPressureDiastolic;
	}
	
	/**
	 * Set method for bloodPressureDiastolic instance.
	 * @param bloodPressureDiastolic: Required string.
	 */
	public void setBloodPressureDiastolic(String bloodPressureDiastolic) {
		this.bloodPressureDiastolic = bloodPressureDiastolic;
	}
	
	/**
	 * Get method for dateTime instance.
	 * @return: String.
	 */
	public LocalDate getDateTime() {
		return dateTime;
	}
	
	/**
	 * Set method for dateTime instance.
	 * @param dateTime
	 */
	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}
	
	/**
	 * Default toString() method.
	 */
	@Override
	public String toString() {
		return "Vitals [userVitalId=" + userVitalId + ", userId=" + userId + ", age=" + age + ", weight=" + weight
				+ ", height=" + height + ", dateTime=" + dateTime + "]";
	}

}
