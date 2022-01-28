package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpDeliverableDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpDeliverableDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpDeliverableService;

public class GpDeliverableServiceImpl implements IGpDeliverableService {
	
	private IGpDeliverableDAO dlvbDAO = new GpDeliverableDAOImpl();

	@Override
	public GpDeliverable create(GpDeliverable dlvb) throws GesproBusinessException {

		if(this.dlvbDAO.ifDlvbExistByDlvbCode(dlvb.getDelCode())) {
			
			throw new GesproBusinessException(String.format("Un Livrable existe deja avec ce code[%s]", dlvb.getDelCode()));
			
		}
		
		return this.dlvbDAO.create(dlvb);
		
	}

	@Override
	public void update(GpDeliverable dlvb) throws GesproBusinessException {

		this.dlvbDAO.update(dlvb);
		
	}

	@Override
	public List<GpDeliverable> findAll() {

		return this.dlvbDAO.findAll();
		
	}

	@Override
	public void deleteById(Integer dlvbId) {

		this.dlvbDAO.deleteById(dlvbId);
		
	}

	@Override
	public GpDeliverable findById(Integer dlvbId) {

		return this.dlvbDAO.findById(dlvbId);
		
	}

}
