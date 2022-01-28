package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpPhaseService {
	public GpPhase create(GpPhase phs) throws GesproBusinessException;
	public void update(GpPhase phs) throws GesproBusinessException;
	public List<GpPhase> findAll();
	public void deleteById(Integer phsId);
	public GpPhase findById(Integer phsId);
	public boolean ifPhsExistByPhaseCode(String phaseCode);
}
