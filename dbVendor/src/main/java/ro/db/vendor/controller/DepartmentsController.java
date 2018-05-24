package ro.db.vendor.controller;

import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.db.vendor.domain.Departments;
import ro.db.vendor.service.DepartmentsService;

@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentsController {

  private DepartmentsService departmentsService;

  @Inject
  public DepartmentsController(DepartmentsService departmentsService) {
    this.departmentsService = departmentsService;
  }

  @GetMapping("/allDep")
  public List<Departments> findAll() {
    return departmentsService.findAll();
  }
}
