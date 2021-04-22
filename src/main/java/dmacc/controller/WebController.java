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
import org.springframework.web.bind.annotation.PathVariable;

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
		
		// Look up record in table by id and return Vital object type or null if not found.
		Vitals v = vitalsRepo.findById(id).orElse(null);
		
		// Selection logic to determine if the record is found.
		// If record is not found.
		if (v == null) {
			return userHome(model);
		}
		// If record is found.
		else {

			// Instantiate Score object.
			Score s = new Score();
			
			// Populate Score Object.
			s.renderWeightInfo(v.getWeight(), v.getHeight());
			s.renderBPInfo(v.getBloodPressureSystolic(), v.getBloodPressureDiastolic());
			s.renderFinalScore();
			s.renderHealthStatus();

			// Assign model attributes.
			model.addAttribute("vital", v);
			model.addAttribute("score", s);
			
			// Return statement.
			return "viewVitals";
		}
		
	}
	
	@GetMapping("/addVitals")
	public String addVitals(Model model) {
		Vitals v = new Vitals();
		model.addAttribute("field", v);
		return "userHome";
	}
	
	@GetMapping("/userHome")
	public String userHome(Model model) {
		return "userHome";
	}
}
