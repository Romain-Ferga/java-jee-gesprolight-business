package af.cmr.indyli.gespro.light.business.dao.impl;

import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.IEntity;

public abstract class GpAbstractEmployeeDAOImpl<Entity extends IEntity> implements IGpEmployeeDAO<Entity>{

	private GpEntityManager entityManager = new GpEntityManager();


	public Entity create(Entity emp) {
		
		String REQ_SQL = "INSERT INTO GP_EMPLOYEE ( FILE_NUMBER,LASTNAME,FIRSTNAME,PHONE_NUMBER,PASSWORD,CREATION_DATE,EMAIL,LOGIN) VALUES (?,?,?,?,?,?,?,?)";
    	Object[] tabParam = {emp.getFileNumber(),emp.getLastname(),emp.getFirstname(),emp.getPhoneNumber(),emp.getPassword(),new Date(),emp.getEmail(),emp.getLogin()};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
    	Integer empId = getEntityManager().findIdByAnyColumn("GP_EMPLOYEE", "EMAIL", emp.getEmail(), "EMP_ID");
    	emp.setId(empId);
    	return emp;
	
	}

	public void update(Entity emp) {
		
	}

	public List<Entity> findAll() {
		return null;
	}

	public void deleteById(Integer empId) {
		String REQ_SQL = "DELETE FROM "+this.getCurrentTableName()+" WHERE EMP_ID = ?";
    	Object[] tabParam = {empId};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
    	//On supprime ensuite dans la table mere
    	if (!this.getCurrentTableName().equals(GpEmployee.GP_EMPLOYEE_TABLE_NAME)) {
    		String REQ_SQL_EMP = "DELETE FROM "+ GpEmployee.GP_EMPLOYEE_TABLE_NAME +" WHERE EMP_ID = ?";
        	this.getEntityManager().updateAvecParamGenerique(REQ_SQL_EMP, tabParam);
    	}
    	
	}

	public Entity findById(Integer empId) {
		return null;
	}

	public boolean ifEmpExistByFileNumberOrEmail(String fileNumber, String email,String login) {
		Integer empIdForEmail = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "EMAIL", email, "EMP_ID");
		Integer empIdForFileNumber = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "FILE_NUMBER", fileNumber, "EMP_ID");
		Integer empIdForLogin = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "LOGIN", fileNumber, "EMP_ID");
		return empIdForEmail != null || empIdForFileNumber != null || empIdForLogin != null;
	}

	public GpEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(GpEntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public abstract String getCurrentTableName();

}
