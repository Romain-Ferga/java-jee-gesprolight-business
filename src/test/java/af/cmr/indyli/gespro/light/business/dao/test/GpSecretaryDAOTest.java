package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpSecretaryDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpSecretary;

public class GpSecretaryDAOTest {
	
	private IGpEmployeeDAO<GpSecretary> secrDAO = new GpSecretaryDAOImpl();
	private Integer secrIdForAllTest = null;
	private Integer createSecrId = null;

	@Test
	public void testCreateAccountantWithSuccess() {
		
		// Given
		GpSecretary secr = new GpSecretary();
		
		Assert.assertNull(secr.getId());
		
		secr.setFileNumber("18024");
		secr.setLastname("PELTIER");
		secr.setFirstname("Guillaume");
		secr.setPhoneNumber("0265863289");
		secr.setPassword("myPassword");
		secr.setEmail("guillaume.peltierAcc@gouv.fr");
		secr.setLogin("guillaume.peltierAcc");

		// When
		secr = secrDAO.create(secr);
		
		//On le sauvegarde pour le supprimer apres
		this.createSecrId = secr.getId();
		
		// Then
		Assert.assertNotNull(secr.getId());
		
	}

	@Test
	public void testFindAllAccountantWithSuccess() {
		
		// Given

		// When
		List<GpSecretary> secrs = this.secrDAO.findAll();
		
		// Then
		Assert.assertTrue(secrs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer secrId = this.secrIdForAllTest;

		// When
		GpEmployee secr = this.secrDAO.findById(secrId);
		
		// Then
		Assert.assertNotNull(secr);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer secrId = this.secrIdForAllTest;
		
		// When
		this.secrDAO.deleteById(secrId);
		GpEmployee secr = this.secrDAO.findById(secrId);
		
		// Then
		Assert.assertNull(secr);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		
		GpSecretary secr = new GpSecretary();
		
		Assert.assertNull(secr.getId());
		
		secr.setFileNumber("2001");
		secr.setLastname("Laurent");
		secr.setFirstname("FABIUS");
		secr.setPhoneNumber("0125698745");
		secr.setPassword("myThirdPassword");
		secr.setEmail("laurent.fabius@gouv.fr");
		secr.setLogin("laurent.fabius");
		
		secr = secrDAO.create(secr) ;
		
		this.secrIdForAllTest = secr.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.secrDAO.deleteById(this.secrIdForAllTest);
		
		if(!Objects.isNull(this.createSecrId)) {
			
			this.secrDAO.deleteById(this.createSecrId);
			
		}
		
	}

}
