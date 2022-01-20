package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public interface IGpPhaseDAO {
	public GpPhase create(GpPhase phs);
	public void update(GpPhase phs);
	public List<GpPhase> findAll();
	public void deleteById(Integer phsId);
	public GpPhase findById(Integer phsId);
	public boolean ifPhsExistByPhaseCode(String phaseCode);
}
