package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpAdminDAO<Entity extends IEntity> {
	public Entity create(Entity adm);
	public void update(Entity adm);
	public List<Entity> findAll();
	public void deleteById(Integer admId);
	public Entity findById(Integer admId);
	public boolean ifAdmExistByFileNumberOrEmail(String fileNumber,String email,String login);
}
