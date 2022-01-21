package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpSecretaryDAO;
import af.cmr.indyli.gespro.light.business.entity.GpSecretary;

public class GpSecretaryDAOImpl extends GpAbstractEmployeeDAOImpl<GpSecretary> implements IGpSecretaryDAO {
	
	@Override
	public GpSecretary create(GpSecretary secr) {
		
		try {
			
			//On demarre une transaction
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			
			// On enregistre dans la table employé
			secr = super.create(secr);
			
			// On enregistre l'id dans la table comptable
			String REQ_SQL = "INSERT INTO GP_SECRETARY (EMP_ID) VALUES (?)";
			
	    	Object[] tabParam = {secr.getId()};
	    	
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
    	
    	return secr;
    	
	}

	@Override
	public void update(GpSecretary secr) {
		
		super.update(secr);
    	
	}

	@Override
	public List<GpSecretary> findAll() {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE AS EMP, GP_SECRETARY AS SECR WHERE EMP.EMP_ID = SECR.EMP_ID";
		
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	
    	List<GpSecretary> secrList = new ArrayList<GpSecretary>();
    	
    	if (resultat != null) {
    		
            try {
            	
				while (resultat.next()) {
					
					Integer secrId = resultat.getInt("EMP_ID");
					String fileNumber = resultat.getString("FILE_NUMBER");
					String lastname = resultat.getString("LASTNAME");
					String firstname = resultat.getString("FIRSTNAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String password = resultat.getString("PASSWORD");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String email = resultat.getString("EMAIL");
					String login = resultat.getString("LOGIN");
					
					GpSecretary foundSecr = new GpSecretary();
					
					foundSecr.setId(secrId);
					foundSecr.setFileNumber(fileNumber);
					foundSecr.setLastname(lastname);
					foundSecr.setFirstname(firstname);
					foundSecr.setCreationDate(creationDate);
					foundSecr.setPassword(password);
					foundSecr.setPhoneNumber(phoneNumber);
					foundSecr.setEmail(email);
					foundSecr.setLogin(login);
					
					secrList.add(foundSecr);
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
        }
    	
		return secrList;
		
	}

	@Override
	public GpSecretary findById(Integer secrId) {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE WHERE EMP_ID = ?";
		
		Object[] tabParam = {secrId};
		
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	
    	GpSecretary foundSecr = null;
    	
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
					
					foundSecr = new GpSecretary();
					
					foundSecr.setId(secrId);
					foundSecr.setFileNumber(fileNumber);
					foundSecr.setLastname(lastname);
					foundSecr.setFirstname(firstname);
					foundSecr.setCreationDate(creationDate);
					foundSecr.setPassword(password);
					foundSecr.setPhoneNumber(phoneNumber);
					foundSecr.setEmail(email);
					foundSecr.setLogin(login);
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
            
        }
    	
		return foundSecr;
	}

	@Override
	public String getCurrentTableName() {
		
		return GpSecretary.GP_SECRETARY_TABLE_NAME;
		
	}

}
