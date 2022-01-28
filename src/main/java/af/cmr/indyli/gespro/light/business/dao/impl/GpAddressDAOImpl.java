package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;

public class GpAddressDAOImpl implements IGpAddressDAO {

	private GpEntityManager entityManager = new GpEntityManager();

	public GpAddress create(GpAddress addr) {
		
		try {
			
			// On démarre une transaction
			entityManager.getDbConnect().setAutoCommit(false);
			
			// On insert l'adresse
	    	String REQ_SQL = "INSERT INTO GP_ADDRESS ( STREET_NUMBER , STREET_LABEL ,ZIP_CODE , COUNTRY ,IS_MAIN ,ORG_ID ,EMP_ID ) VALUES (?,?,?,?,?,?,?)";
	    	
	    	Object[] tabParam = {addr.getStreetNumber(), addr.getStreetLabel(),addr.getZipCode(), addr.getCountry(), addr.getIsMain(), addr.getGpOrganization().getId(), addr.getGpEmployee().getId()};
	    	
	    	entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	    	
	    	// On récupère le nouvel id
	    	Integer addrId = entityManager.findIdByAnyColumn("GP_ADDRESS", "STREET_LABEL", addr.getStreetLabel(), "ADDRESS_ID"); //TODO : chercher avec getmaxid
	    	
	    	// On l'attribut à l'objet
	    	addr.setId(addrId);
	    	
			/*
			 * String REQ_SQL_MAX_ID = "SELECT MAX(ADDRESS_ID) AS MAX_ID FROM GP_ADDRESS";
			 * 
			 * ResultSet resultat = entityManager.exec(REQ_SQL_MAX_ID);
			 * 
			 * if (resultat!=null) {
			 * 
			 * while(resultat.next()) {
			 * 
			 * addr.setId(resultat.getInt("MAX_ID"));
			 * 
			 * }
			 * 
			 * }
			 */
	    	
	    	entityManager.getDbConnect().setAutoCommit(true);
	    	
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return addr;

	}

	public void update(GpAddress addr) {
		String REQ_SQL = "UPDATE FROM GP_ADDRESS SET STREET_NUMBER=? , STREET_LABEL=? , ZIP_CODE=? ,COUNTRY = ? ,IS_MAIN=? ,ORG_ID=? ,EMP_ID=?     WHERE ADDRESS_ID = ?";
    	Object[] tabParam = {addr.getStreetNumber(), addr.getStreetLabel(), addr.getZipCode(), addr.getCountry(), addr.getIsMain(), addr.getGpOrganization().getId(), addr.getGpEmployee().getId(), addr.getId()};
    	entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	public List<GpAddress> findAll() {
		String REQ_SQL = "SELECT * FROM GP_ADDRESS";
    	ResultSet resultat = entityManager.exec(REQ_SQL);
    	List<GpAddress> addrList = new ArrayList<GpAddress>();
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					
					Integer addrId = resultat.getInt("ADDRESS_ID");
					Integer streetNumber = resultat.getInt("STREET_NUMBER");
					String streetLabel = resultat.getString("STREET_LABEL");
					Integer zipCode = resultat.getInt("ZIP_CODE");
					String country = resultat.getString("COUNTRY");
					Byte isMain = resultat.getByte("IS_MAIN");
					Integer orgId = resultat.getInt("ORG_ID");
					Integer empId = resultat.getInt("EMP_ID");
					
					GpAddress foundAddr = new GpAddress();
					
					foundAddr.setId(addrId);
					foundAddr.setStreetNumber(streetNumber);
					foundAddr.setStreetLabel(streetLabel);
					foundAddr.setZipCode(zipCode);
					foundAddr.setCountry(country);
					foundAddr.setIsMain(isMain);
					
					// find Organization by orgId
					GpOrganizationDAOImpl org = new GpOrganizationDAOImpl();
					foundAddr.setGpOrganization(org.findById(orgId));
					
					// find Employee by empId
					GpEmployeeDAOImpl emp = new GpEmployeeDAOImpl();
					foundAddr.setGpEmployee(emp.findById(empId));
					
					addrList.add(foundAddr);
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
            
        }
    	
		return addrList;
		
	}

	public void deleteById(Integer addrId) {
		
		String REQ_SQL = "DELETE FROM GP_ADDRESS WHERE ADDRESS_ID = ?";
    	Object[] tabParam = {addrId};
    	entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
    	
    	//On supprime ensuite dans la table mere
    	String REQ_SQL_ADDR = "DELETE FROM GP_ADDRESS WHERE ADDRESS_ID = ?";
    	
    	entityManager.updateAvecParamGenerique(REQ_SQL_ADDR, tabParam);
    	
	}

	public GpAddress findById(Integer addrId) {
		
		String REQ_SQL = "SELECT * FROM GP_ADDRESS WHERE ADDRESS_ID = ?";
		Object[] tabParam = {addrId};
    	ResultSet resultat = entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
    	GpAddress foundAddr = null;
    	
    	if (resultat != null) {
    		
            try {
            	
				while (resultat.next()) {
					
					Integer streetNumber = resultat.getInt("STREET_NUMBER");
					String streetLabel = resultat.getString("STREET_LABEL");
					Integer zipCode = resultat.getInt("ZIP_CODE");
					String country = resultat.getString("COUNTRY");
					Byte isMain = resultat.getByte("IS_MAIN");
					Integer orgId = resultat.getInt("ORG_ID");
					Integer empId = resultat.getInt("EMP_ID");
					
					foundAddr = new GpAddress();
					
					foundAddr.setStreetNumber(streetNumber);
					foundAddr.setStreetLabel(streetLabel);
					foundAddr.setZipCode(zipCode);
					foundAddr.setCountry(country);
					foundAddr.setIsMain(isMain);
					
					// find Organization by orgId
					GpOrganizationDAOImpl org = new GpOrganizationDAOImpl();
					foundAddr.setGpOrganization(org.findById(orgId));
					
					// find Employee by empId
					GpEmployeeDAOImpl emp = new GpEmployeeDAOImpl();
					foundAddr.setGpEmployee(emp.findById(empId));
					
				}
				
				resultat.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
            
        }
    	
		return foundAddr;
		
	}
	
}
