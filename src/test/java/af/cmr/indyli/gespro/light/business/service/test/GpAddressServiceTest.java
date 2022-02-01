package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpAddressService;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.light.business.service.impl.GpAddressServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpEmployeeServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;

public class GpAddressServiceTest {

	private IGpAddressService addrService = new GpAddressServiceImpl();
	private IGpOrganizationService organizationService = new GpOrganizationServiceImpl();
	private IGpEmployeeService<GpEmployee> empService = new GpEmployeeServiceImpl();
	
	private Integer addrIdForAllTest = null;
	private Integer createdAddrId = null;
	
	private Integer orgIdForAllTest = null;
	private Integer createdOrgId = null;
	
	private Integer empIdForAllTest = null;
	private Integer createdEmpId = null;

	@Test
	public void testCreateAddressWithSuccess() throws GesproBusinessException {
		
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
		org = organizationService.create(org);
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
		emp = empService.create(emp);
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
		addr = addrService.create(addr);
		
		//On le sauvegarde pour le supprimer apres
		this.createdAddrId = addr.getId();
		
		// Then
		Assert.assertNotNull(addr.getId());
		
	}
	
	@Test
	public void testCreateAddressWithException() throws GesproBusinessException { // TODO : nécéssaire ?
		// Given


		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
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
			org = organizationService.create(org);
			this.orgIdForAllTest = org.getId();
			
			// creation employee
			emp.setFileNumber("1984");
			emp.setLastname("Nicolas");
			emp.setFirstname("SARKOZY");
			emp.setPhoneNumber("03187520990");
			emp.setPassword("myExPresidentPassword");
			emp.setEmail("nicolas.sarkozy@gouv.fr");
			emp.setLogin("nico.sark");
			emp = empService.create(emp);
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
			addr = addrService.create(addr) ;
			this.addrIdForAllTest = addr.getId();
			
	    });
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("Un Comptable existe deja avec cet email"));
	}

	@Test
	public void testFindAllEmployeeWithSuccess() {
		// Given

		// When
		List<GpAddress> addrs = this.addrService.findAll();
		
		// Then
		Assert.assertTrue(addrs.size() > 0);
		
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer empId = this.addrIdForAllTest;

		// When
		GpAddress addr = this.addrService.findById(empId);
		// Then
		Assert.assertNotNull(addr);
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer addrId = this.addrIdForAllTest;
		
		// When
		this.addrService.deleteById(addrId);
		GpAddress addr = this.addrService.findById(addrId);
		
		// Then
		Assert.assertNull(addr);
		
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		
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
		org = organizationService.create(org);
		this.orgIdForAllTest = org.getId();
		
		// creation employee
		emp.setFileNumber("1984");
		emp.setLastname("Nicolas");
		emp.setFirstname("SARKOZY");
		emp.setPhoneNumber("03187520990");
		emp.setPassword("myExPresidentPassword");
		emp.setEmail("nicolas.sarkozy@gouv.fr");
		emp.setLogin("nico.sark");
		emp = empService.create(emp);
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
		addr = addrService.create(addr) ;
		this.addrIdForAllTest = addr.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {

		this.empService.deleteById(this.empIdForAllTest);
		this.organizationService.deleteById(this.orgIdForAllTest);
		this.addrService.deleteById(this.addrIdForAllTest);
		
		if(!Objects.isNull(this.createdEmpId)) {
			
			this.empService.deleteById(this.createdEmpId);
			
		}
		
		if(!Objects.isNull(this.createdAddrId)) {
			
			this.addrService.deleteById(this.createdAddrId);
			
		}
		
		if(!Objects.isNull(this.createdOrgId)) {
			
			this.organizationService.deleteById(this.createdOrgId);
			
		}
		
	}

}
