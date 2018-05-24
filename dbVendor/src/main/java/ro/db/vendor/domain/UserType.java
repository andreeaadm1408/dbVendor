package ro.db.vendor.domain;

public enum UserType {
  ADMIN (1),
  MANAGER (2),
  EMPLOYEE (3);

  private final int idRole;
  UserType(int idRole){
    this.idRole = idRole;
  }
  public int getIdRole() {
    return idRole;
  }
}
