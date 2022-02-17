package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;

public class GpPhaseServiceImpl implements IGpPhaseService {

	private IGpPhaseDAO phsDAO = new GpPhaseDAOImpl();

	@Override
	public GpPhase create(GpPhase phs) throws GesproBusinessException {

		if (this.phsDAO.ifPhsExistByPhaseCode(phs.getPhaseCode()))
			throw new GesproBusinessException(
					String.format("Une Phase existe deja avec le code[%s]", phs.getPhaseCode()));

		if (duringMoreThanSixMonth(phs) && phs.getAmount() > 150000)

			throw new GesproBusinessException(
					"Le montant de la phase ne peut pas exceder 150 000 euros si cette dernière à été crée il y a plus de six mois..");

		if (phs.getStartDate().compareTo(phs.getGpProject().getStartDate()) <= 0)

			throw new GesproBusinessException("La phase ne peut pas commencer avant son projet associé..");

		return this.phsDAO.create(phs);

	}

	public boolean duringMoreThanSixMonth(GpPhase phs) {

		return TimeUnit.DAYS.convert(Math.abs(phs.getStartDate().getTime() - phs.getEndDate().getTime()),
				TimeUnit.MILLISECONDS) > 182.5;
	}

	@Override
	public void update(GpPhase phs) throws GesproBusinessException {

		this.phsDAO.update(phs);

	}

	@Override
	public List<GpPhase> findAll() {

		return this.phsDAO.findAll();

	}

	@Override
	public void deleteById(Integer phsId) {

		this.phsDAO.deleteById(phsId);

	}

	@Override
	public GpPhase findById(Integer phsId) {

		return this.phsDAO.findById(phsId);

	}

	@Override
	public boolean ifPhsExistByPhaseCode(String phaseCode) {

		return this.phsDAO.ifPhsExistByPhaseCode(phaseCode);

	}

}
