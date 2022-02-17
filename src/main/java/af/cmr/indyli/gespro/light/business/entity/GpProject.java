package af.cmr.indyli.gespro.light.business.entity;

import java.util.Date;
import java.util.List;

public class GpProject implements IEntity {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private double amount;

	private Date creationDate;

	private String description;

	private Date endDate;

	private String name;

	private String projectCode;

	private Date startDate;

	private Date updateDate;

	private List<GpPhase> gpPhases;

	private GpProjectManager gpChefProjet;

	private GpOrganization gpOrganization;

	public GpProject() {

		gpChefProjet = new GpProjectManager();

		gpOrganization = new GpOrganization();

	}

	public GpPhase addGpPhas(GpPhase gpPhas) {
		getGpPhases().add(gpPhas);
		gpPhas.setGpProject(this);

		return gpPhas;
	}

	public double getAmount() {
		return this.amount;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public GpProjectManager getGpChefProjet() {
		return this.gpChefProjet;
	}

	public GpOrganization getGpOrganization() {
		return this.gpOrganization;
	}

	public List<GpPhase> getGpPhases() {
		return this.gpPhases;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public String getProjectCode() {
		return this.projectCode;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public GpPhase removeGpPhas(GpPhase gpPhas) {
		getGpPhases().remove(gpPhas);
		gpPhas.setGpProject(null);

		return gpPhas;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setGpChefProjet(GpProjectManager gpChefProjet) {
		this.gpChefProjet = gpChefProjet;
	}

	public void setGpOrganization(GpOrganization gpOrganization) {
		this.gpOrganization = gpOrganization;
	}

	public void setGpPhases(List<GpPhase> gpPhases) {
		this.gpPhases = gpPhases;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}