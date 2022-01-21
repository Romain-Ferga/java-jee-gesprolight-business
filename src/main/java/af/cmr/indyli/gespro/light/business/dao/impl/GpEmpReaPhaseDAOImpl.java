package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmpReaPhaseDAO;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;

public class GpEmpReaPhaseDAOImpl implements IGpEmpReaPhaseDAO {
	
	private GpEntityManager entityManager = new GpEntityManager();

	@Override
	public GpEmpReaPhase create(GpEmpReaPhase erp) {

		String REQ_SQL = "INSERT INTO GP_EMP_REA_PHASE (CREATION_DATE, PHASE_ID, EMP_ID) VALUES (?,?,?)";
		
		Object[] tabParam = {erp.getCreationDate(), erp.getGpPhase().getId(), erp.getGpEmployee().getId()};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		
		return erp;
		
	}

	@Override
	public void update(GpEmpReaPhase erp) {

		String REQ_SQL = "UPDATE FROM GP_EMP_REA_PHASE SET CREATION_DATE=?, PHASE_ID=?, EMP_ID=? WHERE ASSO_REA_ID=?";
		
		Object[] tabParam = {erp.getCreationDate(), erp.getGpPhase().getId(), erp.getGpEmployee().getId(), erp.getId()};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);	
		
	}

	@Override
	public List<GpEmpReaPhase> findAll() {

		String REQ_SQL = "SELECT * FROM GP_EMP_REA_PHASE";
		
		ResultSet resultat = entityManager.exec(REQ_SQL);
		
		List<GpEmpReaPhase> erpList = new ArrayList<GpEmpReaPhase>();
		
		if(resultat != null) {
			
			try {
				
				while(resultat.next()) {
					
					Integer erpId = resultat.getInt("ASSO_REA_ID");
					Date erpCreationDate = resultat.getDate("CREATION_DATE");
					Integer erpPhaseId = resultat.getInt("PHASE_ID");
					Integer erpEmpId = resultat.getInt("EMP_ID");
					
					GpEmpReaPhase foundErp= new GpEmpReaPhase();
					GpPhaseDAOImpl phase = new GpPhaseDAOImpl();
					GpEmployeeDAOImpl emp = new GpEmployeeDAOImpl();
					
					foundErp.setId(erpId);
					foundErp.setCreationDate(erpCreationDate);
					foundErp.setGpPhase(phase.findById(erpPhaseId));
					foundErp.setGpEmployee(emp.findById(erpEmpId));
					
					erpList.add(foundErp);
					
				}
				
				resultat.close();
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return erpList;

	}

	@Override
	public void deleteById(Integer erpId) {

		String REQ_SQL = "DELETE FROM GP_EMP_REA_PHASE WHERE ASSO_REA_ID = ?";
		
		Object[] tabParam = {erpId};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		
	}

	@Override
	public GpEmpReaPhase findById(Integer erpId) {
		
		String REQ_SQL = "SELECT * FROM GP_EMP_REA_PHASE WHERE ASSO_REA_ID = ?";
		
		Object[] tabParam = {erpId};
		
		ResultSet resultat = entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		
		GpEmpReaPhase foundErp = null;
		
		if(resultat != null) {
			
			try {
				
				while(resultat.next()) {
					
					Date erpCreationDate = resultat.getDate("CREATION_DATE");
					Integer erpPhaseId = resultat.getInt("PHASE_ID");
					Integer erpEmpId = resultat.getInt("EMP_ID");
					
					foundErp= new GpEmpReaPhase();
					GpPhaseDAOImpl phase = new GpPhaseDAOImpl();
					GpEmployeeDAOImpl emp = new GpEmployeeDAOImpl();
					
					foundErp.setId(erpId);
					foundErp.setCreationDate(erpCreationDate);
					foundErp.setGpPhase(phase.findById(erpPhaseId));
					foundErp.setGpEmployee(emp.findById(erpEmpId));
					
				}
				
				resultat.close();
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return foundErp;
		
	}

}
