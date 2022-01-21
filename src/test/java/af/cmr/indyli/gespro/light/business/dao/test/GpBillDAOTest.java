package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpBillDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public class GpBillDAOTest {

	private GpBillDAOImpl billDAO = new GpBillDAOImpl();
	private Integer billIdForAllTest = null;
	private Integer createBillId = null;
	
	@Test
	public void testCreateBillWithSuccess() {
		
		// Given
		GpBill bill = new GpBill();
		Assert.assertNull(bill.getId());
		
		// TODO 1: Instancier une phase
		Integer phsId = 1; // à définir dans le before
		GpPhase phs = new GpPhaseDAOImpl().findById(phsId);
		Assert.assertTrue(phs.getId() == phsId);
		
		bill.setAmount(0.d);
		bill.setBillCode("bill code");
		bill.setBillStatus("bill status");
		bill.setGpPhase(phs);

		// When
		bill = billDAO.create(bill);
		
		//On le sauvegarde pour le supprimer apres
		this.createBillId = bill.getId();
		
		// Then
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
	public void prepareAllEntityBefore() {
		//TODO 3: A quoi doit ressembler ma fonction before ?
		
		GpBill bill = new GpBill();
		
		Assert.assertNull(bill.getId());
		
		Integer phsId = 1; 
		GpPhase phs = new GpPhaseDAOImpl().findById(phsId);
		
		Assert.assertTrue(bill.getId() == phsId);
		
		bill.setAmount(0.d);
		bill.setBillCode("bill code[before test]");
		bill.setBillStatus("bill status[before test]");
		bill.setGpPhase(phs);
		
		bill = billDAO.create(bill);
		
		this.billIdForAllTest = bill.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.billDAO.deleteById(this.billIdForAllTest);
		
		if(!Objects.isNull(this.createBillId)) {
			
			this.billDAO.deleteById(this.createBillId);
			
		}
		
	}

}
