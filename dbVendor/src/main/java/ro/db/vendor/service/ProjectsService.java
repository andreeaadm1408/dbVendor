package ro.db.vendor.service;

import ro.db.vendor.domain.Projects;
import ro.db.vendor.model.ProjectModelForInsertNewProject;

public interface ProjectsService {

  Projects save(ProjectModelForInsertNewProject projects);

}
