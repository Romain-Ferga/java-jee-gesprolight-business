package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;

public class GpAddressDAOImpl extends GpAbstractAddressDAOImpl<GpAddress> implements IGpAddressDAO {

	@Override
	public GpAddress create(GpAddress addr) {
	    	String REQ_SQL = "INSERT INTO GP_ADDRESS (STREET_NUMBER,STREET_LABEL,ZIP_CODE,COUNTRY,IS_MAIN,ORG_ID,EMP_ID) VALUES (?,?,?,?,?,?,?)";
	    	Object[] tabParam = {addr.getStreetNumber(), addr.getStreetLabel(), addr.getZipCode(), addr.getCountry(), addr.getIsMain(), addr.getGpOrganization(), addr.getGpEmployee()};
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
		return addr;
	}

	@Override
	public void update(GpAddress addr) {
		String REQ_SQL = "UPDATE FROM GP_ADDRESS SET STREET_NUMBER=? , STREET_LABEL=? , ZIP_CODE=? ,COUNTRY = ? ,IS_MAIN=? ,ORG_ID=? ,EMP_ID=?     WHERE ADDRESS_ID = ?";
    	Object[] tabParam = {addr.getStreetNumber(), addr.getStreetLabel(), addr.getZipCode(), addr.getCountry(), addr.getIsMain(), addr.getGpOrganization(), addr.getGpEmployee()};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpAddress> findAll() {
		String REQ_SQL = "SELECT * FROM GP_ADDRESS";
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
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
					// GpOrganizationDAOImpl org = new GpOrganizationDAOImpl();
					//foundAddr.setGpOrganization(org.find(orgId));
					
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

	@Override
	public GpAddress findById(Integer addrId) {
		
		String REQ_SQL = "SELECT * FROM GP_ADDRESS where ADDRESS_ID = ?";
		Object[] tabParam = {addrId};
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
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
					// GpOrganizationDAOImpl org = new GpOrganizationDAOImpl();
					//foundAddr.setGpOrganization(org.find(orgId));
					
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
