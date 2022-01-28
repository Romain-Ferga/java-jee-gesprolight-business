package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpDirectorDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpDirectorDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDirector;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;

public class GpDirectorServiceImpl implements IGpEmployeeService<GpDirector> {

	private IGpDirectorDAO dirDAO = new GpDirectorDAOImpl();

	public GpDirector create(GpDirector dir) throws GesproBusinessException {
		
		if (this.dirDAO.ifEmpExistByFileNumberOrEmail(dir.getFileNumber(), dir.getEmail(), dir.getLogin())) {
			
			throw new GesproBusinessException(String.format("Un Directeur existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",dir.getEmail(),dir.getLogin(),dir.getFileNumber()));
		
		}
		
		return this.dirDAO.create(dir);
		
	}

	public void update(GpDirector dir) throws GesproBusinessException {
		// TODO Auto-generated method stub
		
	}

	public List<GpDirector> findAll() {

		return this.dirDAO.findAll();
		
	}

	public void deleteById(Integer dirId) {

		this.dirDAO.deleteById(dirId);
		
	}

	public GpDirector findById(Integer dirId) {

		return this.dirDAO.findById(dirId);
		
	}
	
}
