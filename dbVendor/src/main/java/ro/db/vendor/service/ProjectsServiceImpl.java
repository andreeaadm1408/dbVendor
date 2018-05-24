package ro.db.vendor.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.db.vendor.domain.Employees;
import ro.db.vendor.domain.Projects;
import ro.db.vendor.model.ProjectModelForInsertNewProject;
import ro.db.vendor.repository.EmployeesRepository;
import ro.db.vendor.repository.ProjectRepository;

@Service
@Transactional
public class ProjectsServiceImpl implements ProjectsService {

  private final EmployeesRepository employeesRepository;
  private final ProjectRepository projectRepository;

  @Inject
  public ProjectsServiceImpl(EmployeesRepository employeesRepository,
      ProjectRepository projectRepository) {
    this.employeesRepository = employeesRepository;
    this.projectRepository = projectRepository;
  }

  @Override
  public Projects save(ProjectModelForInsertNewProject projectModel) {
    Projects project = new Projects();
    Employees manager = employeesRepository.getManagerByEmail(projectModel.getEmailManager());
    project.setEmployeesByIdMng(manager);
    project.setProjectName(projectModel.getNameProject());
    return this.projectRepository.save(project);
  }
}
