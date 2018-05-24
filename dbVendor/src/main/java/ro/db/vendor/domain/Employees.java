package ro.db.vendor.domain;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employees {

  private int idEmp;
  private String empName;
  private String email;
  private String phone;
  private boolean isMng;
  private String password;
  private Employees employeesByIdMng;
  private Departments departmentsByIdDepartment;
  private Roles rolesByIdRole;

  @Id
  @Column(name = "id_emp", nullable = false)
  public int getIdEmp() {
    return idEmp;
  }

  public void setIdEmp(int idEmp) {
    this.idEmp = idEmp;
  }

  @Basic
  @Column(name = "emp_name", nullable = false, length = -1)
  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  @Basic
  @Column(name = "email", nullable = false, length = -1)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "phone", nullable = true, length = -1)
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Basic
  @Column(name = "is_mng", nullable = false)
  public boolean isMng() {
    return isMng;
  }

  public void setMng(boolean mng) {
    isMng = mng;
  }

  @Basic
  @Column(name = "password", nullable = false, length = -1)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employees employees = (Employees) o;
    return idEmp == employees.idEmp &&
        isMng == employees.isMng &&
        Objects.equals(empName, employees.empName) &&
        Objects.equals(email, employees.email) &&
        Objects.equals(phone, employees.phone) &&
        Objects.equals(password, employees.password);
  }

  @Override
  public int hashCode() {

    return Objects.hash(idEmp, empName, email, phone, isMng, password);
  }

  @ManyToOne
  @JoinColumn(name = "id_mng", referencedColumnName = "id_emp")
  public Employees getEmployeesByIdMng() {
    return employeesByIdMng;
  }

  public void setEmployeesByIdMng(Employees employeesByIdMng) {
    this.employeesByIdMng = employeesByIdMng;
  }

  @ManyToOne
  @JoinColumn(name = "id_department", referencedColumnName = "id_departament")
  public Departments getDepartmentsByIdDepartment() {
    return departmentsByIdDepartment;
  }

  public void setDepartmentsByIdDepartment(Departments departmentsByIdDepartment) {
    this.departmentsByIdDepartment = departmentsByIdDepartment;
  }

  @ManyToOne
  @JoinColumn(name = "id_role", referencedColumnName = "id_role")
  public Roles getRolesByIdRole() {
    return rolesByIdRole;
  }

  public void setRolesByIdRole(Roles rolesByIdRole) {
    this.rolesByIdRole = rolesByIdRole;
  }
}
