package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpEmpReaPhaseService<DTO extends IEntity> {
	public DTO create(DTO erp) throws GesproBusinessException;
	public void update(DTO erp) throws GesproBusinessException;
	public List<DTO> findAll();
	public void deleteById(Integer erpId);
	public DTO findById(Integer erpId);
}
