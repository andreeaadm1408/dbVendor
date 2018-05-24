package ro.db.vendor.controller;

import javax.inject.Inject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.db.vendor.domain.Projects;
import ro.db.vendor.model.ProjectModelForInsertNewProject;
import ro.db.vendor.service.ProjectsService;

@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectsController {

  private ProjectsService projectsService;

  @Inject
  public ProjectsController(ProjectsService projectsService) {
    this.projectsService = projectsService;
  }

  @PostMapping("/addProject")
  public Projects insertNewProject(@RequestBody ProjectModelForInsertNewProject projectModel) {
    return projectsService.save(projectModel);
  }
}
