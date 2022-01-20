package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;

public interface IGpEmpReaPhaseDAO {
	public GpEmpReaPhase create(GpEmpReaPhase erp);
	public void update(GpEmpReaPhase erp);
	public List<GpEmpReaPhase> findAll();
	public void deleteById(Integer erpId);
	public GpEmpReaPhase findById(Integer erpId);
}
