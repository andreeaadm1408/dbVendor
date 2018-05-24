package ro.db.vendor.controller;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.db.vendor.domain.Employees;
import ro.db.vendor.model.EmployeeModel;
import ro.db.vendor.model.EmployeeModelForAdd;
import ro.db.vendor.model.LoginModel;
import ro.db.vendor.model.UserInfoModel;
import ro.db.vendor.model.VendorModel;
import ro.db.vendor.security.Password;
import ro.db.vendor.service.AuthService;
import ro.db.vendor.service.EmployeesService;

@RestController
@RequestMapping("/employee")
@CrossOrigin
@Log4j
public class EmployeesController {

  @Autowired
  private EmployeesService employeesService;
  @Autowired
  private AuthService authService;

  @Inject
  public EmployeesController(EmployeesService employeesService) {
    this.employeesService = employeesService;
  }

  @PostMapping("/login")
  public UserInfoModel findById(@RequestBody LoginModel loginModel,
      HttpServletResponse response) {

    try {
      Employees employee = employeesService
          .logIn(loginModel.getEmail(), loginModel.getPassword());
      response.addHeader("db-vendor-token", authService.createJWT(employee));
      response.addHeader("Access-Control-Expose-Headers", "db-vendor-token");
      response.addHeader("Access-Control-Allowed-Headers", "db-vendor-token");
      UserInfoModel userInfoModel = employeesService.toUserInfo(employee);
      return userInfoModel;
    } catch (RuntimeException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      log.info("User not found or wrong password!");
      return null;
    }
  }

  @GetMapping
  public List<VendorModel> findVendorsByIdEmployee(@RequestParam("id") int id) {
    return employeesService.findVendorsByIdEmployee(id);
  }

  @GetMapping("/showAllEmployees")
  public List<EmployeeModel> findAllEmployees() {
    return employeesService.findAllEmployees();
  }

  @GetMapping("/totalNumberOfEmployees")
  public int returnTotalNumberOfEmployees() {
    return employeesService.findAllEmployees().size();
  }

  @PostMapping("/addEmployee")
  public Employees create(@RequestBody EmployeeModelForAdd employeeModel) {
    String oldPassword = employeeModel.getPassword();
    employeeModel.setPassword(Password.hashPassword(oldPassword));
    return employeesService.save(employeeModel);
  }

  @GetMapping("/allMng")
  public List<Employees> findAllManagers() {
    return employeesService.findAllManagers();
  }
}
