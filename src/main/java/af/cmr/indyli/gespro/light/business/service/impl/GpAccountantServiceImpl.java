package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAccountantDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAccountantDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;

public class GpAccountantServiceImpl implements IGpEmployeeService<GpAccountant> {

	private IGpAccountantDAO accDAO = new GpAccountantDAOImpl();

	public GpAccountant create(GpAccountant acc) throws GesproBusinessException {
		
		if (this.accDAO.ifEmpExistByFileNumberOrEmail(acc.getFileNumber(), acc.getEmail(), acc.getLogin())) {
			
			throw new GesproBusinessException(String.format("Un Comptable existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",acc.getEmail(),acc.getLogin(),acc.getFileNumber()));
		
		}
		
		return this.accDAO.create(acc);
		
	}

	public void update(GpAccountant acc) throws GesproBusinessException {

		this.accDAO.update(acc);
		
	}

	public List<GpAccountant> findAll() {

		return this.accDAO.findAll();
		
	}

	public void deleteById(Integer accId) {

		this.accDAO.deleteById(accId);
		
	}

	public GpAccountant findById(Integer accId) {

		return this.accDAO.findById(accId);
		
	}
	
}
