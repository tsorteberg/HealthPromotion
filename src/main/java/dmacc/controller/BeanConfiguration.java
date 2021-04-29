/**
 * @author Devin Baack - dfbaack11
 * @author Daniel De Lima - dndanli
 * @author Tyler Maschoff - TyMash55
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Apr 10, 2021
 */
package dmacc.controller;

import java.time.LocalDate;

import org.springframework.context.annotation.*;

import dmacc.beans.Vitals;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public Vitals vitals() {
		Vitals bean = new Vitals(123, "42", "200", "72", "100", "70", LocalDate.now());
		return bean;
	}

}
