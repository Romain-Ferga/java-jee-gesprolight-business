package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.Date;

import org.junit.Assert;
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
		
		Integer phsId = 1; // à définir dans le before
		GpPhase phs = new GpPhaseDAOImpl().findById(phsId);
		
		Assert.assertNull(dlvb.getId());
		
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

}
