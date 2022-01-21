package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpBillDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public class GpBillDAOTest {

	private GpBillDAOImpl billDAO = new GpBillDAOImpl();
	private GpPhaseDAOImpl phsDAO = new GpPhaseDAOImpl();
	private GpProjectDAOImpl prjDAO = new GpProjectDAOImpl();
	private GpOrganizationDAOImpl orgDAO = new GpOrganizationDAOImpl();
	private GpProjectManagerDAOImpl pmDAO = new GpProjectManagerDAOImpl();
	
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
	public void testCreateBillWithSuccess() {
		
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
		pm = pmDAO.create(pm);
		this.createdPmId = pm.getId();
		
		// creation organization
		org.setOrgCode("org code");
		org.setName("org name");
		org.setPhoneNumber(0606060606);
		org.setContactName("contact name");
		org.setContactEmail("contact email");
		org.setAdrWeb("org address");
		org = orgDAO.create(org);
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
		prj = prjDAO.create(prj);
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
		phs = phsDAO.create(phs);
		this.createdPhsId = phs.getId();
		Assert.assertNotNull(phs.getId());
		
		// creation bill
		Assert.assertNull(bill.getId());
		
		bill.setAmount(0.d);
		bill.setBillCode("bill code");
		bill.setBillStatus("EDITED");
		bill.setGpPhase(phs);
		bill = billDAO.create(bill);
		this.createdBillId = bill.getId();
		Assert.assertNotNull(bill.getId());
		
	}

	@Test
	public void testFindAllBillWithSuccess() {
		
		// Given

		// When
		List<GpBill> bills = this.billDAO.findAll();
		
		// Then
		Assert.assertTrue(bills.size() > 0);
		
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer billId = this.billIdForAllTest;

		// When
		GpBill bill = this.billDAO.findById(billId);
		
		// Then
		Assert.assertNotNull(bill);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer billId = this.billIdForAllTest;
		
		// When
		this.billDAO.deleteById(billId);
		GpBill bill = this.billDAO.findById(billId);
		
		// Then
		Assert.assertNull(bill);
		
	}
	
	@Before
	public void prepareAllEntityBefore() {// TODO : Les tests marchent mais ils me génèrent des erreurs
		
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
		pm = pmDAO.create(pm);
		this.pmIdForAllTest = pm.getId();
		Assert.assertNotNull(pm.getId());
		
		// creation organization
		org.setOrgCode("org code");
		org.setName("org name");
		org.setPhoneNumber(0606060606);
		org.setContactName("contact name");
		org.setContactEmail("contact email");
		org.setAdrWeb("org address");
		org = orgDAO.create(org);
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
		prj = prjDAO.create(prj);
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
		phs = phsDAO.create(phs);
		this.phsIdForAllTest = phs.getId();
		Assert.assertNotNull(phs.getId());
		
		// creation bill
		Assert.assertNull(bill.getId());
		bill.setAmount(0.d);
		bill.setBillCode("bill code[before test]");
		bill.setBillStatus("EDITED");
		bill.setGpPhase(phs);
		bill = billDAO.create(bill);
		this.billIdForAllTest = bill.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.billDAO.deleteById(this.billIdForAllTest);
		this.phsDAO.deleteById(this.phsIdForAllTest);
		this.prjDAO.deleteById(this.prjIdForAllTest);
		this.orgDAO.deleteById(this.orgIdForAllTest);
		this.pmDAO.deleteById(this.pmIdForAllTest);
		
		if(!Objects.isNull(this.createdBillId)) {
			
			this.billDAO.deleteById(this.createdBillId);
			
		}
		
		if(!Objects.isNull(this.createdPhsId)) {
			
			this.phsDAO.deleteById(this.createdPhsId);
			
		}
		
		if(!Objects.isNull(this.createdPrjId)) {
			
			this.prjDAO.deleteById(this.createdPrjId);
			
		}
		
		if(!Objects.isNull(this.createdOrgId)) {
			
			this.orgDAO.deleteById(this.createdOrgId);
			
		}
		
		if(!Objects.isNull(this.createdPmId)) {
			
			this.pmDAO.deleteById(this.createdPmId);
			
		}
		
	}

}
