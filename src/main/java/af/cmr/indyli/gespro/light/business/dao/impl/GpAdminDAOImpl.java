package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAdminDAO;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.entity.GpAdmin;

public class GpAdminDAOImpl extends GpAbstractEmployeeDAOImpl<GpAdmin> implements IGpAdminDAO {
	
	GpEntityManager entityManager = new GpEntityManager();
	
	@Override
	public GpAdmin create(GpAdmin adm) {
		
		try {
			
			//On demarre une transaction
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			
			// On enregistre dans la table employé
			adm = super.create(adm);
			
			// On enregistre l'id dans la table comptable
			String REQ_SQL = "INSERT INTO GP_ADMIN (EMP_ID) VALUES (?)";
			
	    	Object[] tabParam = {adm.getId()};
	    	
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return adm;
		
	}
	
	@Override
	public void update(GpAdmin adm) {
		
		super.update(adm);
		
	}
	
	@Override
	public List<GpAdmin> findAll() {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE AS EMP, GP_ADMIN AS ADM WHERE EMP.EMP_ID = ADM.EMP_ID";
		
		ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
		
		List<GpAdmin> admList = new ArrayList<GpAdmin>();
		
		if (resultat != null) {
			
	        try {
	        	
				while (resultat.next()) {
					
					Integer admId = resultat.getInt("EMP_ID");
					String fileNumber = resultat.getString("FILE_NUMBER");
					String lastname = resultat.getString("LASTNAME");
					String firstname = resultat.getString("FIRSTNAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String password = resultat.getString("PASSWORD");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String email = resultat.getString("EMAIL");
					String login = resultat.getString("LOGIN");
					
					GpAdmin foundAdm = new GpAdmin();
					
					foundAdm.setId(admId);
					foundAdm.setFileNumber(fileNumber);
					foundAdm.setLastname(lastname);
					foundAdm.setFirstname(firstname);
					foundAdm.setCreationDate(creationDate);
					foundAdm.setPassword(password);
					foundAdm.setPhoneNumber(phoneNumber);
					foundAdm.setEmail(email);
					foundAdm.setLogin(login);
					
					admList.add(foundAdm);
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
	    }
		
		return admList;
		
	}
	
	@Override
	public GpAdmin findById(Integer admId) {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE WHERE EMP_ID = ?";
		
		Object[] tabParam = {admId};
		
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
					
					foundAdm.setId(admId);
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
