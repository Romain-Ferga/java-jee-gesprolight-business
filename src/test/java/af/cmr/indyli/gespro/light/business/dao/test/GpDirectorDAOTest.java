package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpDirectorDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.entity.GpDirector;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;

public class GpDirectorDAOTest {
	
	private IGpEmployeeDAO<GpDirector> dirDAO = new GpDirectorDAOImpl();
	private Integer dirIdForAllTest = null;
	private Integer createDirId = null;

	@Test
	public void testCreateDirectorWithSuccess() {
		
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
		dir = dirDAO.create(dir);
		
		//On le sauvegarde pour le supprimer apres
		this.createDirId = dir.getId();
		
		// Then
		Assert.assertNotNull(dir.getId());
		
	}

	@Test
	public void testFindAllDirectorWithSuccess() {
		
		// Given

		// When
		List<GpDirector> dirs = this.dirDAO.findAll();
		
		// Then
		Assert.assertTrue(dirs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer dirId = this.dirIdForAllTest;

		// When
		GpEmployee dir = this.dirDAO.findById(dirId);
		
		// Then
		Assert.assertNotNull(dir);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer dirId = this.dirIdForAllTest;
		
		// When
		this.dirDAO.deleteById(dirId);
		
		GpEmployee dir = this.dirDAO.findById(dirId);
		
		// Then
		Assert.assertNull(dir);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		
		GpDirector dir = new GpDirector();
		
		Assert.assertNull(dir.getId());
		
		dir.setFileNumber("2001");
		dir.setLastname("Laurent");
		dir.setFirstname("FABIUS");
		dir.setPhoneNumber("0125698745");
		dir.setPassword("myThirdPassword");
		dir.setEmail("laurent.fabius@gouv.fr");
		dir.setLogin("laurent.fabius");
		
		dir = dirDAO.create(dir) ;
		
		this.dirIdForAllTest = dir.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.dirDAO.deleteById(this.dirIdForAllTest);
		
		if(!Objects.isNull(this.createDirId)) {
			
			this.dirDAO.deleteById(this.createDirId);
			
		}
		
	}

}
