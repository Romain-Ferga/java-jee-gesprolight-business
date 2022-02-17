package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;

public class GpProjectServiceImpl implements IGpProjectService {

	private IGpProjectDAO prjDAO = new GpProjectDAOImpl();

	@Override
	public GpProject create(GpProject prj) throws GesproBusinessException {

		if (this.prjDAO.ifPrjExistByProjetCode(prj.getProjectCode()))
			throw new GesproBusinessException(
					String.format("Un Projet existe deja avec ce code[%s]", prj.getProjectCode()));

		if (prj.getGpChefProjet() == null || prjDAO.findById(prj.getGpChefProjet().getId()) == null)

			throw new GesproBusinessException("Veuillez fournir un chef de projet valide..");

		if (prj.getGpOrganization() == null || prjDAO.findById(prj.getGpOrganization().getId()) == null)

			throw new GesproBusinessException("Veuillez fournir une organisation valide..");

		if (prj.getEndDate().compareTo(prj.getStartDate()) <= 0)

			throw new GesproBusinessException("La date de fin ne peut pas être antérieure à la date de début..");

		return this.prjDAO.create(prj);

	}

	@Override
	public void update(GpProject prj) throws GesproBusinessException {

		if (this.prjDAO.ifPrjExistByProjetCode(prj.getProjectCode()))
			throw new GesproBusinessException(
					String.format("Un Projet existe deja avec ce code[%s]", prj.getProjectCode()));

		if (prj.getGpChefProjet() == null || prjDAO.findById(prj.getGpChefProjet().getId()) == null)

			throw new GesproBusinessException("Veuillez fournir un chef de projet valide..");

		if (prj.getGpOrganization() == null || prjDAO.findById(prj.getGpOrganization().getId()) == null)

			throw new GesproBusinessException("Veuillez fournir une organisation valide..");

		if (prj.getEndDate().compareTo(prj.getStartDate()) <= 0)

			throw new GesproBusinessException("La date de fin ne peut pas être antérieure à la date de début..");

		this.prjDAO.update(prj);

	}

	@Override
	public List<GpProject> findAll() {

		return this.prjDAO.findAll();

	}

	@Override
	public void deleteById(Integer prjId) {

		this.prjDAO.deleteById(prjId);

	}

	@Override
	public GpProject findById(Integer prjId) {

		return this.prjDAO.findById(prjId);

	}

	@Override
	public boolean ifPrjExistByProjetCode(String projectCode) {

		return this.prjDAO.ifPrjExistByProjetCode(projectCode);

	}

}
