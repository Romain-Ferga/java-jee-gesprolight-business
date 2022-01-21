package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpBillDAO;
import af.cmr.indyli.gespro.light.business.entity.GpBill;

public class GpBillDAOImpl implements IGpBillDAO {
	
	private GpEntityManager entityManager = new GpEntityManager();

	@Override
	public GpBill create(GpBill bill) {
		
		try {
		
			// On démarre une transaction
			entityManager.getDbConnect().setAutoCommit(false);
	
			String REQ_SQL = "INSERT INTO GP_BILL (AMOUNT, BILL_CODE, BILL_STATUS, PHASE_ID) VALUES (?,?,?,?)";
			
			Object[] tabParam = {bill.getAmount(), bill.getBillCode(), bill.getBillStatus(), bill.getGpPhase().getId()};
			
			entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
			
			// On récupère le nouvel id
			Integer billId = entityManager.findIdByAnyColumn("GP_BILL", "BILL_CODE", bill.getBillCode(), "BILL_ID");
			
			bill.setId(billId);
			
			entityManager.getDbConnect().setAutoCommit(true);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return bill;
		
	}

	@Override
	public void update(GpBill bill) {

		String REQ_SQL = "UPDATE FROM GP_BILL SET AMOUNT=?, BILL_CODE=?, BILL_STATUS=?, PHASE_ID=? WHERE BILL_ID=?";
		
		Object[] tabParam = {bill.getAmount(), bill.getBillCode(), bill.getBillStatus(), bill.getGpPhase().getId(), bill.getId()};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);		
		
	}

	@Override
	public List<GpBill> findAll() {

		String REQ_SQL = "SELECT * FROM GP_BILL";
		
		ResultSet resultat = entityManager.exec(REQ_SQL);
		
		List<GpBill> billList = new ArrayList<GpBill>();
		
		if(resultat != null) {
			
			try {
				
				while(resultat.next()) {
					
					Integer billId = resultat.getInt("BILL_ID");
					Double amount = resultat.getDouble("AMOUNT");
					String billCode = resultat.getString("BILL_CODE");
					String billStatus = resultat.getString("BILL_STATUS");
					Integer phsId = resultat.getInt("PHASE_ID");
					
					GpBill foundBill= new GpBill();
					GpPhaseDAOImpl phase = new GpPhaseDAOImpl();
					
					foundBill.setId(billId);
					foundBill.setAmount(amount);
					foundBill.setBillCode(billCode);
					foundBill.setBillStatus(billStatus);
					foundBill.setGpPhase(phase.findById(phsId));
					
					billList.add(foundBill);
					
				}
				
				resultat.close();
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return billList;
		
	}

	@Override
	public void deleteById(Integer billId) {

		String REQ_SQL = "DELETE FROM GP_BILL WHERE BILL_ID = ?";
		
		Object[] tabParam = {billId};
		
		entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public GpBill findById(Integer billId) {

		String REQ_SQL = "SELECT * FROM GP_BILL WHERE BILL_ID = ?";
		
		Object[] tabParam = {billId};
		
		ResultSet resultat = entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		
		GpBill foundBill = null;
		
		if(resultat != null) {
			
			try {
				
				while(resultat.next()) {
					
					Double amount = resultat.getDouble("AMOUNT");
					String billCode = resultat.getString("BILL_CODE");
					String billStatus = resultat.getString("BILL_STATUS");
					Integer phsId = resultat.getInt("PHASE_ID");
					
					foundBill= new GpBill();
					GpPhaseDAOImpl phase = new GpPhaseDAOImpl();
					
					foundBill.setId(billId);
					foundBill.setAmount(amount);
					foundBill.setBillCode(billCode);
					foundBill.setBillStatus(billStatus);
					foundBill.setGpPhase(phase.findById(phsId));
					
				}
				
				resultat.close();
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return foundBill;
		
	}

	@Override
	public boolean ifBillExistBillCode(String billCode) {

		Integer billIdByBillCode = entityManager.findIdByAnyColumn("GP_BILL", "BILL_CODE", billCode, "BILL_ID");
		
		return billIdByBillCode != null;
		
	}

}
