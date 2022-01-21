package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpTechnicianDAO;
import af.cmr.indyli.gespro.light.business.entity.GpTechnician;

public class GpTechnicianDAOImpl extends GpAbstractEmployeeDAOImpl<GpTechnician> implements IGpTechnicianDAO {
	
	@Override
	public GpTechnician create(GpTechnician tech) {
		
		try {
			
			//On demarre une transaction
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			
			// On enregistre dans la table employé
			tech = super.create(tech);
			
			// On enregistre l'id dans la table comptable
			String REQ_SQL = "INSERT INTO GP_TECHNICIAN (EMP_ID, LAST_DIPLOMA, GRADUATION_YEAR) VALUES (?,?,?)";
			
	    	Object[] tabParam = {tech.getId(), tech.getLastDiploma(), tech.getGraduationYear()};
	    	
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
    	
    	return tech;
    	
	}

	@Override
	public void update(GpTechnician tech) {
		
		super.update(tech);
    	
	}

	@Override
	public List<GpTechnician> findAll() {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE AS EMP, GP_TECHNICIAN AS TECH WHERE EMP.EMP_ID = TECH.EMP_ID";
		
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	
    	List<GpTechnician> techList = new ArrayList<GpTechnician>();
    	
    	if (resultat != null) {
    		
            try {
            	
				while (resultat.next()) {
					
					Integer techId = resultat.getInt("EMP_ID");
					String fileNumber = resultat.getString("FILE_NUMBER");
					String lastname = resultat.getString("LASTNAME");
					String firstname = resultat.getString("FIRSTNAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String password = resultat.getString("PASSWORD");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String email = resultat.getString("EMAIL");
					String login = resultat.getString("LOGIN");
					
					GpTechnician foundTech = new GpTechnician();
					
					foundTech.setId(techId);
					foundTech.setFileNumber(fileNumber);
					foundTech.setLastname(lastname);
					foundTech.setFirstname(firstname);
					foundTech.setCreationDate(creationDate);
					foundTech.setPassword(password);
					foundTech.setPhoneNumber(phoneNumber);
					foundTech.setEmail(email);
					foundTech.setLogin(login);
					
					techList.add(foundTech);
					
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
        }
    	
		return techList;
		
	}

	@Override
	public GpTechnician findById(Integer techId) {
		
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE WHERE EMP_ID = ?";
		
		Object[] tabParam = {techId};
		
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	
    	GpTechnician foundTech = null;
    	
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
					
					foundTech = new GpTechnician();
					
					foundTech.setId(techId);
					foundTech.setFileNumber(fileNumber);
					foundTech.setLastname(lastname);
					foundTech.setFirstname(firstname);
					foundTech.setCreationDate(creationDate);
					foundTech.setPassword(password);
					foundTech.setPhoneNumber(phoneNumber);
					foundTech.setEmail(email);
					foundTech.setLogin(login);
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
            
        }
    	
		return foundTech;
	}

	@Override
	public String getCurrentTableName() {
		
		return GpTechnician.GP_TECHNICIAN_TABLE_NAME;
		
	}

}
