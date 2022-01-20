package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.entity.GpProject;

public class GpProjectDAOImpl implements IGpProjectDAO {
	
	private GpEntityManager entityManager = new GpEntityManager();

	@Override
	public GpProject create(GpProject prj) {

		String REQ_SQL = "INSERT INTO GP_PROJECT (PROJECT_CODE, NAME, DESCRIPTION, START_DATE, END_DATE, AMOUNT, CREATION_DATE, UPDATE_DATE, ORG_ID, EMP_ID) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		Object[] tabParam = {prj.getProjectCode(), prj.getName(), prj.getDescription(), prj.getStartDate(), prj.getStartDate(), prj.getEndDate(), prj.getCreationDate(), prj.getUpdateDate(), prj.getGpOrganization(), prj.getGpChefProjet()};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		
		return prj;
		
	}

	@Override
	public void update(GpProject prj) {

		String REQ_SQL = "UPDATE FROM GP_PROJECT SET PROJECT_CODE=?, NAME=?, DESCRIPTION=?, START_DATE=?, END_DATE=?, AMOUNT=?, CREATION_DATE=?, UPDATE_DATE=?, ORG_ID=?, EMP_ID=? WHERE PROJECT_ID=?";
		
		Object[] tabParam = {prj.getProjectCode(), prj.getName(), prj.getDescription(), prj.getStartDate(), prj.getStartDate(), prj.getEndDate(), prj.getCreationDate(), prj.getUpdateDate(), prj.getGpOrganization(), prj.getGpChefProjet()};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);	
		
	}

	@Override
	public List<GpProject> findAll() {

		String REQ_SQL = "SELECT * FROM GP_PROJECT";
		
		ResultSet resultat = entityManager.exec(REQ_SQL);
		
		List<GpProject> prjList = new ArrayList<GpProject>();
		
		if(resultat != null) {
			
			try {
				
				while(resultat.next()) {
					
					Integer prjId = resultat.getInt("PROJECT_ID");
					String prjCode = resultat.getString("PROJECT_CODE");
					String prjName = resultat.getString("NAME");
					String prjDescription = resultat.getString("DESCRIPTION");
					Date prjStartDate = resultat.getDate("START_DATE");
					Date prjEndDate = resultat.getDate("END_DATE");
					Integer prjAmount = resultat.getInt("AMOUNT");
					Date prjCreationDate = resultat.getDate("CREATION_DATE");
					Date prjUpdateDate = resultat.getDate("UPDATE_DATE");
					Integer prjOrgId = resultat.getInt("ORG_ID");
					Integer prjPmId = resultat.getInt("EMP_ID");
					
					GpProject foundPrj= new GpProject();
					GpOrganizationDAOImpl org = new GpOrganizationDAOImpl();
					GpProjectManagerDAOImpl pm = new GpProjectManagerDAOImpl();
					
					foundPrj.setId(prjId);
					foundPrj.setProjectCode(prjCode);
					foundPrj.setName(prjName);
					foundPrj.setDescription(prjDescription);
					foundPrj.setStartDate(prjStartDate);
					foundPrj.setEndDate(prjEndDate);
					foundPrj.setAmount(prjAmount);
					foundPrj.setCreationDate(prjCreationDate);
					foundPrj.setUpdateDate(prjUpdateDate);
					foundPrj.setGpOrganization(org.findById(prjOrgId));
					foundPrj.setGpChefProjet(pm.findById(prjPmId));
					
					prjList.add(foundPrj);
					
				}
				
				resultat.close();
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return prjList;
		
	}

	@Override
	public void deleteById(Integer prjId) {

		String REQ_SQL = "DELETE FROM GP_PROJECT WHERE PROJECT_ID = ?";
		
		Object[] tabParam = {prjId};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		
	}

	@Override
	public GpProject findById(Integer prjId) {

		String REQ_SQL = "SELECT * FROM GP_PROJECT WHERE PROJECT_ID = ?";
		
		Object[] tabParam = {prjId};
		
		ResultSet resultat = entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		
		GpProject foundPrj = null;
		
		if(resultat != null) {
			
			try {
				
				while(resultat.next()) {
					
					String prjCode = resultat.getString("PROJECT_CODE");
					String prjName = resultat.getString("NAME");
					String prjDescription = resultat.getString("DESCRIPTION");
					Date prjStartDate = resultat.getDate("START_DATE");
					Date prjEndDate = resultat.getDate("END_DATE");
					Integer prjAmount = resultat.getInt("AMOUNT");
					Date prjCreationDate = resultat.getDate("CREATION_DATE");
					Date prjUpdateDate = resultat.getDate("UPDATE_DATE");
					Integer prjOrgId = resultat.getInt("ORG_ID");
					Integer prjPmId = resultat.getInt("EMP_ID");
					
					foundPrj= new GpProject();
					GpOrganizationDAOImpl org = new GpOrganizationDAOImpl();
					GpProjectManagerDAOImpl pm = new GpProjectManagerDAOImpl();
					
					foundPrj.setId(prjId);
					foundPrj.setProjectCode(prjCode);
					foundPrj.setName(prjName);
					foundPrj.setDescription(prjDescription);
					foundPrj.setStartDate(prjStartDate);
					foundPrj.setEndDate(prjEndDate);
					foundPrj.setAmount(prjAmount);
					foundPrj.setCreationDate(prjCreationDate);
					foundPrj.setUpdateDate(prjUpdateDate);
					foundPrj.setGpOrganization(org.findById(prjOrgId));
					foundPrj.setGpChefProjet(pm.findById(prjPmId));
					
				}
				
				resultat.close();
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return foundPrj;
		
	}

	@Override
	public boolean ifPrjExistByProjetCode(String projectCode) {

		Integer prjIdByProjectCode = entityManager.findIdByAnyColumn("GP_PROJECT", "PROJECT_CODE", projectCode, "PROJECT_ID");
		
		return prjIdByProjectCode != null;
		
	}

}
