/**
 * @author Devin Baack - dfbaack11
 * @author Daniel De Lima - dndanli
 * @author Tyler Maschoff - TyMash55
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Apr 10, 2021
 */
package dmacc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dmacc.controller.BeanConfiguration;

@SpringBootApplication
public class HealthPromotionApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HealthPromotionApplication.class, args);
	}
	
	ApplicationContext appContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
}
