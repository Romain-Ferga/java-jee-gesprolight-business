package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAccountantDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;

public class GpAccountantDAOTest {
	
	private IGpEmployeeDAO<GpAccountant> accDAO = new GpAccountantDAOImpl();
	private Integer accIdForAllTest = null;
	private Integer createAccId = null;

	@Test
	public void testCreateAccountantWithSuccess() {
		
		// Given
		GpAccountant acc = new GpAccountant();
		
		Assert.assertNull(acc.getId());
		
		acc.setFileNumber("18024");
		acc.setLastname("PELTIER");
		acc.setFirstname("Guillaume");
		acc.setPhoneNumber("0265863289");
		acc.setPassword("myPassword");
		acc.setEmail("guillaume.peltierAcc@gouv.fr");
		acc.setLogin("guillaume.peltierAcc");

		// When
		acc = accDAO.create(acc);
		
		//On le sauvegarde pour le supprimer apres
		this.createAccId = acc.getId();
		
		// Then
		Assert.assertNotNull(acc.getId());
		
	}

	@Test
	public void testFindAllAccountantWithSuccess() {
		
		// Given

		// When
		List<GpAccountant> accs = this.accDAO.findAll();
		
		// Then
		Assert.assertTrue(accs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer accId = this.accIdForAllTest;

		// When
		GpEmployee acc = this.accDAO.findById(accId);
		
		// Then
		Assert.assertNotNull(acc);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer accId = this.accIdForAllTest;
		
		// When
		this.accDAO.deleteById(accId);
		GpEmployee acc = this.accDAO.findById(accId);
		
		// Then
		Assert.assertNull(acc);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		
		GpAccountant acc = new GpAccountant();
		
		Assert.assertNull(acc.getId());
		
		acc.setFileNumber("2001");
		acc.setLastname("Laurent");
		acc.setFirstname("FABIUS");
		acc.setPhoneNumber("0125698745");
		acc.setPassword("myThirdPassword");
		acc.setEmail("laurent.fabius@gouv.fr");
		acc.setLogin("laurent.fabius");
		
		acc = accDAO.create(acc) ;
		
		this.accIdForAllTest = acc.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.accDAO.deleteById(this.accIdForAllTest);
		
		if(!Objects.isNull(this.createAccId)) {
			
			this.accDAO.deleteById(this.createAccId);
			
		}
		
	}

}
