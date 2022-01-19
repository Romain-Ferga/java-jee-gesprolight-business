package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAdminDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.entity.GpAdmin;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;

public class GpAdminDAOTest {
	
	private IGpEmployeeDAO<GpAdmin> admDAO = new GpAdminDAOImpl();
	private Integer admIdForAllTest = null;
	private Integer createAdmId = null;

	@Test
	public void testCreateAdminWithSuccess() {
		
		// Given
		GpAdmin adm = new GpAdmin();
		
		Assert.assertNull(adm.getId());
		
		adm.setFileNumber("18024");
		adm.setLastname("PELTIER");
		adm.setFirstname("Guillaume");
		adm.setPhoneNumber("0265863289");
		adm.setPassword("myPassword");
		adm.setEmail("guillaume.peltierAcc@gouv.fr");
		adm.setLogin("guillaume.peltierAcc");

		// When
		adm = admDAO.create(adm);
		
		//On le sauvegarde pour le supprimer apres
		this.createAdmId = adm.getId();
		
		// Then
		Assert.assertNotNull(adm.getId());
		
	}

	@Test
	public void testFindAllAdminWithSuccess() {
		
		// Given

		// When
		List<GpAdmin> adms = this.admDAO.findAll();
		
		// Then
		Assert.assertTrue(adms.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer admId = this.admIdForAllTest;

		// When
		GpEmployee adm = this.admDAO.findById(admId);
		
		// Then
		Assert.assertNotNull(adm);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer admId = this.admIdForAllTest;
		
		// When
		this.admDAO.deleteById(admId);
		GpEmployee adm = this.admDAO.findById(admId);
		
		// Then
		Assert.assertNull(adm);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		
		GpAdmin adm = new GpAdmin();
		
		Assert.assertNull(adm.getId());
		
		adm.setFileNumber("2001");
		adm.setLastname("Laurent");
		adm.setFirstname("FABIUS");
		adm.setPhoneNumber("0125698745");
		adm.setPassword("myThirdPassword");
		adm.setEmail("laurent.fabius@gouv.fr");
		adm.setLogin("laurent.fabius");
		
		adm = admDAO.create(adm) ;
		
		this.admIdForAllTest = adm.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.admDAO.deleteById(this.admIdForAllTest);
		
		if(!Objects.isNull(this.createAdmId)) {
			
			this.admDAO.deleteById(this.createAdmId);
			
		}
		
	}

}