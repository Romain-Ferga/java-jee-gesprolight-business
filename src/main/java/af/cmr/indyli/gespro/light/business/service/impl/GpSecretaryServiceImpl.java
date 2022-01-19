package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpSecretaryDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpSecretaryDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpSecretary;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;

public class GpSecretaryServiceImpl implements IGpEmployeeService<GpSecretary> {

	private IGpSecretaryDAO secrDAO = new GpSecretaryDAOImpl();

	@Override
	public GpSecretary create(GpSecretary secr) throws GesproBusinessException {
		
		if (this.secrDAO.ifEmpExistByFileNumberOrEmail(secr.getFileNumber(), secr.getEmail(), secr.getLogin())) {
			
			throw new GesproBusinessException(String.format("Une Secretaire existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",secr.getEmail(),secr.getLogin(),secr.getFileNumber()));
		
		}
		
		return this.secrDAO.create(secr);
		
	}

	@Override
	public void update(GpSecretary secr) throws GesproBusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GpSecretary> findAll() {

		return this.secrDAO.findAll();
		
	}

	@Override
	public void deleteById(Integer secrId) {

		this.secrDAO.deleteById(secrId);
		
	}

	@Override
	public GpSecretary findById(Integer secrId) {

		return this.secrDAO.findById(secrId);
		
	}
	
}
