package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpTechnicianDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpTechnician;

public class GpTechnicianDAOTest {
	
	private IGpEmployeeDAO<GpTechnician> techDAO = new GpTechnicianDAOImpl();
	private Integer techIdForAllTest = null;
	private Integer createTechId = null;

	@Test
	public void testCreateTechnicianWithSuccess() {
		
		// Given
		GpTechnician tech = new GpTechnician();
		
		Assert.assertNull(tech.getId());
		
		tech.setFileNumber("18024");
		tech.setLastname("PELTIER");
		tech.setFirstname("Guillaume");
		tech.setPhoneNumber("0265863289");
		tech.setPassword("myPassword");
		tech.setEmail("guillaume.peltierAcc@gouv.fr");
		tech.setLogin("guillaume.peltierAcc");
		tech.setLastDiploma("Master");
		tech.setGraduationYear(2022);

		// When
		tech = techDAO.create(tech);
		
		//On le sauvegarde pour le supprimer apres
		this.createTechId = tech.getId();
		
		// Then
		Assert.assertNotNull(tech.getId());
		
	}

	@Test
	public void testFindAllTechnicianWithSuccess() {
		
		// Given

		// When
		List<GpTechnician> techs = this.techDAO.findAll();
		
		// Then
		Assert.assertTrue(techs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer techId = this.techIdForAllTest;

		// When
		GpEmployee tech = this.techDAO.findById(techId);
		
		// Then
		Assert.assertNotNull(tech);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer techId = this.techIdForAllTest;
		
		// When
		this.techDAO.deleteById(techId);
		GpEmployee tech = this.techDAO.findById(techId);
		
		// Then
		Assert.assertNull(tech);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		
		GpTechnician tech = new GpTechnician();
		
		Assert.assertNull(tech.getId());
		
		tech.setFileNumber("2001");
		tech.setLastname("Laurent");
		tech.setFirstname("FABIUS");
		tech.setPhoneNumber("0125698745");
		tech.setPassword("myThirdPassword");
		tech.setEmail("laurent.fabius@gouv.fr");
		tech.setLogin("laurent.fabius");
		tech.setLastDiploma("Master");
		tech.setGraduationYear(2022);
		
		tech = techDAO.create(tech) ;
		
		this.techIdForAllTest = tech.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.techDAO.deleteById(this.techIdForAllTest);
		
		if(!Objects.isNull(this.createTechId)) {
			
			this.techDAO.deleteById(this.createTechId);
			
		}
		
	}

}
