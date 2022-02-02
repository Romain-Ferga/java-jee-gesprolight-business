package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public class GpPhaseDAOImpl implements IGpPhaseDAO {

	private GpEntityManager entityManager = new GpEntityManager();

	public GpPhase create(GpPhase phs) {
		
		try {
			
			// On d�marre une transaction
			entityManager.getDbConnect().setAutoCommit(false);	
				
			String REQ_SQL = "INSERT INTO GP_PHASE (PHASE_CODE, DESCRIPTION, START_DATE, END_DATE, AMOUNT, STATUS, IS_ENDED, CREATION_DATE, UPDATE_DATE, PROJECT_ID) VALUES (?,?,?,?,?,?,?,?,?,?)";
			
			Object[] tabParam = {phs.getPhaseCode(), phs.getDescription(), phs.getStartDate(), phs.getEndDate(), phs.getAmount(), phs.getStatus(), phs.getIsEnded(), phs.getCreationDate(), phs.getUpdateDate(), phs.getGpProject().getId()};
			
			entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
			
			// On r�cup�re le nouvel id
			Integer phsId = entityManager.findIdByAnyColumn("GP_PHASE", "PHASE_CODE", phs.getPhaseCode(), "PHASE_ID");
			
			phs.setId(phsId);
			
			entityManager.getDbConnect().setAutoCommit(true);
			
			}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return phs;
	}

	public void update(GpPhase phs) {

		String REQ_SQL = "UPDATE FROM GP_PHASE SET PHASE_CODE=?, DESCRIPTION=?, START_DATE=?, END_DATE=?, AMOUNT=?, STATUS=?, IS_ENDED=?, CREATION_DATE=?, UPDATE_DATE=?, PROJECT_ID=? WHERE PHASE_ID=?";
		
		Object[] tabParam = {phs.getPhaseCode(), phs.getDescription(), phs.getStartDate(), phs.getEndDate(), phs.getAmount(), phs.getStatus(), phs.getIsEnded(), phs.getCreationDate(), phs.getUpdateDate(), phs.getGpProject().getId(), phs.getId()};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);	

	}

	public List<GpPhase> findAll() {

		String REQ_SQL = "SELECT * FROM GP_PHASE";

		ResultSet resultat = entityManager.exec(REQ_SQL);

		List<GpPhase> phsList = new ArrayList<GpPhase>();

		if(resultat != null) {

			try {

				while(resultat.next()) {

					Integer phsId = resultat.getInt("PHASE_ID");
					String phsCode = resultat.getString("PHASE_CODE");
					String phsDescription = resultat.getString("DESCRIPTION");
					Date phsStartDate = resultat.getDate("START_DATE");
					Date phsEndDate = resultat.getDate("END_DATE");
					Integer phsAmount = resultat.getInt("AMOUNT");
					Byte phsStatus = resultat.getByte("STATUS");
					Byte phsIsEnded = resultat.getByte("IS_ENDED");
					Date phsCreationDate = resultat.getDate("CREATION_DATE");
					Date phsUpdateDate = resultat.getDate("UPDATE_DATE");
					Integer phsProjectId = resultat.getInt("PROJECT_ID");

					GpPhase foundPhs= new GpPhase();
					GpProjectDAOImpl project = new GpProjectDAOImpl();

					foundPhs.setId(phsId);
					foundPhs.setPhaseCode(phsCode);
					foundPhs.setDescription(phsDescription);
					foundPhs.setStartDate(phsStartDate);
					foundPhs.setEndDate(phsEndDate);
					foundPhs.setAmount(phsAmount);
					foundPhs.setStatus(phsStatus);
					foundPhs.setIsEnded(phsIsEnded);
					foundPhs.setCreationDate(phsCreationDate);
					foundPhs.setUpdateDate(phsUpdateDate);
					foundPhs.setGpProject(project.findById(phsProjectId));

					phsList.add(foundPhs);

				}

				resultat.close();

			}catch(SQLException e) {

				e.printStackTrace();

			}

		}

		return phsList;

	}

	public void deleteById(Integer phsId) {

		String REQ_SQL = "DELETE FROM GP_PHASE WHERE PHASE_ID = ?";

		Object[] tabParam = {phsId};

		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);

	}

	public GpPhase findById(Integer phsId) {

		String REQ_SQL = "SELECT * FROM GP_PHASE WHERE PHASE_ID = ?";

		Object[] tabParam = {phsId};

		ResultSet resultat = entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);

		GpPhase foundPhs = null;

		if(resultat != null) {

			try {

				while(resultat.next()) {

					String phsCode = resultat.getString("PHASE_CODE");
					String phsDescription = resultat.getString("DESCRIPTION");
					Date phsStartDate = resultat.getDate("START_DATE");
					Date phsEndDate = resultat.getDate("END_DATE");
					Integer phsAmount = resultat.getInt("AMOUNT");
					Byte phsStatus = resultat.getByte("STATUS");
					Byte phsIsEnded = resultat.getByte("IS_ENDED");
					Date phsCreationDate = resultat.getDate("CREATION_DATE");
					Date phsUpdateDate = resultat.getDate("UPDATE_DATE");
					Integer phsProjectId = resultat.getInt("PROJECT_ID");

					foundPhs = new GpPhase();
					GpProjectDAOImpl project = new GpProjectDAOImpl();

					foundPhs.setId(phsId);
					foundPhs.setPhaseCode(phsCode);
					foundPhs.setDescription(phsDescription);
					foundPhs.setStartDate(phsStartDate);
					foundPhs.setEndDate(phsEndDate);
					foundPhs.setAmount(phsAmount);
					foundPhs.setStatus(phsStatus);
					foundPhs.setIsEnded(phsIsEnded);
					foundPhs.setCreationDate(phsCreationDate);
					foundPhs.setUpdateDate(phsUpdateDate);
					foundPhs.setGpProject(project.findById(phsProjectId));

				}

				resultat.close();

			}catch(SQLException e) {

				e.printStackTrace();

			}

		}

		return foundPhs;

	}

	public boolean ifPhsExistByPhaseCode(String phaseCode) {

		Integer phsIdByPhsCode = entityManager.findIdByAnyColumn("GP_PHASE", "PHASE_CODE", phaseCode, "PHASE_ID");

		return phsIdByPhsCode != null;

	}

}
