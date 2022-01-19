package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;

public class GpProjectManagerServiceImpl implements IGpEmployeeService<GpProjectManager> {

	private IGpProjectManagerDAO pmDAO = new GpProjectManagerDAOImpl();

	@Override
	public GpProjectManager create(GpProjectManager pm) throws GesproBusinessException {
		
		if (this.pmDAO.ifEmpExistByFileNumberOrEmail(pm.getFileNumber(), pm.getEmail(), pm.getLogin())) {
			
			throw new GesproBusinessException(String.format("Un comptable existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",pm.getEmail(),pm.getLogin(),pm.getFileNumber()));
		
		}
		
		return this.pmDAO.create(pm);
		
	}

	@Override
	public void update(GpProjectManager pm) throws GesproBusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GpProjectManager> findAll() {

		return this.pmDAO.findAll();
		
	}

	@Override
	public void deleteById(Integer pmId) {

		this.pmDAO.deleteById(pmId);
		
	}

	@Override
	public GpProjectManager findById(Integer pmId) {

		return this.pmDAO.findById(pmId);
		
	}
	
}
