package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public class GpProjectDAOTest {

	private GpProjectDAOImpl prjDAO = new GpProjectDAOImpl();
	private Integer prjIdForAllTest = null;
	private Integer createPrjId = null;
	
	@Test
	public void testGpProjectWithSuccess() {
		
		// Given
		GpProject prj = new GpProject();
		
		Integer orgId = 1;
		GpOrganization org = new GpOrganizationDAOImpl().findById(orgId);
		
		Integer pmId = 1;
		GpProjectManager pm = new GpProjectManagerDAOImpl().findById(pmId);
		
		Assert.assertNull(prj.getId());

		prj.setProjectCode("prj code");
		prj.setName("prj name");
		prj.setDescription("prj description");
		prj.setStartDate(new Date());
		prj.setEndDate(new Date());
		prj.setAmount(0.d);
		prj.setCreationDate(new Date());
		prj.setUpdateDate(new Date());
		prj.setGpOrganization(org);
		prj.setGpChefProjet(pm);
		
		
		// When
		prj = prjDAO.create(prj);
		
		//On le sauvegarde pour le supprimer apres
		this.createPrjId = prj.getId();
		
		// Then
		Assert.assertNotNull(prj.getId());
		
	}

	@Test
	public void testFindAllProjectWithSuccess() {
		
		// Given

		// When
		List<GpProject> prjs = this.prjDAO.findAll();
		
		// Then
		Assert.assertTrue(prjs.size() > 0);
		
	}

}
