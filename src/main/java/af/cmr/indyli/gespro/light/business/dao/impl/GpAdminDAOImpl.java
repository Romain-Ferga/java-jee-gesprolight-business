package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAdminDAO;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.entity.GpAdmin;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;

public class GpAdminDAOImpl extends GpAbstractAdminDAOImpl<GpAdmin> implements IGpAdminDAO<GpAdmin> {
	
	GpEntityManager entityManager = new GpEntityManager();
	
	@Override
	public GpAdmin create(GpAdmin acc) {
		
		try {
			
			//On demarre une transaction
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			
			// On enregistre dans la table employé
			acc = super.create(acc);
			
			// On enregistre l'id dans la table comptable
			String REQ_SQL = "INSERT INTO GP_ACCOUNTANT (EMP_ID) VALUES (?)";
	    	Object[] tabParam = {acc.getId()};
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return acc;
		
	}
	
	@Override
	public void update(GpAdmin acc) {
		
		super.update(acc);
		
	}
	
	@Override
	public List<GpAdmin> findAll() {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE";
		ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
		List<GpAdmin> accList = new ArrayList<GpAdmin>();
		
		if (resultat != null) {
			
	        try {
	        	
				while (resultat.next()) {
					
					Integer accId = resultat.getInt("EMP_ID");
					String fileNumber = resultat.getString("FILE_NUMBER");
					String lastname = resultat.getString("LASTNAME");
					String firstname = resultat.getString("FIRSTNAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String password = resultat.getString("PASSWORD");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String email = resultat.getString("EMAIL");
					String login = resultat.getString("LOGIN");
					
					GpAdmin foundEmp = new GpAdmin();
					
					foundEmp.setId(accId);
					foundEmp.setFileNumber(fileNumber);
					foundEmp.setLastname(lastname);
					foundEmp.setFirstname(firstname);
					foundEmp.setCreationDate(creationDate);
					foundEmp.setPassword(password);
					foundEmp.setPhoneNumber(phoneNumber);
					foundEmp.setEmail(email);
					foundEmp.setLogin(login);
					
					accList.add(foundEmp);
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
	    }
		
		return accList;
		
	}
	
	@Override
	public GpAdmin findById(Integer accId) {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE WHERE EMP_ID = ?";
		Object[] tabParam = {accId};
		ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
		GpAdmin foundAdm = null;
		
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
					
					foundAdm = new GpAdmin();
					
					foundAdm.setId(accId);
					foundAdm.setFileNumber(fileNumber);
					foundAdm.setLastname(lastname);
					foundAdm.setFirstname(firstname);
					foundAdm.setCreationDate(creationDate);
					foundAdm.setPassword(password);
					foundAdm.setPhoneNumber(phoneNumber);
					foundAdm.setEmail(email);
					foundAdm.setLogin(login);
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
	        
	    }
		
		return foundAdm;
	}
	
	@Override
	public String getCurrentTableName() {
		return GpAccountant.GP_ACCOUNTANT_TABLE_NAME;
	}
	
}
