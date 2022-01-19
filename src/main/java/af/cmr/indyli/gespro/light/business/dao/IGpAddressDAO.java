package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpAddress;

public interface IGpAddressDAO {
	public GpAddress create(GpAddress addr);
	public void update(GpAddress addr);
	public List<GpAddress> findAll();
	public void deleteById(Integer addrId);
	public GpAddress findById(Integer addrId);
}
