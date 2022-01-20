package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpEmpReaPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmployeeDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public class GpEmpReaPhaseDAOTest {

	private GpEmpReaPhaseDAOImpl erpDAO = new GpEmpReaPhaseDAOImpl();
	private Integer erpIdForAllTest = null;
	private Integer createErpId = null;
	
	@Test
	public void testCreateEmpReaPhaseWithSuccess() {
		
		// Given
		GpEmpReaPhase erp = new GpEmpReaPhase();
		
		Integer phsId = 1; // à définir dans le before
		GpPhase phs = new GpPhaseDAOImpl().findById(phsId);
		
		Integer empId = 1;
		GpEmployee emp = new GpEmployeeDAOImpl().findById(empId);
		
		Assert.assertNull(erp.getId());
		
		erp.setCreationDate(new Date());
		erp.setGpPhase(phs);
		erp.setGpEmployee(emp);
		
		// When
		erp = erpDAO.create(erp);
		//On le sauvegarde pour le supprimer apres
		this.createErpId = erp.getId();
		
		// Then
		Assert.assertNotNull(erp.getId());
	}

	@Test
	public void testFindAllEmpReaPhaseWithSuccess() {
		
		// Given

		// When
		List<GpEmpReaPhase> erps = this.erpDAO.findAll();
		
		// Then
		Assert.assertTrue(erps.size() > 0);
		
	}

	@Test
	public void testFindByIdWithSuccess() {
		
		// Given
		Integer erpId = this.erpIdForAllTest;

		// When
		GpEmpReaPhase erp = this.erpDAO.findById(erpId);
		
		// Then
		Assert.assertNotNull(erp);
		
	}

	@Test
	public void testDelete() {
		
		// Given
		Integer erpId = this.erpIdForAllTest;
		
		// When
		this.erpDAO.deleteById(erpId);
		GpEmpReaPhase erp = this.erpDAO.findById(erpId);
		
		// Then
		Assert.assertNull(erp);
		
	}

}
