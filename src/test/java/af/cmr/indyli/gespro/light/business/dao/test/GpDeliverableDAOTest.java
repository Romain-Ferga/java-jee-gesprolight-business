package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpDeliverableDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public class GpDeliverableDAOTest {

	private GpDeliverableDAOImpl dlvbDAO = new GpDeliverableDAOImpl();
	private Integer dlvbIdForAllTest = null;
	private Integer createDlvbId = null;
	
	@Test
	public void testCreateDeliverableWithSuccess() {
		
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
		dlvb.setCreationDate(new Date()); //TODO 2: Est-ce que ca fonctionne ?
		dlvb.setGpPhase(phs);
		
		// When
		dlvb = dlvbDAO.create(dlvb);
		
		//On le sauvegarde pour le supprimer apres
		this.createDlvbId = dlvb.getId();
		
		// Then
		Assert.assertNotNull(dlvb.getId());
		
	}

	@Test
	public void testFindAllDeliverableWithSuccess() {
		
		// Given

		// When
		List<GpDeliverable> dlvbs = this.dlvbDAO.findAll();
		
		// Then
		Assert.assertTrue(dlvbs.size() > 0);
		
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer dlvbId = this.dlvbIdForAllTest;

		// When
		GpDeliverable dlvb = this.dlvbDAO.findById(dlvbId);
		
		// Then
		Assert.assertNotNull(dlvb);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer dlvbId = this.dlvbIdForAllTest;
		
		// When
		this.dlvbDAO.deleteById(dlvbId);
		GpDeliverable dlvb = this.dlvbDAO.findById(dlvbId);
		
		// Then
		Assert.assertNull(dlvb);
		
	}
	
	@Before
	public void prepareAllEntityBefore() {
		
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
		
		dlvb = dlvbDAO.create(dlvb);
		
		this.dlvbIdForAllTest = dlvb.getId();
		
	}
	
	@After
	public void deleteAllEntityAfter() {
		
		this.dlvbDAO.deleteById(this.dlvbIdForAllTest);
		
		if(!Objects.isNull(this.createDlvbId)) {
			
			this.dlvbDAO.deleteById(this.createDlvbId);
			
		}
		
	}

}
