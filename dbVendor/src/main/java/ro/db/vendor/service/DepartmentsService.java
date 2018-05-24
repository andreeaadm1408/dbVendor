package ro.db.vendor.service;

import java.util.List;
import ro.db.vendor.domain.Departments;

public interface DepartmentsService {

  List<Departments> findAll();
}
