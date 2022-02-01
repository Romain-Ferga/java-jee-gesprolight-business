package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;

public class GpProjectManagerServiceTest {

	private IGpEmployeeService<GpProjectManager> pmService = new GpProjectManagerServiceImpl();
	private Integer pmIdForAllTest = null;
	private Integer createPmId = null;
	
	@Test
	public void testCreateEmployeeWithSuccess() throws GesproBusinessException {
		//Given
		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1024");
		emp.setLastname("HOLLANDE");
		emp.setFirstname("Francois");
		emp.setPhoneNumber("0365987854");
		emp.setPassword("mySecondPassword");
		emp.setEmail("francois.hollande@gouv.fr");
		emp.setLogin("francois.hollande");
		
		//When
		emp = pmService.create(emp) ;
		//On le sauvegarde pour le supprimer après
		this.createPmId = emp.getId();
		
		//Then
		Assert.assertNotNull(emp.getId());
	}
	
	@Test
	public void testCreateProjectManagerWithException() throws GesproBusinessException {
		// Given


		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpProjectManager emp = new GpProjectManager();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("1050");
			emp.setLastname("Segolene");
			emp.setFirstname("ROYAL");
			emp.setPhoneNumber("0256897856");
			emp.setPassword("mySecondPassword");
			emp.setEmail("segolene.royal@gouv.fr");
			emp.setLogin("sego.royal");
			emp = pmService.create(emp) ;
	    });
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("Un Chef de Projet existe deja avec cet email"));
	}
	
	@Test
	public void testFindAllEmployeeWithSuccess() {
		//Given
		
		//When 
		List<GpProjectManager> emps = this.pmService.findAll();
		
		//Then
		Assert.assertTrue(emps.size() >0);
	}
	
	@Test
	public void testFindByIdWithSuccess() {
		//Given
		Integer empId = this.pmIdForAllTest;
		
		//When 
		GpEmployee emp = this.pmService.findById(empId);
		//Then
		Assert.assertNotNull(emp);
	}
	
	@Test
	public void testDeleteByIdWithSuccess() {
		//Given
		Integer empId = this.pmIdForAllTest;
		
		//When 
		this.pmService.deleteById(empId);
		
		
		//Then
		GpEmployee emp = this.pmService.findById(empId);
		Assert.assertNull(emp);
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1050");
		emp.setLastname("Segolene");
		emp.setFirstname("ROYAL");
		emp.setPhoneNumber("0256897856");
		emp.setPassword("mySecondPassword");
		emp.setEmail("segolene.royal@gouv.fr");
		emp.setLogin("sego.royal");
		emp = pmService.create(emp) ;
		this.pmIdForAllTest = emp.getId();
	}
	
	@After
	public void deleteAllEntityAfter() {
		this.pmService.deleteById(this.pmIdForAllTest);
		if(!Objects.isNull(this.createPmId)) {
			this.pmService.deleteById(this.createPmId);
		}
	}
}
