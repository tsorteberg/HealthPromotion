/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Apr 14, 2021
 */
package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.beans.Vitals;

@Repository
public interface VitalsRepository extends JpaRepository<Vitals, Long> {}
