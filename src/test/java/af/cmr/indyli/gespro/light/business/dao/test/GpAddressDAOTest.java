package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAddressDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmployeeDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;

public class GpAddressDAOTest {

	private IGpAddressDAO addrDAO = new GpAddressDAOImpl();
	private IGpOrganizationDAO organizationDAO = new GpOrganizationDAOImpl();
	private IGpEmployeeDAO<GpEmployee> empDAO = new GpEmployeeDAOImpl();
	
	private Integer addrIdForAllTest = null;
	private Integer createdAddrId = null;
	
	private Integer orgIdForAllTest = null;
	private Integer createdOrgId = null;
	
	private Integer empIdForAllTest = null;
	private Integer createdEmpId = null;

	@Test
	public void testCreateAddressWithSuccess() {
		
		// Given
		GpAddress addr = new GpAddress();
		GpEmployee emp = new GpEmployee();
		GpOrganization org = new GpOrganization();
		
		Assert.assertNull(addr.getId());
		
		// creation organization
		Assert.assertNull(org.getId());
		org.setOrgCode("ALPHA");
		org.setName("Big Org");
		org.setAdrWeb("bigorg.com");
		org.setContactEmail("big@org.com");
		org.setContactName("CName");
		org.setPhoneNumber(7895);
		org = organizationDAO.create(org);
		this.createdOrgId = org.getId();
		Assert.assertNotNull(org.getId());
		
		// creation employee
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1029");
		emp.setLastname("George");
		emp.setFirstname("POMPIDOU");
		emp.setPhoneNumber("03165120995");
		emp.setPassword("myExPresidentPassword");
		emp.setEmail("george.pompidou@gouv.fr");
		emp.setLogin("george.pomp");
		emp = empDAO.create(emp);
		this.createdEmpId = emp.getId();
		Assert.assertNotNull(emp.getId());
		
		byte b = 1;

		// creation address
		addr.setCountry("FRANCE");
		addr.setIsMain(b);
		addr.setStreetLabel("Avenue des Champs Elysées");
		addr.setStreetNumber(1);
		addr.setZipCode(75000);
		addr.setGpEmployee(emp);
		addr.setGpOrganization(org);

		// When
		addr = addrDAO.create(addr);
		
		//On le sauvegarde pour le supprimer apres
		this.createdAddrId = addr.getId();
		
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
				
		// creation organization
		org.setOrgCode("ALPHA");
		org.setName("Big Org");
		org.setAdrWeb("bigorg.com");
		org.setContactEmail("big@org.com");
		org.setContactName("CName");
		org.setPhoneNumber(7895);
		org = organizationDAO.create(org);
		this.orgIdForAllTest = org.getId();
		
		// creation employee
		emp.setFileNumber("1984");
		emp.setLastname("Nicolas");
		emp.setFirstname("SARKOZY");
		emp.setPhoneNumber("03187520990");
		emp.setPassword("myExPresidentPassword");
		emp.setEmail("nicolas.sarkozy@gouv.fr");
		emp.setLogin("nico.sark");
		emp = empDAO.create(emp);
		this.empIdForAllTest = emp.getId();
		
		// creation address
		byte byteExample = 1;
		addr.setCountry("FRANCE");
		addr.setIsMain(byteExample);
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

		this.empDAO.deleteById(this.empIdForAllTest);
		this.organizationDAO.deleteById(this.orgIdForAllTest);
		this.addrDAO.deleteById(this.addrIdForAllTest);
		
		if(!Objects.isNull(this.createdEmpId)) {
			
			this.empDAO.deleteById(this.createdEmpId);
			
		}
		
		if(!Objects.isNull(this.createdOrgId)) {
			
			this.organizationDAO.deleteById(this.createdOrgId);
			
		}
		
		if(!Objects.isNull(this.createdAddrId)) {
			
			this.addrDAO.deleteById(this.createdAddrId);
			
		}
		
	}

}
