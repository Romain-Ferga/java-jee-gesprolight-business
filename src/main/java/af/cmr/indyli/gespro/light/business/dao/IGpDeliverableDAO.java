package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;

public interface IGpDeliverableDAO {
	public GpDeliverable create(GpDeliverable dlvb);
	public void update(GpDeliverable dlvb);
	public List<GpDeliverable> findAll();
	public void deleteById(Integer dlvbId);
	public GpDeliverable findById(Integer dlvbId);
	public boolean ifDlvbExistByDlvbCode(String dlvbCode);
}
