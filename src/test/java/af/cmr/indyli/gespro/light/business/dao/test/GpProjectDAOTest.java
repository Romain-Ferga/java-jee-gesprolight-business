package af.cmr.indyli.gespro.light.business.dao.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public class GpProjectDAOTest {

	private IGpProjectManagerDAO pmDAO = new GpProjectManagerDAOImpl();
	private IGpOrganizationDAO orgDAO = new GpOrganizationDAOImpl();
	private IGpProjectDAO prjDAO = new GpProjectDAOImpl();

	private Integer pmIdForAllTest = null;
	private Integer pmIdCreatedTest = null;

	private Integer orgIdForAllTest = null;
	private Integer orgIdCreatedTest = null;

	private Integer prjIdForAllTest = null;
	private Integer prjIdCreatedTest = null;

	@Test
	public void testCreateProjectWithSuccess() {

		// Given

		// creation ProjetManager
		GpProjectManager pm = new GpProjectManager();
		Assert.assertNull(pm.getId());
		pm.setFileNumber("1751");
		pm.setLastname("Jean-Luc");
		pm.setFirstname("Melenchon");
		pm.setPhoneNumber("0683897891");
		pm.setPassword("myPassword");
		pm.setEmail("jl.melenchon@gouv.fr");
		pm.setLogin("jl.mlchn");
		pm = pmDAO.create(pm);
		assertNotNull(pm.getId());
		this.pmIdCreatedTest = pm.getId();

		// creation organisation
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org.setOrgCode("BETA");
		org.setName("Little Org");
		org.setAdrWeb("littleorg.com");
		org.setContactEmail("little@org.com");
		org.setContactName("CName");
		org.setPhoneNumber(9581);
		org = orgDAO.create(org);
		Assert.assertNotNull(org.getId());
		this.orgIdCreatedTest = org.getId();

		// creation project
		GpProject prj = new GpProject();
		Assert.assertNull(prj.getId());
		prj.setProjectCode("Code2");
		prj.setName("Project2");
		prj.setDescription("SecondProject");
		prj.setStartDate(new Date());
		prj.setEndDate(new Date());
		prj.setAmount(5623.66);
		prj.setCreationDate(new Date());
		prj.setGpOrganization(org);
		prj.setGpChefProjet(pm);
		prj = this.prjDAO.create(prj);
		Assert.assertNotNull(prj.getId());
		this.prjIdCreatedTest = prj.getId();

	}

	@Test
	public void testFindAllProjectsWithSuccess() {

		// Given

		// When
		List<GpProject> prjs = this.prjDAO.findAll();

		// Then
		Assert.assertTrue(prjs.size() == 1);

	}

	@Test
	public void testFindByIdWithSuccess() {

		// Given
		Integer prjId = this.prjIdForAllTest;

		// When
		Assert.assertNotNull(prjId);
		GpProject prj = this.prjDAO.findById(prjId);

		// Then
		Assert.assertNotNull(prj);

	}

	@Test
	public void testUpdateProjectsWithSuccess() {

		// Given
		Integer prjId = this.prjIdForAllTest;
		Assert.assertNotNull(prjId);

		// When
		GpProject prj = this.prjDAO.findById(prjId);
		prj.setAmount(8659);
		prjDAO.update(prj);

	}

	@Test
	public void testDeleteProjectWithSuccess() {

		// Given
		Integer prjId = this.prjIdForAllTest;
		Assert.assertNotNull(prjId);

		// When
		prjDAO.deleteById(prjId);

	}

	@Before
	public void prepareAllEntityBefore() {

		// creation ProjetManager
		GpProjectManager pm = new GpProjectManager();
		pm.setFileNumber("1050");
		pm.setLastname("Segolene");
		pm.setFirstname("ROYAL");
		pm.setPhoneNumber("0256897856");
		pm.setPassword("mySecondPassword");
		pm.setEmail("segolene.royal@gouv.fr");
		pm.setLogin("sego.royal");
		pm = pmDAO.create(pm);
		assertNotNull(pm.getId());

		// creation organisation
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org.setOrgCode("ALPHA");
		org.setName("Big Org");
		org.setAdrWeb("bigorg.com");
		org.setContactEmail("big@org.com");
		org.setContactName("CName");
		org.setPhoneNumber(7895);
		org = orgDAO.create(org);
		Assert.assertNotNull(org.getId());

		// creation project
		GpProject prj = new GpProject();
		Assert.assertNull(prj.getId());
		prj.setProjectCode("Code1");
		prj.setName("Project1");
		prj.setDescription("FirstProject");
		prj.setStartDate(new Date());
		prj.setEndDate(new Date());
		prj.setAmount(5623.66);
		prj.setCreationDate(new Date());
		prj.setGpOrganization(org);
		prj.setGpChefProjet(pm);

		prj = this.prjDAO.create(prj);
		Assert.assertNotNull(prj.getId());

		this.prjIdForAllTest = prj.getId();

	}

	@After
	public void deleteAllEntityAfter() {

		this.pmDAO.deleteById(this.pmIdForAllTest);
		this.orgDAO.deleteById(this.orgIdForAllTest);
		this.prjDAO.deleteById(this.prjIdForAllTest);

		if (!Objects.isNull(this.pmIdCreatedTest)) {

			this.pmDAO.deleteById(this.pmIdCreatedTest);

		}

		if (!Objects.isNull(this.orgIdCreatedTest)) {

			this.orgDAO.deleteById(this.orgIdCreatedTest);

		}

		if (!Objects.isNull(this.prjIdCreatedTest)) {

			this.prjDAO.deleteById(prjIdCreatedTest);

		}

	}

}
