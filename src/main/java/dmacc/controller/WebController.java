/**
 * @author Devin Baack - dfbaack11
 * @author Daniel De Lima - dndanli
 * @author Tyler Maschoff - TyMash55
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Apr 10, 2021
 */
package dmacc.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.Score;
import dmacc.beans.User;
import dmacc.beans.Vitals;
import dmacc.repository.UserRepository;
import dmacc.repository.VitalsRepository;

@Controller
public class WebController {
	@Autowired
	VitalsRepository vitalsRepo;
	
	@Autowired
	UserRepository userRepo;
	
	/**
	 * Method to trim spaces of form entries
	 * @param dataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor strTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class,strTrimmerEditor);
	}
	
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
	
	@GetMapping({"/viewAllVitals"})
	public String viewAllVitals(Model model) {
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
    
    @GetMapping("/userSignIn")
	public String userSignIn(@PathVariable("userId") long userId, Model model) {
		User u = userRepo.findById(userId).orElse(null);
		model.addAttribute(u);
		return "userHome";
	}
    
    // ----------------------------
 	// --- Registration methods ---
 	// ----------------------------
 	@GetMapping("/registration")
 	public String register(Model model) {
 		User user = new User();
 		model.addAttribute("newUser", user);
 		return "userRegistration";
 		
 	}
 	
 	@PostMapping("/registration")
 	public String addUser(@Valid @ModelAttribute("newUser") User user,BindingResult bindingResult, Model model ) {
 		
 		if(bindingResult.hasErrors()) {
 			return "userRegistration";
 		}
 		try {
 			userRepo.save(user); 
 		}catch(Exception e) {
 			if(e.getCause()!= null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
 				return "duplicateEntries";
 			}
 		}
 		return "sucessfulRegistration";	
 	}
 	
 	/**
	 * Example controllers that works with current project structure.
	 * Simply pass user id from database into url.
	@GetMapping({"/viewAllVitals/{id}"})
	public String viewAllVitals(@PathVariable("id") long id, Model model) {
		User u = userRepo.findById(id).orElse(null);
		model.addAttribute("user", u);
		return "userHome";
	}
	
	@GetMapping("/addVitals/{id}")
	public String addVitals(@ModelAttribute("user") User u, @PathVariable("id") long id, Model model) {
		User user = userRepo.findById(id).orElse(null);
		Vitals v = new Vitals();
		
		// Ensure user id is set on Vitals object.
		if (u.getUserId() == 0) {
			v.setUserId(user.getUserId());
		}
		else if (user.getUserId() == 0) {
			v.setUserId(u.getUserId());
		}
		model.addAttribute("newVital", v);
		return "addVitals";
	}
	
	@PostMapping("/postAddVital/{id}")
    public String postAddVitals(@ModelAttribute("newVital") Vitals vital, @PathVariable("id") Long id, Model model) {
    	User user = userRepo.findById(id).orElse(null);
    	user.getVitals().add(vital);
        userRepo.save(user);
        //return viewAllVitals(user.getUserId(), model);
        return "redirect:../viewAllVitals" + "/" + id;
    }
    
    @GetMapping("/delete/{uid}/{vid}")
    public String deleteVitals(@PathVariable("uid") long uid, @PathVariable("vid") long vid, Model model) {
        vitalsRepo.findById(vid).ifPresent(vitalsRepo :: delete);
        //return viewAllVitals(uid, model);
        return "redirect:../../viewAllVitals" + "/" + uid;
    }
    */
	
}
