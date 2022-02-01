package af.cmr.indyli.gespro.light.business.service.test;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.impl.GpDeliverableServiceImpl;

public class GpDeliverableServiceTest {

	private GpDeliverableServiceImpl dlvbService = new GpDeliverableServiceImpl();
	private Integer dlvbIdForAllTest = null;
	private Integer createDlvbId = null;
	
	@Test
	public void testCreateDeliverableWithSuccess() throws GesproBusinessException {
		
		// Given
		GpDeliverable dlvb = new GpDeliverable();
		Assert.assertNull(dlvb.getId());
		
		Integer phsId = 1; // à définir dans le before
		GpPhase phs = new GpPhaseDAOImpl().findById(phsId);
		Assert.assertTrue(phs.getId() == phsId);
		
		dlvb.setDelCode("dlvb code");
		dlvb.setLabel("dlvb label");
		dlvb.setDescription("dlvb description");
		dlvb.setDelPath("dlvb path");
		dlvb.setCreationDate(new Date());
		dlvb.setGpPhase(phs);
		
		// When
		dlvb = dlvbService.create(dlvb);
		
		//On le sauvegarde pour le supprimer apres
		this.createDlvbId = dlvb.getId();
		
		// Then
		Assert.assertNotNull(dlvb.getId());
		
	}

	@Test
	public void testFindAllDeliverableWithSuccess() {
		
		// Given

		// When
		List<GpDeliverable> dlvbs = this.dlvbService.findAll();
		
		// Then
		Assert.assertTrue(dlvbs.size() > 0);
		
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer dlvbId = this.dlvbIdForAllTest;

		// When
		GpDeliverable dlvb = this.dlvbService.findById(dlvbId);
		
		// Then
		Assert.assertNotNull(dlvb);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer dlvbId = this.dlvbIdForAllTest;
		
		// When
		this.dlvbService.deleteById(dlvbId);
		GpDeliverable dlvb = this.dlvbService.findById(dlvbId);
		
		// Then
		Assert.assertNull(dlvb);
		
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		
		GpDeliverable dlvb = new GpDeliverable();
		Assert.assertNull(dlvb.getId());
		
		Integer phsId = 1;
		GpPhase phs = new GpPhaseDAOImpl().findById(phsId);
		Assert.assertTrue(phs.getId() == phsId);
		
		dlvb.setDelCode("dlvb code [before test]");
		dlvb.setLabel("dlvb label [before test]");
		dlvb.setDescription("dlvb description [before test]");
		dlvb.setDelPath("dlvb path [before test]");
		dlvb.setCreationDate(new Date());
		dlvb.setGpPhase(phs);
		
		dlvb = dlvbService.create(dlvb);
		
		this.dlvbIdForAllTest = dlvb.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.dlvbService.deleteById(this.dlvbIdForAllTest);
		
		if(!Objects.isNull(this.createDlvbId)) {
			
			this.dlvbService.deleteById(this.createDlvbId);
			
		}
		
	}

}
