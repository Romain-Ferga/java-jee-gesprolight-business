package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpDirector;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.impl.GpDirectorServiceImpl;

public class GpDirectorServiceTest {
	
	private IGpEmployeeService<GpDirector> dirService = new GpDirectorServiceImpl();
	private Integer dirIdForAllTest = null;
	private Integer createDirId = null;

	@Test
	public void testCreateDirectorWithSuccess() throws GesproBusinessException {
		
		// Given
		GpDirector dir = new GpDirector();
		
		Assert.assertNull(dir.getId());
		
		dir.setFileNumber("18024");
		dir.setLastname("PELTIER");
		dir.setFirstname("Guillaume");
		dir.setPhoneNumber("0265863289");
		dir.setPassword("myPassword");
		dir.setEmail("guillaume.peltierAcc@gouv.fr");
		dir.setLogin("guillaume.peltierAcc");

		// When
		dir = dirService.create(dir);
		
		//On le sauvegarde pour le supprimer apres
		this.createDirId = dir.getId();
		
		// Then
		Assert.assertNotNull(dir.getId());
		
	}
	
	@Test
	public void testCreateDirectorWithException() throws GesproBusinessException {
		// Given


		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpDirector dir = new GpDirector();
			
			Assert.assertNull(dir.getId());
			
			dir.setFileNumber("2001");
			dir.setLastname("Laurent");
			dir.setFirstname("FABIUS");
			dir.setPhoneNumber("0125698745");
			dir.setPassword("myThirdPassword");
			dir.setEmail("laurent.fabius@gouv.fr");
			dir.setLogin("laurent.fabius");
			
			dir = dirService.create(dir) ;
	    });
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("Un Directeur existe deja avec cet email"));
	}

	@Test
	public void testFindAllDirectorWithSuccess() {
		
		// Given

		// When
		List<GpDirector> dirs = this.dirService.findAll();
		
		// Then
		Assert.assertTrue(dirs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer dirId = this.dirIdForAllTest;

		// When
		GpEmployee dir = this.dirService.findById(dirId);
		
		// Then
		Assert.assertNotNull(dir);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer dirId = this.dirIdForAllTest;
		
		// When
		this.dirService.deleteById(dirId);
		
		GpEmployee dir = this.dirService.findById(dirId);
		
		// Then
		Assert.assertNull(dir);
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		
		GpDirector dir = new GpDirector();
		
		Assert.assertNull(dir.getId());
		
		dir.setFileNumber("2001");
		dir.setLastname("Laurent");
		dir.setFirstname("FABIUS");
		dir.setPhoneNumber("0125698745");
		dir.setPassword("myThirdPassword");
		dir.setEmail("laurent.fabius@gouv.fr");
		dir.setLogin("laurent.fabius");
		
		dir = dirService.create(dir) ;
		
		this.dirIdForAllTest = dir.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.dirService.deleteById(this.dirIdForAllTest);
		
		if(!Objects.isNull(this.createDirId)) {
			
			this.dirService.deleteById(this.createDirId);
			
		}
		
	}

}
