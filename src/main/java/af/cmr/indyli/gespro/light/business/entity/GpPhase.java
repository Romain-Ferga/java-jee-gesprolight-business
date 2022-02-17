package af.cmr.indyli.gespro.light.business.entity;

import java.util.Date;
import java.util.List;

public class GpPhase implements IEntity {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private double amount;

	private Date creationDate;

	private String description;

	private Date endDate;

	private byte isEnded;

	private String phaseCode;

	private Date startDate;

	private byte status;

	private Date updateDate;

	private List<GpBill> gpBills;

	private List<GpDeliverable> gpDeliverables;

	private List<GpEmpReaPhase> gpEmpReaPhases;

	private GpProject gpProject;

	public GpPhase() {

		gpProject = new GpProject();

	}

	public GpBill addGpBill(GpBill gpBill) {
		getGpBills().add(gpBill);
		gpBill.setGpPhase(this);

		return gpBill;
	}

	public GpDeliverable addGpDeliverable(GpDeliverable gpDeliverable) {
		getGpDeliverables().add(gpDeliverable);
		gpDeliverable.setGpPhase(this);

		return gpDeliverable;
	}

	public GpEmpReaPhase addGpEmpReaPhas(GpEmpReaPhase gpEmpReaPhas) {
		getGpEmpReaPhases().add(gpEmpReaPhas);
		gpEmpReaPhas.setGpPhase(this);

		return gpEmpReaPhas;
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

	public List<GpBill> getGpBills() {
		return this.gpBills;
	}

	public List<GpDeliverable> getGpDeliverables() {
		return this.gpDeliverables;
	}

	public List<GpEmpReaPhase> getGpEmpReaPhases() {
		return this.gpEmpReaPhases;
	}

	public GpProject getGpProject() {
		return this.gpProject;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public byte getIsEnded() {
		return this.isEnded;
	}

	public String getPhaseCode() {
		return this.phaseCode;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public byte getStatus() {
		return this.status;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public GpBill removeGpBill(GpBill gpBill) {
		getGpBills().remove(gpBill);
		gpBill.setGpPhase(null);

		return gpBill;
	}

	public GpDeliverable removeGpDeliverable(GpDeliverable gpDeliverable) {
		getGpDeliverables().remove(gpDeliverable);
		gpDeliverable.setGpPhase(null);

		return gpDeliverable;
	}

	public GpEmpReaPhase removeGpEmpReaPhas(GpEmpReaPhase gpEmpReaPhas) {
		getGpEmpReaPhases().remove(gpEmpReaPhas);
		gpEmpReaPhas.setGpPhase(null);

		return gpEmpReaPhas;
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

	public void setGpBills(List<GpBill> gpBills) {
		this.gpBills = gpBills;
	}

	public void setGpDeliverables(List<GpDeliverable> gpDeliverables) {
		this.gpDeliverables = gpDeliverables;
	}

	public void setGpEmpReaPhases(List<GpEmpReaPhase> gpEmpReaPhases) {
		this.gpEmpReaPhases = gpEmpReaPhases;
	}

	public void setGpProject(GpProject gpProject) {
		this.gpProject = gpProject;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsEnded(byte isEnded) {
		this.isEnded = isEnded;
	}

	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}