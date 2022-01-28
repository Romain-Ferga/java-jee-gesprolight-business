package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;

public class GpOrganizationServiceImpl implements IGpOrganizationService {
	
	private IGpOrganizationDAO orgDAO = new GpOrganizationDAOImpl();

	@Override
	public GpOrganization create(GpOrganization org) throws GesproBusinessException {

		if(this.orgDAO.ifOrgExistByOrgCode(org.getOrgCode())) {
			
			throw new GesproBusinessException(String.format("Une Organisation existe deja avec ce code[%s]", org.getOrgCode()));
			
		}
		
		return this.orgDAO.create(org);
		
	}

	@Override
	public void update(GpOrganization org) throws GesproBusinessException {

		this.orgDAO.update(org);
		
	}

	@Override
	public List<GpOrganization> findAll() {

		return this.orgDAO.findAll();
		
	}

	@Override
	public void deleteById(Integer orgId) {

		this.orgDAO.deleteById(orgId);
		
	}

	@Override
	public GpOrganization findById(Integer orgId) {

		return this.orgDAO.findById(orgId);
		
	}

	@Override
	public boolean ifOrgExistByOrgCode(String orgCode) {

		return this.orgDAO.ifOrgExistByOrgCode(orgCode);
		
	}

}
