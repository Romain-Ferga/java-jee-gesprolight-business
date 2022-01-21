package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpPhaseService<DTO extends IEntity> {
	public DTO create(DTO phs) throws GesproBusinessException;
	public void update(DTO phs) throws GesproBusinessException;
	public List<DTO> findAll();
	public void deleteById(Integer phsId);
	public DTO findById(Integer phsId);
}
