package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;

public class GpPhaseServiceImpl implements IGpPhaseService {
	
	private IGpPhaseDAO phsDAO = new GpPhaseDAOImpl();

	@Override
	public GpPhase create(GpPhase phs) throws GesproBusinessException {

		if(this.phsDAO.ifPhsExistByPhaseCode(phs.getPhaseCode())) {
			
			throw new GesproBusinessException(String.format("Une Phase existe deja avec le code[%s]", phs.getPhaseCode()));
			
		}
		
		return this.phsDAO.create(phs);
		
	}

	@Override
	public void update(GpPhase phs) throws GesproBusinessException {

		this.phsDAO.update(phs);
		
	}

	@Override
	public List<GpPhase> findAll() {

		return this.phsDAO.findAll();
		
	}

	@Override
	public void deleteById(Integer phsId) {

		this.phsDAO.deleteById(phsId);
		
	}

	@Override
	public GpPhase findById(Integer phsId) {

		return this.phsDAO.findById(phsId);
		
	}

	@Override
	public boolean ifPhsExistByPhaseCode(String phaseCode) {

		return this.phsDAO.ifPhsExistByPhaseCode(phaseCode);
		
	}

}
