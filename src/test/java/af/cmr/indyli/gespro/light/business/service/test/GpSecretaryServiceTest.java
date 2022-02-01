package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpSecretary;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.impl.GpSecretaryServiceImpl;

public class GpSecretaryServiceTest {
	
	private IGpEmployeeService<GpSecretary> secrService = new GpSecretaryServiceImpl();
	private Integer secrIdForAllTest = null;
	private Integer createSecrId = null;

	@Test
	public void testCreateSecretaryWithSuccess() throws GesproBusinessException {
		
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
		secr = secrService.create(secr);
		
		//On le sauvegarde pour le supprimer apres
		this.createSecrId = secr.getId();
		
		// Then
		Assert.assertNotNull(secr.getId());
		
	}
	
	@Test
	public void testCreateSecretaryWithException() throws GesproBusinessException {
		// Given


		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpSecretary secr = new GpSecretary();
			
			Assert.assertNull(secr.getId());
			
			secr.setFileNumber("2001");
			secr.setLastname("Laurent");
			secr.setFirstname("FABIUS");
			secr.setPhoneNumber("0125698745");
			secr.setPassword("myThirdPassword");
			secr.setEmail("laurent.fabius@gouv.fr");
			secr.setLogin("laurent.fabius");
			
			secr = secrService.create(secr) ;
	    });
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("Un Comptable existe deja avec cet email"));
	}

	@Test
	public void testFindAllSecretaryWithSuccess() {
		
		// Given

		// When
		List<GpSecretary> secrs = this.secrService.findAll();
		
		// Then
		Assert.assertTrue(secrs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer secrId = this.secrIdForAllTest;

		// When
		GpEmployee secr = this.secrService.findById(secrId);
		
		// Then
		Assert.assertNotNull(secr);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer secrId = this.secrIdForAllTest;
		
		// When
		this.secrService.deleteById(secrId);
		GpEmployee secr = this.secrService.findById(secrId);
		
		// Then
		Assert.assertNull(secr);
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		
		GpSecretary secr = new GpSecretary();
		
		Assert.assertNull(secr.getId());
		
		secr.setFileNumber("2001");
		secr.setLastname("Laurent");
		secr.setFirstname("FABIUS");
		secr.setPhoneNumber("0125698745");
		secr.setPassword("myThirdPassword");
		secr.setEmail("laurent.fabius@gouv.fr");
		secr.setLogin("laurent.fabius");
		
		secr = secrService.create(secr) ;
		
		this.secrIdForAllTest = secr.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.secrService.deleteById(this.secrIdForAllTest);
		
		if(!Objects.isNull(this.createSecrId)) {
			
			this.secrService.deleteById(this.createSecrId);
			
		}
		
	}

}
