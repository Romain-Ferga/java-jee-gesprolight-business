package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpBillDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpBillDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpBillService;

public class GpBillServiceImpl implements IGpBillService {
	
	private IGpBillDAO billDAO = new GpBillDAOImpl();

	@Override
	public GpBill create(GpBill bill) throws GesproBusinessException {

		if(this.billDAO.ifBillExistBillCode(bill.getBillCode())) {
			
			throw new GesproBusinessException(String.format("Une Facture existe deja avec ce code[%s]", bill.getBillCode()));
			
		}
		
		return this.billDAO.create(bill);
		
	}

	@Override
	public void update(GpBill bill) throws GesproBusinessException {

		this.billDAO.update(bill);
		
	}

	@Override
	public List<GpBill> findAll() {

		return this.billDAO.findAll();
		
	}

	@Override
	public void deleteById(Integer billId) {

		this.billDAO.deleteById(billId);
		
	}

	@Override
	public GpBill findById(Integer billId) {

		return this.billDAO.findById(billId);
		
	}

	@Override
	public boolean ifBillExistBillCode(String billCode) {

		return this.billDAO.ifBillExistBillCode(billCode);
		
	}

}
