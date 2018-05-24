package ro.db.vendor.service;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.db.vendor.domain.Departments;
import ro.db.vendor.domain.Employees;
import ro.db.vendor.domain.UserType;
import ro.db.vendor.domain.Vendors;
import ro.db.vendor.model.EmployeeModel;
import ro.db.vendor.model.EmployeeModelForAdd;
import ro.db.vendor.model.UserInfoModel;
import ro.db.vendor.model.VendorModel;
import ro.db.vendor.repository.EmployeesRepository;
import ro.db.vendor.repository.RolesRepository;
import ro.db.vendor.repository.VendorRepository;

@Service
@Transactional(readOnly = true)
@Log4j
public class EmployeesServiceImpl implements EmployeesService {

  private final EmployeesRepository employeesRepository;
  private final VendorRepository vendorRepository;
  private final VendorService vendorService;
  private final RolesRepository rolesRepository;

  @Inject
  public EmployeesServiceImpl(EmployeesRepository employeesRepository,
      VendorRepository vendorRepository, VendorService vendorService,
      RolesRepository rolesRepository) {
    this.employeesRepository = employeesRepository;
    this.vendorRepository = vendorRepository;
    this.vendorService = vendorService;
    this.rolesRepository = rolesRepository;
  }

  public List<EmployeeModel> convertEmployeeListToEmployeeModelList(List<Employees> employeesList) {
    List<EmployeeModel> employeeModelList = new ArrayList<>();
    for (Employees employees : employeesList) {
      EmployeeModel employeeModel = new EmployeeModel();
      employeeModel.setEmployeeName(employees.getEmpName());
      employeeModel.setEmployeeId(employees.getIdEmp());
      employeeModelList.add(employeeModel);
    }
    return employeeModelList;
  }

  @Override
  public Employees logIn(String email, String password) {
    final Employees currentEmployee = employeesRepository.findByEmail(email).orElse(null);
    final BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
    try {
      if (pwEncoder.matches(password, currentEmployee.getPassword())) {
        return currentEmployee;
      }
    } catch (RuntimeException e) {
      log.error("Wrong password!", e);
    }
    return null;
  }

  @Override
  public UserInfoModel toUserInfo(Employees employee) {
    UserInfoModel employeeInfo = new UserInfoModel();
    int admin = UserType.ADMIN.getIdRole();
    int isEmployee = UserType.EMPLOYEE.getIdRole();
    if (employee.getRolesByIdRole().getIdRole() != admin) {
      employeeInfo.setDepName(employee.getDepartmentsByIdDepartment().getDepName());
      employeeInfo.setMng(employee.isMng());
      if (employee.getRolesByIdRole().getIdRole() == isEmployee) {
        employeeInfo.setIdMng(employee.getEmployeesByIdMng().getIdEmp());
      }
    }
    employeeInfo.setEmail(employee.getEmail());
    employeeInfo.setEmpName(employee.getEmpName());
    employeeInfo.setIdEmp(employee.getIdEmp());
    employeeInfo.setPhone(employee.getPhone());
    employeeInfo.setIdRole(employee.getRolesByIdRole().getIdRole());
    return employeeInfo;
  }

  @Override
  public List<VendorModel> findVendorsByIdEmployee(int id) {
    List<Vendors> vendorsList = vendorRepository.findVendorsByIdEmployee(id);
    return vendorService.convertVendorListToVendorModelList(vendorsList);
  }

  @Override
  public Employees save(EmployeeModelForAdd employeeModel) {
    Employees manager = employeesRepository.findManager(employeeModel.getId_mng());
    Departments department = new Departments();
    department.setIdDepartament(employeeModel.getId_departament());
    Employees employee = new Employees();
    employee.setIdEmp(employeesRepository.getMaxOfIdEmployee() + 1);
    employee.setEmpName(employeeModel.getName());
    employee.setEmail(employeeModel.getEmail());
    employee.setPhone(employeeModel.getPhone());
    employee.setMng(employeeModel.isIs_mng());
    employee.setPassword(employeeModel.getPassword());
    employee.setEmployeesByIdMng(manager);
    employee.setDepartmentsByIdDepartment(department);
    int employeeType = UserType.EMPLOYEE.getIdRole();
    int managerType = UserType.MANAGER.getIdRole();
    if (employee.isMng()) {
      employee.setRolesByIdRole(rolesRepository.findRoleById(managerType));
    } else {
      employee.setRolesByIdRole(rolesRepository.findRoleById(employeeType));
    }
    return this.employeesRepository.save(employee);
  }

  @Override
  public List<Employees> findAllManagers() {
    List<Employees> managersList = employeesRepository.findAllManagers();
    return managersList;
  }

  @Override
  public List<EmployeeModel> findAllEmployees() {
    List<Employees> employeesList = employeesRepository.findAll();
    for (Employees employee : employeesList) {
      if (employee.getRolesByIdRole().getIdRole() == 1) {
        employeesList.remove(employee);
        break;
      }
    }
    return convertEmployeeListToEmployeeModelList(employeesList);
  }

}
