package ro.db.vendor.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.db.vendor.domain.Departments;
import ro.db.vendor.repository.DepartmentsRepository;

@Service
@Transactional(readOnly = true)
public class DepartmentsServiceImpl implements DepartmentsService {

  private final DepartmentsRepository departmentsRepository;

  @Inject
  public DepartmentsServiceImpl(DepartmentsRepository departmentsRepository) {
    this.departmentsRepository = departmentsRepository;
  }

  @Override
  public List<Departments> findAll() {
    List<Departments> departmentsList = departmentsRepository.findAll();
    return departmentsList;
  }
}
