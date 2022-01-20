package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpProject;

public interface IGpProjectDAO {
	public GpProject create(GpProject prj);
	public void update(GpProject prj);
	public List<GpProject> findAll();
	public void deleteById(Integer prjId);
	public GpProject findById(Integer prjId);
	public boolean ifPrjExistByProjetCode(String projectCode);
}
