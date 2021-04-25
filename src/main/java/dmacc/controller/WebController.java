/**
 * @author Devin Baack - dfbaack11
 * @author Daniel De Lima - dndanli
 * @author Tyler Maschoff - TyMash55
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Apr 10, 2021
 */
package dmacc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import dmacc.beans.Score;
import dmacc.beans.Vitals;
import dmacc.repository.VitalsRepository;

@Controller
public class WebController {
	@Autowired
	VitalsRepository vitalsRepo;
	
	/**
	 * Action method that retrieves a record from the uservitals table by id and passes and updated model to viewVitals.html.
	 * @param id: Required long.
	 * @param model: Model object type.
	 * @return: If record is found, returns to viewVitals.html.  If record is not found, returns to user home method.
	 */
	@GetMapping("/viewVitals/{id}")
	public String viewVitals(@PathVariable("id") long id, Model model) {
		
		// Local variable declaration.
		double bmi;
		double score = 0;
		
		// Recommendations by weight variable declaration and initialization.
		String weightMessage1 = "Diet: Increase caloric intake by 500 calories per day. "
				+ "Exercise: Maintain current exercise routine.";
		String weightMessage2 = "Diet: Maintain caloric intake.  "
				+ "Exercise: Work your way up to 150 minutes of moderate-intensity aerobic activity,"
				+ " 75 minutes of vigorous-intensity aerobic activity,"
				+ " or an equivalent mix of the two each week.";
		String weightMessage3 = "Diet: Reduce caloric intake by 500 calories per day.  "
				+ "Exercise: 150 minutes of moderate-intensity aerobic activity,"
				+ " 75 minutes of vigorous-intensity aerobic activity,"
				+ " or an equivalent mix of the two each week.";
		
		// Contact a physician warnings by blood pressure.
		String bpMessage1 = "Normal.";
		String bpMessage2 = "Elevated. You should consider contacting a physician.";
		String bpMessage3 = "Hypertension Stage 1: You should contact a physician.";
		String bpMessage4 = "Hypertension Stage 2: You should contact a physician as soon as possible.";
		String bpMessage5 = "Hypertension State 3: You should contact a physician immediately.";
		
		// Look up record in table by id and return Vital object type or null if not found.
		Vitals v = vitalsRepo.findById(id).orElse(null);
		
		// Selection logic to determine if the record is found.
		// If record is not found.
		if (v == null) {
			return userHome(model);
		}
		// If record is found.
		else {
			// Attempt to parse record data to type double to local variable.
			// To Do: Try catch statement implementation.
			double weight = Double.parseDouble(v.getWeight());
			double height = Double.parseDouble(v.getHeight());
			double bpSys = Double.parseDouble(v.getBloodPressureSystolic());
			double bpDia = Double.parseDouble(v.getBloodPressureDiastolic());
			
			// Instantiate Score object.
			Score s = new Score();
			
			// Calculate BMI
			bmi = weight / ((height * height) * 703);
			
			// Assign BMI attribute to Score object
			s.setBmi(bmi);
			
			// Selection logic to determine and assign BMI category, score, message.
			if (bmi < 18.5) {
				s.setCategory("Underweight");
				score += 2;
				s.setWeightMessage(weightMessage1);
			} 
			else if (bmi < 25) {
				s.setCategory("Normal");
				score += 3;
				s.setWeightMessage(weightMessage2);
			}
			else if (bmi < 30) {
				s.setCategory("Overweight");
				score += 2;
				s.setWeightMessage(weightMessage3);
			}
			else if (bmi >= 30) {
				s.setCategory("Obese");
				score += 1;
				s.setWeightMessage(weightMessage3);
			}
			
			// Selection logic to determine and assign blood pressure category, score, message.
			if (bpSys < 120 && bpDia < 80) {
				s.setBpMessage(bpMessage1);
				score += 5;
			}
			else if (bpSys < 130 && bpDia < 80 ) {
				s.setBpMessage(bpMessage2);
				score += 4;
			}
			else if (bpSys < 140 || bpDia < 90) {
				s.setBpMessage(bpMessage3);
				score += 3;
			}
			else if (bpSys < 180 || bpDia < 120) {
				s.setBpMessage(bpMessage4);
				score += 2;
			}
			else if (bpSys >= 180 || bpDia >= 120 ) {
				s.setBpMessage(bpMessage5);
				score += 1;
			}
			
			// Calculate and assign score to Score object.
			s.setScore((score / 8) * 100);
			
			// Assign model attributes.
			model.addAttribute("vital", v);
			model.addAttribute("score", s);
			
			// Return statement.
			return "viewVitals";
		}
		
	}
	
	 @GetMapping({"/", "/viewAllVitals"})
	   public String viewAllVitals( Model model) {
	       if (vitalsRepo.findAll().isEmpty()) {
	           return addVitals(model);
	       }
	       model.addAttribute("vitals", vitalsRepo.findAll());
	       return "userHome";
	   }
	
	@GetMapping("/addVitals")
	public String addVitals(Model model) {
		Vitals v = new Vitals();
		model.addAttribute("newVital", v);
		return "addVitals";
	}
	
	
	@GetMapping("/userHome")
	public String userHome(Model model) {
		return "userHome";
	}
	@GetMapping("/edit/{id}")
    public String showUpdateVital( @PathVariable("id") long id, Model model) {
        Vitals v = vitalsRepo.findById(id).orElse(null);
        model.addAttribute("newVital", v);
        return "addVitals";
    }

    @PostMapping("/update/{id}")
    public String updateVitals(Vitals v, Model model) {
        vitalsRepo.save(v);
        return viewAllVitals(model);
    }

    @GetMapping("/delete/{id}")
    public String deleteVitals(@PathVariable("id") long id, Model model) {
        vitalsRepo.findById(id).ifPresent(vitalsRepo :: delete);
        return viewAllVitals(model);
    }
	
}
