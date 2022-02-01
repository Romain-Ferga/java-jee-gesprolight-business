package af.cmr.indyli.gespro.light.business.service.test;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

public class GpPhaseServiceTest {

	private IGpPhaseService phsService = new GpPhaseServiceImpl();
	private IGpEmployeeService<GpProjectManager> projectManagerService = new GpProjectManagerServiceImpl();
	private IGpProjectService projectService = new GpProjectServiceImpl();
	private IGpOrganizationService organizationService = new GpOrganizationServiceImpl();

	private Integer phaseIdForAllTest = null;
	private Integer createdPhaseId = null;

	private Integer orgIdForAllTest = null;
	private Integer createdOrgId = null;

	private Integer projectManagerIdForAllTest = null;
	private Integer createProjectManagerId = null;

	private Integer projectIdForAllTest = null;
	private Integer createdProjectId = null;
	
	@Test
	public void testCreateGpPhaseWithSuccess() throws GesproBusinessException {

		// Given
		// Init GpProjetManager
		GpProjectManager projectManager = new GpProjectManager();
		Assert.assertNull(projectManager.getId());
		projectManager.setFileNumber("1008");
		projectManager.setLastname("Jean-Pierre");
		projectManager.setFirstname("ALCABACHE");
		projectManager.setPhoneNumber("0256897856");
		projectManager.setPassword("myStreos");
		projectManager.setEmail("jp.alca@gouv.fr");
		projectManager.setLogin("jp.alca");
		projectManager = projectManagerService.create(projectManager);
		this.createProjectManagerId = projectManager.getId();


		// creation organisation
		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization.setOrgCode("ALPHA");
		organization.setName("Big Org");
		organization.setAdrWeb("bigorg.com");
		organization.setContactEmail("big@org.com");
		organization.setContactName("CName");
		organization.setPhoneNumber(7895);
		organization = organizationService.create(organization);
		this.createdOrgId = organization.getId();

		// creation project
		GpProject project = new GpProject();
		Assert.assertNull(project.getId());
		project.setProjectCode("Code-10");
		project.setName("Project-13");
		project.setDescription("Second Project");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(5623.66);
		project.setCreationDate(new Date());
		project.setGpOrganization(organization);
		project.setGpChefProjet(projectManager);
		project = this.projectService.create(project);
		this.createdProjectId = project.getId();
		Assert.assertNotNull(project.getId());

		//Creation de la phase
		GpPhase phaseToCreate = new GpPhase();
		Assert.assertNull(phaseToCreate.getId());
		phaseToCreate.setPhaseCode("PH_C087");
		phaseToCreate.setDescription("phs description");
		phaseToCreate.setStartDate(new Date());
		phaseToCreate.setEndDate(new Date());
		phaseToCreate.setAmount(0.d);
		phaseToCreate.setCreationDate(new Date());
		phaseToCreate.setGpProject(project);


		// When
		phaseToCreate = this.phsService.create(phaseToCreate);
		//On le sauvegarde pour le supprimer apres
		this.createdPhaseId = phaseToCreate.getId();
		
		// Then
		Assert.assertNotNull(this.createdPhaseId);

	}

	@Test
	public void testFindAllPhaseWithSuccess() {

		// Given

		// When
		List<GpPhase> phss = this.phsService.findAll();

		// Then
		Assert.assertTrue(phss.size() > 0);

	}

	@Test
	public void testFindByIdWithSuccess() {

		// Given
		Integer phsId = this.phaseIdForAllTest;

		// When
		GpPhase phs = this.phsService.findById(phsId);

		// Then
		Assert.assertNotNull(phs);

	}

	@Test
	public void testDelete() {

		// Given
		Integer phsId = this.phaseIdForAllTest;

		// When
		this.phsService.deleteById(phsId);
		GpPhase phs = this.phsService.findById(phsId);

		// Then
		Assert.assertNull(phs);

	}


	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		
		// Init GpProjetManager
		GpProjectManager projectManager = new GpProjectManager();
		Assert.assertNull(projectManager.getId());
		projectManager.setFileNumber("1050");
		projectManager.setLastname("Segolene");
		projectManager.setFirstname("ROYAL");
		projectManager.setPhoneNumber("0256897856");
		projectManager.setPassword("mySecondPassword");
		projectManager.setEmail("segolene.royal@gouv.fr");
		projectManager.setLogin("sego.royal");
		projectManager = projectManagerService.create(projectManager);
		this.projectManagerIdForAllTest = projectManager.getId();


		// creation organisation
		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization.setOrgCode("ALPHA BEFORE");
		organization.setName("Big Org");
		organization.setAdrWeb("bigorg.com");
		organization.setContactEmail("big@org.com");
		organization.setContactName("CName");
		organization.setPhoneNumber(7895);
		organization = organizationService.create(organization);
		this.orgIdForAllTest = organization.getId();

		// creation project
		GpProject project = new GpProject();
		Assert.assertNull(project.getId());
		project.setProjectCode("Code-1");
		project.setName("Project-1");
		project.setDescription("First Project");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(5623.66);
		project.setCreationDate(new Date());
		project.setGpOrganization(organization);
		project.setGpChefProjet(projectManager);
		project = this.projectService.create(project);
		this.projectIdForAllTest = project.getId();
		Assert.assertNotNull(project.getId());

		// Creation de la phase
		GpPhase phaseToCreate = new GpPhase();
		Assert.assertNull(phaseToCreate.getId());
		phaseToCreate.setPhaseCode("PH_C0230");
		phaseToCreate.setDescription("phs description");
		phaseToCreate.setStartDate(new Date());
		phaseToCreate.setEndDate(new Date());
		phaseToCreate.setAmount(0.d);
		phaseToCreate.setCreationDate(new Date());
		phaseToCreate.setGpProject(project);
		this.phsService.create(phaseToCreate);
		this.phaseIdForAllTest = phaseToCreate.getId();
		Assert.assertNotNull(phaseToCreate.getId());
		// TODO 7: Assert.assertNotNull(phaseToCreate.getId()); ?
	}

	@After
	public void deleteAllEntityAfter() {
		this.phsService.deleteById(this.phaseIdForAllTest);
		this.projectService.deleteById(this.projectIdForAllTest);
		this.organizationService.deleteById(this.orgIdForAllTest);
		this.projectManagerService.deleteById(projectManagerIdForAllTest);
		//Suppression des enregitrements cres lors du Test de Creation
		if(!Objects.isNull(this.createdPhaseId)) {
			this.phsService.deleteById(this.createdPhaseId);
		}
		if(!Objects.isNull(this.createdProjectId)) {
			this.projectService.deleteById(this.createdProjectId);
		}
		if(!Objects.isNull(this.createdOrgId)) {
			this.organizationService.deleteById(this.createdOrgId);
		}
		if(!Objects.isNull(this.createProjectManagerId)) {
			this.projectManagerService.deleteById(this.createProjectManagerId);
		}
	}


}
