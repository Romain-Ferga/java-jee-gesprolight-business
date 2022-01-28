package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmpReaPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmpReaPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmpReaPhaseService;

public class GpEmpReaPhaseServiceImpl implements IGpEmpReaPhaseService {
	
	private IGpEmpReaPhaseDAO erpDAO = new GpEmpReaPhaseDAOImpl();

	@Override
	public GpEmpReaPhase create(GpEmpReaPhase erp) throws GesproBusinessException {

		return this.erpDAO.create(erp);
		
	}

	@Override
	public void update(GpEmpReaPhase erp) throws GesproBusinessException {

		this.erpDAO.update(erp);
		
	}

	@Override
	public List<GpEmpReaPhase> findAll() {

		return this.erpDAO.findAll();
		
	}

	@Override
	public void deleteById(Integer erpId) {

		this.erpDAO.deleteById(erpId);
		
	}

	@Override
	public GpEmpReaPhase findById(Integer erpId) {

		return this.erpDAO.findById(erpId);
		
	}

}
