package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;

public class GpOrganizationServiceTest {

	private GpOrganizationServiceImpl orgService = new GpOrganizationServiceImpl();
	private Integer orgIdForAllTest = null;
	private Integer createOrgId = null;
	
	@Test
	public void testCreateOrganizationWithSuccess() throws GesproBusinessException {
		
		// Given
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		
		org.setOrgCode("org code");
		org.setName("org name");
		org.setPhoneNumber(0606060606);
		org.setContactName("contact name");
		org.setContactEmail("contact email");
		org.setAdrWeb("org address");
		
		// When
		org = orgService.create(org);
		
		//On le sauvegarde pour le supprimer apres
		this.createOrgId = org.getId();
		
		// Then
		Assert.assertNotNull(org.getId());
		
	}

	@Test
	public void testFindAllOrganizationWithSuccess() {
		
		// Given

		// When
		List<GpOrganization> orgs = this.orgService.findAll();
		
		// Then
		Assert.assertTrue(orgs.size() > 0);
		
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer orgId = this.orgIdForAllTest;

		// When
		GpOrganization org = this.orgService.findById(orgId);
		
		// Then
		Assert.assertNotNull(org);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer orgId = this.orgIdForAllTest;
		
		// When
		this.orgService.deleteById(orgId);
		GpOrganization org = this.orgService.findById(orgId);
		
		// Then
		Assert.assertNull(org);
		
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		
		org.setOrgCode("org code [before test]");
		org.setName("org name [before test]");
		org.setPhoneNumber(0606060606);
		org.setContactName("contact name [before test]");
		org.setContactEmail("contact email [before test]");
		org.setAdrWeb("org address [before test]");
		
		org = orgService.create(org);
		
		this.orgIdForAllTest = org.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.orgService.deleteById(this.orgIdForAllTest);
		
		if(!Objects.isNull(this.createOrgId)) {
			
			this.orgService.deleteById(this.createOrgId);
			
		}
		
	}

}
