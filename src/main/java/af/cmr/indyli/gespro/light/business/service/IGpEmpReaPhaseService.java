package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpEmpReaPhaseService {
	public GpEmpReaPhase create(GpEmpReaPhase erp) throws GesproBusinessException;
	public void update(GpEmpReaPhase erp) throws GesproBusinessException;
	public List<GpEmpReaPhase> findAll();
	public void deleteById(Integer erpId);
	public GpEmpReaPhase findById(Integer erpId);
}
