/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Apr 14, 2021
 */
package dmacc.beans;

public class Score {
	
	//Instance variable declaration.
	private double bmi;
	private double score;
	private String category;
	private String weightMessage;
	private String bpMessage;
	
	/**
	 * Default constructor.
	 */
	public Score () {
		super();
	}

	/**
	 * Primary constructor.
	 * @param bmi
	 * @param score
	 * @param category
	 * @param weightMessage
	 * @param bpMessage
	 */
	public Score(double bmi, double score, String category, String weightMessage, String bpMessage) {
		super();
		this.bmi = bmi;
		this.score = score;
		this.category = category;
		this.weightMessage = weightMessage;
		this.bpMessage = bpMessage;
	}
	
	/**
	 * Get method for bmi instance.
	 * @return: double.
	 */
	public double getBmi() {
		return bmi;
	}
	
	/**
	 * Set method for bmi instance.
	 * @param bmi: Required double.
	 */
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	
	/**
	 * Get method for score instance.
	 * @return: double.
	 */
	public double getScore() {
		return score;
	}
	
	/**
	 * Set method for score instance.
	 * @param score: Required double.
	 */
	public void setScore(double score) {
		this.score = score;
	}
	
	/**
	 * Get method for category instance.
	 * @return: String.
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Set method for category instance.
	 * @param category: Required String.
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * Get method for weightMessage instance.
	 * @return: String.
	 */
	public String getWeightMessage() {
		return weightMessage;
	}
	
	/**
	 * Set method for weightMessage instance.
	 * @param weightMessage: Required string.
	 */
	public void setWeightMessage(String weightMessage) {
		this.weightMessage = weightMessage;
	}
	
	/**
	 * Get method for BpMessage instance.
	 * @return: String.
	 */
	public String getBpMessage() {
		return bpMessage;
	}

	/**
	 * Set method for BpMessage instance.
	 * @param bpMessage: Required string.
	 */
	public void setBpMessage(String bpMessage) {
		this.bpMessage = bpMessage;
	}
	
	/**
	 * Default toString() method.
	 */
	@Override
	public String toString() {
		return "Score [bmi=" + bmi + ", score=" + score + ", category=" + category + ", weightMessage=" + weightMessage
				+ ", bpMessage=" + bpMessage + "]";
	}
	
}
