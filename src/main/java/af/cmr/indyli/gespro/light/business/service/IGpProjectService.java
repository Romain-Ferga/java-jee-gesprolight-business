package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpProjectService {
	public GpProject create(GpProject prj) throws GesproBusinessException;
	public void update(GpProject prj) throws GesproBusinessException;
	public List<GpProject> findAll();
	public void deleteById(Integer prjId);
	public GpProject findById(Integer prjId);
	public boolean ifPrjExistByProjetCode(String projectCode);
}
