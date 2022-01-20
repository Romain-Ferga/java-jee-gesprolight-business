package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpBill;

public interface IGpBillDAO {
	public GpBill create(GpBill bill);
	public void update(GpBill bill);
	public List<GpBill> findAll();
	public void deleteById(Integer billId);
	public GpBill findById(Integer billId);
	public boolean ifBillExistBillCode(String billCode);
}
