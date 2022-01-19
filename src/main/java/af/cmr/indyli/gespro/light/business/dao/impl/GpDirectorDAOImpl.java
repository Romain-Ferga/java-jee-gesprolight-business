package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpDirectorDAO;
import af.cmr.indyli.gespro.light.business.entity.GpDirector;

public class GpDirectorDAOImpl extends GpAbstractEmployeeDAOImpl<GpDirector> implements IGpDirectorDAO {

	@Override
	public GpDirector create(GpDirector dir) {
		
		try {
			
			//On demarre une transaction
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			
			// On enregistre dans la table employé
			dir = super.create(dir);
			
			// On enregistre l'id dans la table comptable
			String REQ_SQL = "INSERT INTO GP_DIRECTOR (EMP_ID) VALUES (?)";
			
	    	Object[] tabParam = {dir.getId()};
	    	
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
    	
    	return dir;
    	
	}

	@Override
	public void update(GpDirector dir) {
		
		//super.update(acc);
    	
	}

	@Override
	public List<GpDirector> findAll() {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE AS EMP, GP_DIRECTOR AS DIR WHERE EMP.EMP_ID = DIR.EMP_ID";
		
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	
    	List<GpDirector> dirList = new ArrayList<GpDirector>();
    	
    	if (resultat != null) {
    		
            try {
            	
				while (resultat.next()) {
					
					Integer dirId = resultat.getInt("EMP_ID");
					String fileNumber = resultat.getString("FILE_NUMBER");
					String lastname = resultat.getString("LASTNAME");
					String firstname = resultat.getString("FIRSTNAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String password = resultat.getString("PASSWORD");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String email = resultat.getString("EMAIL");
					String login = resultat.getString("LOGIN");
					
					GpDirector foundDir = new GpDirector();
					
					foundDir.setId(dirId);
					foundDir.setFileNumber(fileNumber);
					foundDir.setLastname(lastname);
					foundDir.setFirstname(firstname);
					foundDir.setCreationDate(creationDate);
					foundDir.setPassword(password);
					foundDir.setPhoneNumber(phoneNumber);
					foundDir.setEmail(email);
					foundDir.setLogin(login);
					
					dirList.add(foundDir);
					
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
            
        }
    	
		return dirList;
		
	}

	@Override
	public GpDirector findById(Integer dirId) {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE WHERE EMP_ID = ?";
		
		Object[] tabParam = {dirId};
		
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	
    	GpDirector foundDir = null;
    	
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
					
					foundDir = new GpDirector();
					
					foundDir.setId(dirId);
					foundDir.setFileNumber(fileNumber);
					foundDir.setLastname(lastname);
					foundDir.setFirstname(firstname);
					foundDir.setCreationDate(creationDate);
					foundDir.setPassword(password);
					foundDir.setPhoneNumber(phoneNumber);
					foundDir.setEmail(email);
					foundDir.setLogin(login);
					
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
            
        }
    	
		return foundDir;
	}

	@Override
	public String getCurrentTableName() {
		return GpDirector.GP_DIRECTOR_TABLE_NAME;
	}

}
