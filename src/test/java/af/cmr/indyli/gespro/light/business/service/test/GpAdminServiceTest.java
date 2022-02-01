package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpAdmin;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.impl.GpAdminServiceImpl;

public class GpAdminServiceTest {
	
	private IGpEmployeeService<GpAdmin> admService = new GpAdminServiceImpl();
	private Integer admIdForAllTest = null;
	private Integer createAdmId = null;

	@Test
	public void testCreateAdminWithSuccess() throws GesproBusinessException {
		
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
		adm = admService.create(adm);
		
		//On le sauvegarde pour le supprimer apres
		this.createAdmId = adm.getId();
		
		// Then
		Assert.assertNotNull(adm.getId());
		
	}
	
	@Test
	public void testCreateAdminWithException() throws GesproBusinessException {
		// Given


		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpAdmin adm = new GpAdmin();
			
			adm.setFileNumber("2001");
			adm.setLastname("Laurent");
			adm.setFirstname("FABIUS");
			adm.setPhoneNumber("0125698745");
			adm.setPassword("myThirdPassword");
			adm.setEmail("laurent.fabius@gouv.fr");
			adm.setLogin("laurent.fabius");
			
			adm = admService.create(adm) ;
	    });
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("Un Admin existe deja avec cet email"));
	}

	@Test
	public void testFindAllAdminWithSuccess() {
		
		// Given

		// When
		List<GpAdmin> adms = this.admService.findAll();
		
		// Then
		Assert.assertTrue(adms.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer admId = this.admIdForAllTest;

		// When
		GpEmployee adm = this.admService.findById(admId);
		
		// Then
		Assert.assertNotNull(adm);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer admId = this.admIdForAllTest;
		
		// When
		this.admService.deleteById(admId);
		GpEmployee adm = this.admService.findById(admId);
		
		// Then
		Assert.assertNull(adm);
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		
		GpAdmin adm = new GpAdmin();
		
		Assert.assertNull(adm.getId());
		
		adm.setFileNumber("2001");
		adm.setLastname("Laurent");
		adm.setFirstname("FABIUS");
		adm.setPhoneNumber("0125698745");
		adm.setPassword("myThirdPassword");
		adm.setEmail("laurent.fabius@gouv.fr");
		adm.setLogin("laurent.fabius");
		
		adm = admService.create(adm) ;
		
		this.admIdForAllTest = adm.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.admService.deleteById(this.admIdForAllTest);
		
		if(!Objects.isNull(this.createAdmId)) {
			
			this.admService.deleteById(this.createAdmId);
			
		}
		
	}

}
