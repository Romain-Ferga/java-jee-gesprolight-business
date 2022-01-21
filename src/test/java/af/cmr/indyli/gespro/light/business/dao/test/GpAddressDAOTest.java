package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAddressDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;

public class GpAddressDAOTest {

	private IGpAddressDAO addrDAO = new GpAddressDAOImpl();
	private Integer addrIdForAllTest = null;
	private Integer createAddrId = null;

	@Test
	public void testCreateEmployeeWithSuccess() {
		
		// Given
		GpAddress addr = new GpAddress();
		GpEmployee emp = new GpEmployee();
		//GpOrganization org = new GpOrganization();
		
		Assert.assertNull(addr.getId());
		
		byte b = 1;

		addr.setCountry("FRANCE");
		addr.setIsMain(b);
		addr.setStreetLabel("Avenue des Champs Elysées");
		addr.setStreetNumber(1);
		addr.setZipCode(75000);
		addr.setGpEmployee(emp);
		//addr.setGpOrganization(org);

		// When
		addr = addrDAO.create(addr);
		
		//On le sauvegarde pour le supprimer apres
		this.createAddrId = addr.getId();
		
		// Then
		Assert.assertNotNull(addr.getId());
		
	}

	@Test
	public void testFindAllEmployeeWithSuccess() {
		// Given

		// When
		List<GpAddress> addrs = this.addrDAO.findAll();
		
		// Then
		Assert.assertTrue(addrs.size() > 0);
		
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer empId = this.addrIdForAllTest;

		// When
		GpAddress addr = this.addrDAO.findById(empId);
		// Then
		Assert.assertNotNull(addr);
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer addrId = this.addrIdForAllTest;
		
		// When
		this.addrDAO.deleteById(addrId);
		GpAddress addr = this.addrDAO.findById(addrId);
		
		// Then
		Assert.assertNull(addr);
		
	}
	
	@Before
	public void prepareAllEntityBefore() {
		
		GpAddress addr = new GpAddress();
		GpEmployee emp = new GpEmployee();
		GpOrganization org = new GpOrganization();
		
		Assert.assertNull(addr.getId());
		
		byte b = 1;

		addr.setCountry("FRANCE");
		addr.setIsMain(b);
		addr.setStreetLabel("Place de la Bastille");
		addr.setStreetNumber(5);
		addr.setZipCode(75000);
		addr.setGpEmployee(emp);
		addr.setGpOrganization(org);
		
		addr = addrDAO.create(addr) ;
		
		this.addrIdForAllTest = addr.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.addrDAO.deleteById(this.addrIdForAllTest);
		
		if(!Objects.isNull(this.createAddrId)) {
			
			this.addrDAO.deleteById(this.createAddrId);
			
		}
		
	}

}
