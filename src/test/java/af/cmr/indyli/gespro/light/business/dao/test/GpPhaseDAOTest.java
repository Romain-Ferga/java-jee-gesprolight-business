package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;

public class GpPhaseDAOTest {

	private GpPhaseDAOImpl phsDAO = new GpPhaseDAOImpl();
	private Integer phsIdForAllTest = null;
	private Integer createPhsId = null;
	
	@Test
	public void testGpPhaseWithSuccess() {
		
		// Given
		GpPhase phs = new GpPhase();
		
		Integer prjId = 1;
		GpProject project = new GpProjectDAOImpl().findById(prjId);
		
		Assert.assertNull(phs.getId());
		
		phs.setPhaseCode("phs code");
		phs.setDescription("phs description");
		phs.setStartDate(new Date());
		phs.setEndDate(new Date());
		phs.setAmount(0.d);
		phs.setStatus((byte) 0); 
		phs.setIsEnded((byte) 0);
		phs.setCreationDate(new Date());
		phs.setUpdateDate(new Date());
		phs.setGpProject(project);
		
		
		// When
		phs = phsDAO.create(phs);
		//On le sauvegarde pour le supprimer apres
		this.createPhsId = phs.getId();
		// Then
		Assert.assertNotNull(phs.getId());
	}

}
