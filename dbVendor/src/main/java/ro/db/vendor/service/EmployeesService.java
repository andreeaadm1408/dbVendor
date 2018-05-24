package ro.db.vendor.service;

import java.util.List;
import ro.db.vendor.domain.Employees;
import ro.db.vendor.model.EmployeeModel;
import ro.db.vendor.model.EmployeeModelForAdd;
import ro.db.vendor.model.UserInfoModel;
import ro.db.vendor.model.VendorModel;

public interface EmployeesService {

  Employees logIn(String email, String password);

  UserInfoModel toUserInfo(Employees employee);

  List<VendorModel> findVendorsByIdEmployee(int id);

  Employees save(EmployeeModelForAdd employee);

  List<Employees> findAllManagers();

  List<EmployeeModel> findAllEmployees();
}
