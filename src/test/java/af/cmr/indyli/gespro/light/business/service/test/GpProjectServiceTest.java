package af.cmr.indyli.gespro.light.business.service.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

public class GpProjectServiceTest {

	private IGpEmployeeService<GpProjectManager> projectManagerService = new GpProjectManagerServiceImpl();
	private IGpProjectService projectService = new GpProjectServiceImpl();
	private IGpOrganizationService organizationService = new GpOrganizationServiceImpl();

	private Integer pjIdForAllTest = null;
	private Integer pjIdCreateTest = null;

	private GpOrganization orgTest;
	private GpProjectManager pmTest;

	@Test
	public void testCreateProjectWithSuccess() throws GesproBusinessException {
		
		// Given
		GpProject project = new GpProject();
		Assert.assertNull(project.getId());
		project.setProjectCode("Code2");
		project.setName("Project2");
		project.setDescription("SecondProject");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(5623.66);
		project.setCreationDate(new Date());

		Assert.assertNotNull(this.pmTest.getId());
		Assert.assertNotNull(this.orgTest.getId());

		project.setGpOrganization(this.orgTest);
		project.setGpChefProjet(pmTest);

		project = this.projectService.create(project);
		Assert.assertNotNull(project.getId());

		this.pjIdCreateTest = project.getId();

	}

	@Test
	public void testFindAllProjectsWithSuccess() {
		
		// Given

		// When
		List<GpProject> projects = this.projectService.findAll();
		
		// Then
		Assert.assertTrue(projects.size() > 0);
		
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer projectId = this.pjIdForAllTest;
		
		// When
		Assert.assertNotNull(projectId);
		GpProject project = this.projectService.findById(projectId);
		
		// Then
		Assert.assertNotNull(project);
		
	}

	@Test
	public void testUpdateProjectsWithSuccess() throws GesproBusinessException {
		
		// Given
		Integer projectId = this.pjIdForAllTest;
		Assert.assertNotNull(projectId);
		// When

		GpProject gpProject = this.projectService.findById(projectId);
		gpProject.setAmount(8659);
		projectService.update(gpProject);
		
	}

	@Test
	public void testDeleteProjectWithSuccess() {
		
		// Given
		Integer projectId = this.pjIdForAllTest;
		Assert.assertNotNull(projectId);
		
		// When
		projectService.deleteById(projectId);
		
	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {

		// Init GpProjetManager
		GpProjectManager emp = new GpProjectManager();
		emp.setFileNumber("1050");
		emp.setLastname("Segolene");
		emp.setFirstname("ROYAL");
		emp.setPhoneNumber("0256897856");
		emp.setPassword("mySecondPassword");
		emp.setEmail("segolene.royal@gouv.fr");
		emp.setLogin("sego.royal");
		emp = projectManagerService.create(emp);

		this.pmTest = new GpProjectManager();
		this.pmTest = emp;
		assertNotNull(this.pmTest.getId());

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

		this.orgTest = new GpOrganization();
		this.orgTest = organization;
		Assert.assertNotNull(this.orgTest.getId());

		// creation project
		GpProject project = new GpProject();
		Assert.assertNull(project.getId());
		project.setProjectCode("Code1");
		project.setName("Project1");
		project.setDescription("FirstProject");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(5623.66);
		project.setCreationDate(new Date());
		project.setGpOrganization(this.orgTest);
		project.setGpChefProjet(this.pmTest);

		project = this.projectService.create(project);
		Assert.assertNotNull(project.getId());

		this.pjIdForAllTest = project.getId();
		
	}

	@After
	public void deleteAllEntityAfter() {
		
		this.projectService.deleteById(this.pjIdForAllTest);
		
		if (!Objects.isNull(this.pjIdCreateTest)) {
			
			this.projectService.deleteById(pjIdCreateTest);
			
		}
		
		if (!Objects.isNull(this.pmTest)) {
			
			this.projectManagerService.deleteById(this.pmTest.getId());
			
		}
		
		if (!Objects.isNull(this.orgTest)) {
			
			this.organizationService.deleteById(this.orgTest.getId());
			
		}
		
	}


}
