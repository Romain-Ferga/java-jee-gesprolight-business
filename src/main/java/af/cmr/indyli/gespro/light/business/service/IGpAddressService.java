package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpAddressService {
	public GpAddress create(GpAddress addr) throws GesproBusinessException;
	public void update(GpAddress addr) throws GesproBusinessException;
	public List<GpAddress> findAll();
	public void deleteById(Integer addrId);
	public GpAddress findById(Integer addrId);
}
