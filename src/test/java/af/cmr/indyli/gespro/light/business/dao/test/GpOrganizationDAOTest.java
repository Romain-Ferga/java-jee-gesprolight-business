package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public class GpOrganizationDAOTest {

	private GpOrganizationDAOImpl orgDAO = new GpOrganizationDAOImpl();
	private Integer orgIdForAllTest = null;
	private Integer createOrgId = null;
	
	@Test
	public void testCreateOrganizationWithSuccess() {
		
		// Given
		GpOrganization org = new GpOrganization();
		
		Assert.assertNull(org.getId());
		
		org.setOrgCode("org code");
		org.setContactName("org name");
		org.setPhoneNumber(0606060606);
		org.setContactName("contact name");
		org.setContactEmail("contact email");
		org.setAdrWeb("org address");
		
		// When
		org = orgDAO.create(org);
		
		//On le sauvegarde pour le supprimer apres
		this.createOrgId = org.getId();
		
		// Then
		Assert.assertNotNull(org.getId());
		
	}

	@Test
	public void testFindAllOrganizationWithSuccess() {
		
		// Given

		// When
		List<GpOrganization> orgs = this.orgDAO.findAll();
		
		// Then
		Assert.assertTrue(orgs.size() > 0);
		
	}

}
