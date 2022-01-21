package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpDeliverableService<DTO extends IEntity> {
	public DTO create(DTO dlvb) throws GesproBusinessException;
	public void update(DTO dlvb) throws GesproBusinessException;
	public List<DTO> findAll();
	public void deleteById(Integer dlvbId);
	public DTO findById(Integer dlvbId);
}
