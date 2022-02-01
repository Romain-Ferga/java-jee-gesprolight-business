package af.cmr.indyli.gespro.light.business.service.test;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.impl.GpBillServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

public class GpBillServiceTest {

	private GpBillServiceImpl billService = new GpBillServiceImpl();
	private GpPhaseServiceImpl phsService = new GpPhaseServiceImpl();
	private GpProjectServiceImpl prjService = new GpProjectServiceImpl();
	private GpOrganizationServiceImpl orgService = new GpOrganizationServiceImpl();
	private GpProjectManagerServiceImpl pmService = new GpProjectManagerServiceImpl();
	
	private Integer billIdForAllTest = null;
	private Integer createdBillId = null;
	
	private Integer phsIdForAllTest = null;
	private Integer createdPhsId = null;
	
	private Integer orgIdForAllTest = null;
	private Integer createdOrgId = null;
	
	private Integer prjIdForAllTest = null;
	private Integer createdPrjId = null;
	
	private Integer pmIdForAllTest = null;
	private Integer createdPmId = null;
	
	@Test
	public void testCreateBillWithSuccess() throws GesproBusinessException {
		
		GpBill bill = new GpBill();
		GpPhase phs = new GpPhase();
		GpProject prj = new GpProject();
		GpOrganization org = new GpOrganization();
		GpProjectManager pm = new GpProjectManager();
		
		// creation project manager
		pm.setFileNumber("6482");
		pm.setLastname("Jacques");
		pm.setFirstname("CHIRAC");
		pm.setPhoneNumber("0665473956");
		pm.setPassword("myExPresidentPassword");
		pm.setEmail("jacques.chirac@gouv.fr");
		pm.setLogin("jacques.chirac");
		pm = pmService.create(pm);
		this.createdPmId = pm.getId();
		
		// creation organization
		org.setOrgCode("org code");
		org.setName("org name");
		org.setPhoneNumber(0606060606);
		org.setContactName("contact name");
		org.setContactEmail("contact email");
		org.setAdrWeb("org address");
		org = orgService.create(org);
		this.createdOrgId = org.getId();
		
		// creation project
		prj.setProjectCode("projet code");
		prj.setName("Premier Projet");
		prj.setDescription("Notre premier projet en JEE");
		prj.setStartDate(new Date());
		prj.setEndDate(new Date());
		prj.setAmount(0.d);
		prj.setCreationDate(new Date());
		prj.setUpdateDate(new Date());
		prj.setGpOrganization(org);
		prj.setGpChefProjet(pm);
		prj = prjService.create(prj);
		this.createdPrjId = prj.getId();
		
		// creation phase
		byte byteExample = 1;
		phs.setPhaseCode("");
		phs.setDescription("");
		phs.setStartDate(new Date());
		phs.setEndDate(new Date());
		phs.setAmount(0.d);
		phs.setStatus(byteExample);
		phs.setIsEnded(byteExample);
		phs.setCreationDate(new Date());
		phs.setUpdateDate(new Date());
		phs.setGpProject(prj);
		phs = phsService.create(phs);
		this.createdPhsId = phs.getId();
		Assert.assertNotNull(phs.getId());
		
		// creation bill
		Assert.assertNull(bill.getId());
		
		bill.setAmount(0.d);
		bill.setBillCode("bill code");
		bill.setBillStatus("EDITED");
		bill.setGpPhase(phs);
		bill = billService.create(bill);
		this.createdBillId = bill.getId();
		Assert.assertNotNull(bill.getId());
		
	}

	@Test
	public void testFindAllBillWithSuccess() {
		
		// Given

		// When
		List<GpBill> bills = this.billService.findAll();
		
		// Then
		Assert.assertTrue(bills.size() > 0);
		
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer billId = this.billIdForAllTest;

		// When
		GpBill bill = this.billService.findById(billId);
		
		// Then
		Assert.assertNotNull(bill);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer billId = this.billIdForAllTest;
		
		// When
		this.billService.deleteById(billId);
		GpBill bill = this.billService.findById(billId);
		
		// Then
		Assert.assertNull(bill);
		
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {// TODO : Les tests marchent mais ils me génèrent des erreurs
		
		GpBill bill = new GpBill();
		GpPhase phs = new GpPhase();
		GpProject prj = new GpProject();
		GpOrganization org = new GpOrganization();
		GpProjectManager pm = new GpProjectManager();
		
		// creation project manager
		pm.setFileNumber("8647");
		pm.setLastname("Emmanuel");
		pm.setFirstname("MACRON");
		pm.setPhoneNumber("0623509493");
		pm.setPassword("myActualPresidentPassword");
		pm.setEmail("emmanuel.macron@gouv.fr");
		pm.setLogin("emmanuel.macron");
		pm = pmService.create(pm);
		this.pmIdForAllTest = pm.getId();
		Assert.assertNotNull(pm.getId());
		
		// creation organization
		org.setOrgCode("org code");
		org.setName("org name");
		org.setPhoneNumber(0606060606);
		org.setContactName("contact name");
		org.setContactEmail("contact email");
		org.setAdrWeb("org address");
		org = orgService.create(org);
		this.orgIdForAllTest = org.getId();
		Assert.assertNotNull(org.getId());
		
		// creation project
		prj.setProjectCode("projet code");
		prj.setName("Premier Projet");
		prj.setDescription("Notre premier projet en JEE");
		prj.setStartDate(new Date());
		prj.setEndDate(new Date());
		prj.setAmount(0.d);
		prj.setCreationDate(new Date());
		prj.setUpdateDate(new Date());
		prj.setGpOrganization(org);
		prj.setGpChefProjet(pm);
		prj = prjService.create(prj);
		this.prjIdForAllTest = prj.getId();
		Assert.assertNotNull(pm.getId());
		
		// creation phase
		byte byteExample = 1;
		phs.setPhaseCode("phase code");
		phs.setDescription("phase description");
		phs.setStartDate(new Date());
		phs.setEndDate(new Date());
		phs.setAmount(0.d);
		phs.setStatus(byteExample);
		phs.setIsEnded(byteExample);
		phs.setCreationDate(new Date());
		phs.setUpdateDate(new Date());
		phs.setGpProject(prj);
		phs = phsService.create(phs);
		this.phsIdForAllTest = phs.getId();
		Assert.assertNotNull(phs.getId());
		
		// creation bill
		Assert.assertNull(bill.getId());
		bill.setAmount(0.d);
		bill.setBillCode("bill code[before test]");
		bill.setBillStatus("EDITED");
		bill.setGpPhase(phs);
		bill = billService.create(bill);
		this.billIdForAllTest = bill.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.billService.deleteById(this.billIdForAllTest);
		this.phsService.deleteById(this.phsIdForAllTest);
		this.prjService.deleteById(this.prjIdForAllTest);
		this.orgService.deleteById(this.orgIdForAllTest);
		this.pmService.deleteById(this.pmIdForAllTest);
		
		if(!Objects.isNull(this.createdBillId)) {
			
			this.billService.deleteById(this.createdBillId);
			
		}
		
		if(!Objects.isNull(this.createdPhsId)) {
			
			this.phsService.deleteById(this.createdPhsId);
			
		}
		
		if(!Objects.isNull(this.createdPrjId)) {
			
			this.prjService.deleteById(this.createdPrjId);
			
		}
		
		if(!Objects.isNull(this.createdOrgId)) {
			
			this.orgService.deleteById(this.createdOrgId);
			
		}
		
		if(!Objects.isNull(this.createdPmId)) {
			
			this.pmService.deleteById(this.createdPmId);
			
		}
		
	}

}
