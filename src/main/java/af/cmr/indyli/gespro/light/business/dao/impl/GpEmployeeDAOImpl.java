package af.cmr.indyli.gespro.light.business.dao.impl;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;

public class GpEmployeeDAOImpl extends GpAbstractEmployeeDAOImpl<GpEmployee> implements IGpEmployeeDAO<GpEmployee>{
	
	@Override
	public String getCurrentTableName() {
		
		return GpEmployee.GP_EMPLOYEE_TABLE_NAME;
		
	}
	
}
