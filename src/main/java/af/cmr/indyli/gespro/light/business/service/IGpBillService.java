package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpBillService<DTO extends IEntity> {
	public DTO create(DTO bill) throws GesproBusinessException;
	public void update(DTO bill) throws GesproBusinessException;
	public List<DTO> findAll();
	public void deleteById(Integer billId);
	public DTO findById(Integer billId);
}
