package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpProjectService<DTO extends IEntity> {
	public DTO create(DTO prj) throws GesproBusinessException;
	public void update(DTO prj) throws GesproBusinessException;
	public List<DTO> findAll();
	public void deleteById(Integer prjId);
	public DTO findById(Integer prjId);
}
