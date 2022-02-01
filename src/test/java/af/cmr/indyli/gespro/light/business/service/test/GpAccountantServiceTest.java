package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.impl.GpAccountantServiceImpl;

public class GpAccountantServiceTest {
	
	private IGpEmployeeService<GpAccountant> accService = new GpAccountantServiceImpl();
	private Integer accIdForAllTest = null;
	private Integer createAccId = null;

	@Test
	public void testCreateAccountantWithSuccess() throws GesproBusinessException {
		
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
		acc = accService.create(acc);
		
		//On le sauvegarde pour le supprimer apres
		this.createAccId = acc.getId();
		
		// Then
		Assert.assertNotNull(acc.getId());
		
	}
	
	@Test
	public void testCreateAccountantWithException() throws GesproBusinessException {
		// Given


		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpAccountant acc = new GpAccountant();
			Assert.assertNull(acc.getId());
			acc.setFileNumber("2001");
			acc.setLastname("Laurent");
			acc.setFirstname("FABIUS");
			acc.setPhoneNumber("0125698745");
			acc.setPassword("myThirdPassword");
			acc.setEmail("laurent.fabius@gouv.fr");
			acc.setLogin("laurent.fabius");
			accService.create(acc);
	    });
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("Un Comptable existe deja avec cet email"));
	}

	@Test
	public void testFindAllAccountantWithSuccess() {
		
		// Given

		// When
		List<GpAccountant> accs = this.accService.findAll();
		
		// Then
		Assert.assertTrue(accs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer accId = this.accIdForAllTest;

		// When
		GpEmployee acc = this.accService.findById(accId);
		
		// Then
		Assert.assertNotNull(acc);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer accId = this.accIdForAllTest;
		
		// When
		this.accService.deleteById(accId);
		GpEmployee acc = this.accService.findById(accId);
		
		// Then
		Assert.assertNull(acc);
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		
		GpAccountant acc = new GpAccountant();
		
		Assert.assertNull(acc.getId());
		
		acc.setFileNumber("2001");
		acc.setLastname("Laurent");
		acc.setFirstname("FABIUS");
		acc.setPhoneNumber("0125698745");
		acc.setPassword("myThirdPassword");
		acc.setEmail("laurent.fabius@gouv.fr");
		acc.setLogin("laurent.fabius");
		
		acc = accService.create(acc);
		
		this.accIdForAllTest = acc.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.accService.deleteById(this.accIdForAllTest);
		
		if(!Objects.isNull(this.createAccId)) {
			
			this.accService.deleteById(this.createAccId);
			
		}
		
	}

}
