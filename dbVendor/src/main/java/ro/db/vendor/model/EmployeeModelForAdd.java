package ro.db.vendor.model;

public class EmployeeModelForAdd {

  private String name;
  private String email;
  private String password;
  private String phone;
  private boolean is_mng;
  private int id_mng;
  private int id_departament;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public boolean isIs_mng() {
    return is_mng;
  }

  public void setIs_mng(boolean is_mng) {
    this.is_mng = is_mng;
  }

  public int getId_mng() {
    return id_mng;
  }

  public void setId_mng(int id_mng) {
    this.id_mng = id_mng;
  }

  public int getId_departament() {
    return id_departament;
  }

  public void setId_departament(int id_departament) {
    this.id_departament = id_departament;
  }
}
