package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAccountantDAO;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;

public class GpAccountantDAOImpl extends GpAbstractEmployeeDAOImpl<GpAccountant> implements IGpAccountantDAO {
	
	@Override
	public GpAccountant create(GpAccountant acc) {
		
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
	public void update(GpAccountant acc) {
		
		super.update(acc);
    	
	}

	@Override
	public List<GpAccountant> findAll() {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE";
		
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	
    	List<GpAccountant> accList = new ArrayList<GpAccountant>();
    	
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
					
					GpAccountant foundEmp = new GpAccountant();
					
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
	public GpAccountant findById(Integer accId) {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE WHERE EMP_ID = ?";
		
		Object[] tabParam = {accId};
		
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	
    	GpAccountant foundAcc = null;
    	
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
					
					foundAcc = new GpAccountant();
					
					foundAcc.setId(accId);
					foundAcc.setFileNumber(fileNumber);
					foundAcc.setLastname(lastname);
					foundAcc.setFirstname(firstname);
					foundAcc.setCreationDate(creationDate);
					foundAcc.setPassword(password);
					foundAcc.setPhoneNumber(phoneNumber);
					foundAcc.setEmail(email);
					foundAcc.setLogin(login);
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
            
        }
    	
		return foundAcc;
	}

	@Override
	public String getCurrentTableName() {
		
		return GpAccountant.GP_ACCOUNTANT_TABLE_NAME;
		
	}

}
