package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;

public class GpOrganizationDAOImpl implements IGpOrganizationDAO {
	
	private GpEntityManager entityManager = new GpEntityManager();
	
	public GpOrganization create(GpOrganization org) {
		
		try {
			
			// On démarre une transaction
			entityManager.getDbConnect().setAutoCommit(false);
	
			String REQ_SQL = "INSERT INTO GP_ORGANIZATION (ORG_CODE, NAME, PHONE_NUMBER, CONTACT_NAME, CONTACT_EMAIL, ADR_WEB) VALUES (?,?,?,?,?,?)";
			
			Object[] tabParam = {org.getOrgCode(), org.getName(), org.getPhoneNumber(), org.getContactName(), org.getContactEmail(), org.getAdrWeb()};
			
			entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
			
			// On récupère le nouvel id
			Integer orgId = entityManager.findIdByAnyColumn("GP_ORGANIZATION", "ORG_CODE", org.getOrgCode(), "ORG_ID");
			
			org.setId(orgId);
			
			entityManager.getDbConnect().setAutoCommit(true);
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
			return org;
		
	}

	public void update(GpOrganization org) {

		String REQ_SQL = "UPDATE FROM GP_ORGANIZATION SET ORG_CODE=?, NAME=?, PHONE_NUMBER=?, CONTACT_NAME=?, CONTACT_EMAIL=?, ADR_WEB=? WHERE ORG_ID=?";
		
		Object[] tabParam = {org.getOrgCode(), org.getName(), org.getPhoneNumber(), org.getContactName(), org.getContactName(), org.getAdrWeb(), org.getId()};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);	
		
	}

	public List<GpOrganization> findAll() {

		String REQ_SQL = "SELECT * FROM GP_ORGANIZATION";
		
		ResultSet resultat = entityManager.exec(REQ_SQL);
		
		List<GpOrganization> orgList = new ArrayList<GpOrganization>();
		
		if(resultat != null) {
			
			try {
				
				while(resultat.next()) {
					
					Integer orgId = resultat.getInt("ORG_ID");
					String orgCode = resultat.getString("ORG_CODE");
					String orgName = resultat.getString("NAME");
					Integer orgPhoneNumber = resultat.getInt("PHONE_NUMBER");
					String orgContactName = resultat.getString("CONTACT_NAME");
					String orgContactEmail = resultat.getString("CONTACT_EMAIL");
					String orgAdrWeb = resultat.getString("ADR_WEB");
					
					GpOrganization foundOrg= new GpOrganization();
					
					foundOrg.setId(orgId);
					foundOrg.setOrgCode(orgCode);
					foundOrg.setName(orgName);
					foundOrg.setPhoneNumber(orgPhoneNumber);
					foundOrg.setContactName(orgContactName);
					foundOrg.setContactEmail(orgContactEmail);
					foundOrg.setAdrWeb(orgAdrWeb);
					
					orgList.add(foundOrg);
					
				}
				
				resultat.close();
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return orgList;
		
	}

	public void deleteById(Integer orgId) {

		String REQ_SQL = "DELETE FROM GP_ORGANIZATION WHERE ORG_ID = ?";
		
		Object[] tabParam = {orgId};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		
	}

	public GpOrganization findById(Integer orgId) {

		String REQ_SQL = "SELECT * FROM GP_ORGANIZATION WHERE ORG_ID = ?";
		
		Object[] tabParam = {orgId};
		
		ResultSet resultat = entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		
		GpOrganization foundOrg = null;
		
		if(resultat != null) {
			
			try {
				
				while(resultat.next()) {
					
					String orgCode = resultat.getString("ORG_CODE");
					String orgName = resultat.getString("NAME");
					Integer orgPhoneNumber = resultat.getInt("PHONE_NUMBER");
					String orgContactName = resultat.getString("CONTACT_NAME");
					String orgContactEmail = resultat.getString("CONTACT_EMAIL");
					String orgAdrWeb = resultat.getString("ADR_WEB");
					
					foundOrg= new GpOrganization();
					
					foundOrg.setId(orgId);
					foundOrg.setOrgCode(orgCode);
					foundOrg.setName(orgName);
					foundOrg.setPhoneNumber(orgPhoneNumber);
					foundOrg.setContactName(orgContactName);
					foundOrg.setContactEmail(orgContactEmail);
					foundOrg.setAdrWeb(orgAdrWeb);
					
				}
				
				resultat.close();
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return foundOrg;
		
	}

	public boolean ifOrgExistByOrgCode(String orgCode) {

		Integer orgIdByOrgCode = entityManager.findIdByAnyColumn("GP_ORGANIZATION", "ORG_CODE", orgCode, "ORG_ID");
		
		return orgIdByOrgCode != null;
		
	}

}
