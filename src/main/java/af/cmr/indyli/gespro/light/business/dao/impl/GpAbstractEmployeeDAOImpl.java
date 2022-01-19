package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.IEntity;

public abstract class GpAbstractEmployeeDAOImpl<Entity extends IEntity> implements IGpEmployeeDAO<Entity>{

	private GpEntityManager entityManager = new GpEntityManager();

	public Entity create(Entity empEntity) {
		
		String REQ_SQL = "INSERT INTO GP_EMPLOYEE ( FILE_NUMBER,LASTNAME,FIRSTNAME,PHONE_NUMBER,PASSWORD,CREATION_DATE,EMAIL,LOGIN) VALUES (?,?,?,?,?,?,?,?)";

		GpEmployee emp = (GpEmployee)empEntity; 
		
		Object[] tabParam = {emp.getFileNumber(),emp.getLastname(),emp.getFirstname(),emp.getPhoneNumber(),emp.getPassword(),new Date(),emp.getEmail(),emp.getLogin()};
    	
		this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
    	
		Integer empId = getEntityManager().findIdByAnyColumn("GP_EMPLOYEE", "EMAIL", emp.getEmail(), "EMP_ID");
    	
		emp.setId(empId);
    	
		return empEntity;
	
	}

	public void update(Entity empEntity) {
		
		String REQ_SQL = "UPDATE FROM GP_EMPLOYEE SET LASTNAME=? , FIRSTNAME=? , PHONE_NUMBER=? ,PASSWORD = ? ,EMAIL=? ,LOGIN=?     WHERE EMP_ID = ?";
    	
		GpEmployee emp = (GpEmployee)empEntity; 
		
		Object[] tabParam = {emp.getLastname(),emp.getFirstname(),emp.getPhoneNumber(),emp.getPassword(),emp.getEmail(),emp.getLogin(),emp.getId()};
    	
		this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@SuppressWarnings("unchecked")
	public List<Entity> findAll() {
		
		  String REQ_SQL = "SELECT * FROM GP_EMPLOYEE";
		  
		  ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
		  
		  List<Entity> empList = new ArrayList<Entity>();
		  
		  if (resultat != null) {
		  
			  try {

				  while (resultat.next()) {

					  Integer empId = resultat.getInt("EMP_ID");
					  String fileNumber = resultat.getString("FILE_NUMBER");
					  String lastname = resultat.getString("LASTNAME"); 
					  String firstname = resultat.getString("FIRSTNAME"); 
					  String phoneNumber = resultat.getString("PHONE_NUMBER"); 
					  String password = resultat.getString("PASSWORD"); 
					  Date creationDate = resultat.getDate("CREATION_DATE"); 
					  String email = resultat.getString("EMAIL"); 
					  String login = resultat.getString("LOGIN");

					  GpEmployee foundEmp = new GpEmployee();

					  foundEmp.setId(empId); 
					  foundEmp.setFileNumber(fileNumber);
					  foundEmp.setLastname(lastname); 
					  foundEmp.setFirstname(firstname);
					  foundEmp.setCreationDate(creationDate); 
					  foundEmp.setPassword(password);
					  foundEmp.setPhoneNumber(phoneNumber); 
					  foundEmp.setEmail(email);
					  foundEmp.setLogin(login);

					  empList.add((Entity)foundEmp);
					  
				  }

				  resultat.close();

			  } catch (SQLException e) {

				  e.printStackTrace();

			  }

		  }

		  return empList;
		 
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

	@SuppressWarnings("unchecked")
	public Entity findById(Integer empId) {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE where EMP_ID = ?";

		Object[] tabParam = {empId};

		ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);

		GpEmployee foundEmp = null;

		if (resultat != null) {

			try {

				while (resultat.next()) {

					String fileNumber = resultat.getString("FILE_NUMBER"); 
					String lastname = resultat.getString("LASTNAME"); 
					String firstname = resultat.getString("FIRSTNAME"); 
					String phoneNumber = resultat.getString("PHONE_NUMBER"); 
					String password = resultat.getString("PASSWORD"); 
					Date creationDate = resultat.getDate("CREATION_DATE"); 
					String email = resultat.getString("EMAIL"); 
					String login = resultat.getString("LOGIN");

					foundEmp = new GpEmployee();

					foundEmp.setId(empId); 
					foundEmp.setFileNumber(fileNumber);
					foundEmp.setLastname(lastname); 
					foundEmp.setFirstname(firstname);
					foundEmp.setCreationDate(creationDate); 
					foundEmp.setPassword(password);
					foundEmp.setPhoneNumber(phoneNumber); 
					foundEmp.setEmail(email);
					foundEmp.setLogin(login);

				}

				resultat.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}
			
		}

		return (Entity)foundEmp;
		
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
