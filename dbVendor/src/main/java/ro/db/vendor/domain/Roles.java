package ro.db.vendor.domain;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Roles {

  private int idRole;
  private String role;

  @Id
  @Column(name = "id_role", nullable = false)
  public int getIdRole() {
    return idRole;
  }

  public void setIdRole(int idRole) {
    this.idRole = idRole;
  }

  @Basic
  @Column(name = "role", nullable = false, length = -1)
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Roles roles = (Roles) o;
    return idRole == roles.idRole &&
        Objects.equals(role, roles.role);
  }

  @Override
  public int hashCode() {

    return Objects.hash(idRole, role);
  }
}
