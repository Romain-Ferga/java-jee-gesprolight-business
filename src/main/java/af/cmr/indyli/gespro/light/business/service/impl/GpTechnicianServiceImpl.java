package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpTechnicianDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpTechnicianDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.entity.GpTechnician;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;

public class GpTechnicianServiceImpl implements IGpEmployeeService<GpTechnician> {

	private IGpTechnicianDAO techDAO = new GpTechnicianDAOImpl();

	@Override
	public GpTechnician create(GpTechnician tech) throws GesproBusinessException {
		
		if (this.techDAO.ifEmpExistByFileNumberOrEmail(tech.getFileNumber(), tech.getEmail(), tech.getLogin())) {
			
			throw new GesproBusinessException(String.format("Un Technicien existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",tech.getEmail(),tech.getLogin(),tech.getFileNumber()));
		
		}
		
		return this.techDAO.create(tech);
		
	}

	@Override
	public void update(GpTechnician tech) throws GesproBusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GpTechnician> findAll() {

		return this.techDAO.findAll();
		
	}

	@Override
	public void deleteById(Integer techId) {

		this.techDAO.deleteById(techId);
		
	}

	@Override
	public GpTechnician findById(Integer techId) {

		return this.techDAO.findById(techId);
		
	}
	
}
