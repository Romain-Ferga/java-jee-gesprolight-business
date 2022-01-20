package af.cmr.indyli.gespro.light.business.dao.test;

import org.junit.Assert;
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
		
		// TODO 1: Instancier une phase
		Integer phsId = 1; // à définir dans le before
		GpPhase phs = new GpPhaseDAOImpl().findById(phsId);
		
		Assert.assertNull(bill.getId());
		
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

}
