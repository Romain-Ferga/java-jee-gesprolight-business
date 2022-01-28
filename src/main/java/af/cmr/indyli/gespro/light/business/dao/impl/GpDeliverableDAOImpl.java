package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpDeliverableDAO;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;

public class GpDeliverableDAOImpl implements IGpDeliverableDAO {
	
	private GpEntityManager entityManager = new GpEntityManager();

	public GpDeliverable create(GpDeliverable dlvb) {

		String REQ_SQL = "INSERT INTO GP_DELIVERABLE (DEL_CODE, LABEL, DESCRIPTION, DEL_PATH, CREATION_DATE, PHASE_ID) VALUES (?,?,?,?,?,?)";
		
		Object[] tabParam = {dlvb.getDelCode(), dlvb.getLabel(), dlvb.getDescription(), dlvb.getDelPath(), dlvb.getCreationDate(), dlvb.getGpPhase().getId()};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		
		return dlvb;
		
	}

	public void update(GpDeliverable dlvb) {

		String REQ_SQL = "UPDATE FROM GP_DELIVERABLE SET DEL_CODE=?, LABEL=?, DESCRIPTION=?, DEL_PATH=?, CREATION_DATE=?, PHASE_ID=? WHERE DEL_ID=?";
		
		Object[] tabParam = {dlvb.getDelCode(), dlvb.getLabel(), dlvb.getDescription(), dlvb.getDelPath(), dlvb.getCreationDate(), dlvb.getGpPhase().getId()};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);	
		
	}

	public List<GpDeliverable> findAll() {

		String REQ_SQL = "SELECT * FROM GP_DELIVERABLE";
		
		ResultSet resultat = entityManager.exec(REQ_SQL);
		
		List<GpDeliverable> dlvbList = new ArrayList<GpDeliverable>();
		
		if(resultat != null) {
			
			try {
				
				while(resultat.next()) {
					
					Integer dlvbId = resultat.getInt("DEL_ID");
					String dlvbCode = resultat.getString("DEL_CODE");
					String dlvbLabel = resultat.getString("LABEL");
					String dlvbDescription = resultat.getString("DESCRIPTION");
					String dlvbPath = resultat.getString("DEL_PATH");
					Date dlvbCreationDate = resultat.getDate("CREATION_DATE");
					Integer dlvbPhaseId = resultat.getInt("PHASE_ID");
					
					GpDeliverable foundDlvb= new GpDeliverable();
					GpPhaseDAOImpl phase = new GpPhaseDAOImpl();
					
					foundDlvb.setId(dlvbId);
					foundDlvb.setDelCode(dlvbCode);
					foundDlvb.setLabel(dlvbLabel);
					foundDlvb.setDescription(dlvbDescription);
					foundDlvb.setDelPath(dlvbPath);
					foundDlvb.setCreationDate(dlvbCreationDate);
					foundDlvb.setGpPhase(phase.findById(dlvbPhaseId));
					
					dlvbList.add(foundDlvb);
					
				}
				
				resultat.close();
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return dlvbList;
		
	}

	public void deleteById(Integer dlvbId) {

		String REQ_SQL = "DELETE FROM GP_DELIVERABLE WHERE DEL_ID = ?";
		
		Object[] tabParam = {dlvbId};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		
	}

	public GpDeliverable findById(Integer dlvbId) {

		String REQ_SQL = "SELECT * FROM GP_DELIVERABLE WHERE DEL_ID = ?";
		
		Object[] tabParam = {dlvbId};
		
		ResultSet resultat = entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		
		GpDeliverable foundDlvb = null;
		
		if(resultat != null) {
			
			try {
				
				while(resultat.next()) {
					
					String dlvbCode = resultat.getString("DEL_CODE");
					String dlvbLabel = resultat.getString("LABEL");
					String dlvbDescription = resultat.getString("DESCRIPTION");
					String dlvbPath = resultat.getString("DEL_PATH");
					Date dlvbCreationDate = resultat.getDate("CREATION_DATE");
					Integer dlvbPhaseId = resultat.getInt("PHASE_ID");
					
					foundDlvb= new GpDeliverable();
					GpPhaseDAOImpl phase = new GpPhaseDAOImpl();
					
					foundDlvb.setId(dlvbId);
					foundDlvb.setDelCode(dlvbCode);
					foundDlvb.setLabel(dlvbLabel);
					foundDlvb.setDescription(dlvbDescription);
					foundDlvb.setDelPath(dlvbPath);
					foundDlvb.setCreationDate(dlvbCreationDate);
					foundDlvb.setGpPhase(phase.findById(dlvbPhaseId));
					
				}
				
				resultat.close();
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return foundDlvb;
		
	}

	public boolean ifDlvbExistByDlvbCode(String dlvbCode) {

		Integer delIdByDlvbCode = entityManager.findIdByAnyColumn("GP_DELIVERABLE", "DEL_CODE", dlvbCode, "DEL_ID");
		
		return delIdByDlvbCode != null;
		
	}

}
