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
	private String healthStatus;
	
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
	public Score(double bmi, double score, String category, String weightMessage, String bpMessage, String healthStatus) {
		super();
		this.bmi = bmi;
		this.score = score;
		this.category = category;
		this.weightMessage = weightMessage;
		this.bpMessage = bpMessage;
		this.healthStatus = healthStatus;
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
	 * Get method for the healthStatus instance.
	 * @return: String.
	 */
	public String getHealthStatus() {
		return healthStatus;
	}
	
	/**
	 * Set method for the healthStatus instance.
	 * @param healthStatus: Required string.
	 */
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	
	/**
	 * Helper method to calculate and return BMI.
	 * @param weight: Required string.
	 * @param height: Required string.
	 * @return: Double.
	 */
	public void calculateBMI(String weight, String height) {
		
		// Attempt to parse record data to type double to local variable.
		// To Do: Try catch statement implementation.
		double w = Double.parseDouble(weight);
		double h = Double.parseDouble(height);
		
		// Calculate BMI
		this.bmi = (w / h / h) * 703;
	}
	
	/**
	 * Helper method that calls calculateBMI method and assigns weight messages.
	 * @param weight: Required string.
	 * @param height: Required string.
	 */
	public void renderWeightInfo(String weight, String height) {
		
		// Method call to calculate BMI.
		calculateBMI(weight, height);
		
		// Recommendations by weight constant declaration and initialization.
		final String weightMessage1 = "Diet: Increase caloric intake by 500 calories per day. "
				+ "Exercise: Maintain current exercise routine.";
		final String weightMessage2 = "Diet: Maintain caloric intake.  "
				+ "Exercise: Work your way up to 150 minutes of moderate-intensity aerobic activity,"
				+ " 75 minutes of vigorous-intensity aerobic activity,"
				+ " or an equivalent mix of the two each week.";
		final String weightMessage3 = "Diet: Reduce caloric intake by 500 calories per day.  "
				+ "Exercise: 150 minutes of moderate-intensity aerobic activity,"
				+ " 75 minutes of vigorous-intensity aerobic activity,"
				+ " or an equivalent mix of the two each week.";
		
		// Selection logic to determine and assign BMI category, weight message, and score.
		if (this.bmi < 18.5) {
			this.category = "Underweight";
			this.score += 2;
			this.weightMessage = weightMessage1;
		} 
		else if (this.bmi < 25) {
			this.category = "Normal";
			this.score += 3;
			this.weightMessage = weightMessage2;
		}
		else if (this.bmi < 30) {
			this.category = "Overweight";
			this.score += 2;
			this.weightMessage = weightMessage3;
		}
		else if (this.bmi >= 30) {
			this.category = "Obese";
			this.score += 1;
			this.weightMessage = weightMessage3;
		}
	}
	
	/**
	 * 
	 * Helper method that assigns blood pressure messages.
	 * @param bpSys: Required string.
	 * @param bpDia: Required string.
	 */
	public void renderBPInfo(String bpSys, String bpDia) {
		
		// Contact a physician warnings by blood pressure.
		final String bpMessage1 = "Normal.";
		final String bpMessage2 = "Elevated. You should consider contacting a physician.";
		final String bpMessage3 = "Hypertension Stage 1: You should contact a physician.";
		final String bpMessage4 = "Hypertension Stage 2: You should contact a physician as soon as possible.";
		final String bpMessage5 = "Hypertension State 3: You should contact a physician immediately.";
		
		// Attempt to parse record data to type double to local variable.
		// To Do: Try catch statement implementation.
		double Sys = Double.parseDouble(bpSys);
		double Dia = Double.parseDouble(bpDia);
		
		// Selection logic to determine and assign blood pressure category, score, message.
		if (Sys < 120 && Dia < 80) {
			this.bpMessage = bpMessage1;
			this.score += 5;
		}
		else if (Sys < 130 && Dia < 80 ) {
			this.bpMessage = bpMessage2;
			this.score += 4;
		}
		else if (Sys < 140 || Dia < 90) {
			this.bpMessage = bpMessage3;
			this.score += 3;
		}
		else if (Sys < 180 || Dia < 120) {
			this.bpMessage = bpMessage4;
			this.score += 2;
		}
		else if (Sys >= 180 || Dia >= 120 ) {
			this.bpMessage = bpMessage5;
			this.score += 1;
		}
	}
	
	/**
	 * Helper method that calculates the final score.
	 */
	public void renderFinalScore () {
		double finalScore = (score / 8) * 100;
		this.score = finalScore;
	}
	
	public void renderHealthStatus () {
		// Selection logic to determine health status.
		if (this.score <= 33) {
			this.healthStatus = "Poor";
		}
		else if (this.score <= 66) {
			this.healthStatus = "At Risk";
		}
		else if (this.score <= 100) {
			this.healthStatus = "Good";
		}
	}
	
	@Override
	public String toString() {
		return "Score [bmi=" + bmi + ", score=" + score + ", category=" + category + ", weightMessage=" + weightMessage
				+ ", bpMessage=" + bpMessage + ", healthStatus=" + healthStatus + "]";
	}
	
}
