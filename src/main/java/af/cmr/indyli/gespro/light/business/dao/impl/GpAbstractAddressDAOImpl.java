package af.cmr.indyli.gespro.light.business.dao.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.entity.IEntity;

public abstract class GpAbstractAddressDAOImpl<Entity extends IEntity> implements IGpAddressDAO {

	private GpEntityManager entityManager = new GpEntityManager();


	public GpAddress create(GpAddress addr) {
		return null;
	}

	public void update(GpAddress addr) {
		
	}

	public List<GpAddress> findAll() {
		return null;
	}

	public void deleteById(Integer addrId) {
		
		String REQ_SQL = "DELETE FROM gp_address WHERE ADDRESS_ID = ?";
    	Object[] tabParam = {addrId};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
    	
    	//On supprime ensuite dans la table mere
    	String REQ_SQL_ADDR = "DELETE FROM gp_address WHERE ADDRESS_ID = ?";
    	
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL_ADDR, tabParam);
    	
	}

	public GpAddress findById(Integer empId) {
		return null;
	}

	public GpEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(GpEntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
