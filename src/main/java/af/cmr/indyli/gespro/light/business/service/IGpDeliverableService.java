package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpDeliverableService {
	public GpDeliverable create(GpDeliverable dlvb) throws GesproBusinessException;
	public void update(GpDeliverable dlvb) throws GesproBusinessException;
	public List<GpDeliverable> findAll();
	public void deleteById(Integer dlvbId);
	public GpDeliverable findById(Integer dlvbId);
}
