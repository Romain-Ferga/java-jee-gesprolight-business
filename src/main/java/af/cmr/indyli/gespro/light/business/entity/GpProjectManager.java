package af.cmr.indyli.gespro.light.business.entity;

import java.util.List;

public class GpProjectManager extends GpEmployee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<GpProject> gpProjects;

	public GpProjectManager() {
		super();
	}


	// TODO 6: A réaliser au niveau des services ? Mais pourquoi ne pas le stocker en base ?
	public List<GpProject> getGpProjects() {
		return gpProjects;
	}

	public void setGpProjects(List<GpProject> gpProjects) {
		this.gpProjects = gpProjects;
	}

}