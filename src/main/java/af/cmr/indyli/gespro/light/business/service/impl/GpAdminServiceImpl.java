package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAdminDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAdminDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAdmin;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;

public class GpAdminServiceImpl implements IGpEmployeeService<GpAdmin> {

	private IGpAdminDAO admDAO = new GpAdminDAOImpl();

	public GpAdmin create(GpAdmin adm) throws GesproBusinessException {
		
		if (this.admDAO.ifEmpExistByFileNumberOrEmail(adm.getFileNumber(), adm.getEmail(), adm.getLogin())) {
			
			throw new GesproBusinessException(String.format("Un Admin existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",adm.getEmail(),adm.getLogin(),adm.getFileNumber()));
		
		}
		
		return this.admDAO.create(adm);
		
	}

	public void update(GpAdmin adm) throws GesproBusinessException {

		this.admDAO.update(adm);
		
	}

	public List<GpAdmin> findAll() {

		return this.admDAO.findAll();
		
	}

	public void deleteById(Integer admId) {

		this.admDAO.deleteById(admId);
		
	}

	public GpAdmin findById(Integer admId) {

		return this.admDAO.findById(admId);
		
	}
	
}
