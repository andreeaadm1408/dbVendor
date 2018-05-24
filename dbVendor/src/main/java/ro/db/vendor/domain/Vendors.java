package ro.db.vendor.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vendors {

  private int idVendor;
  private String vendorName;
  private String email;
  private String phone;
  private Double dailyRate;
  private VendorManagers vendorManagersByIdOriginManager;
  private Employees employeesByIdDbManager;

  @Id
  @Column(name = "id_vendor", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getIdVendor() {
    return idVendor;
  }

  public void setIdVendor(int idVendor) {
    this.idVendor = idVendor;
  }

  @Basic
  @Column(name = "vendor_name", nullable = false, length = -1)
  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
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
  @Column(name = "daily_rate", nullable = true, precision = 0)
  public Double getDailyRate() {
    return dailyRate;
  }

  public void setDailyRate(Double dailyRate) {
    this.dailyRate = dailyRate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vendors vendors = (Vendors) o;
    return idVendor == vendors.idVendor &&
        Objects.equals(vendorName, vendors.vendorName) &&
        Objects.equals(email, vendors.email) &&
        Objects.equals(phone, vendors.phone) &&
        Objects.equals(dailyRate, vendors.dailyRate);
  }

  @Override
  public int hashCode() {

    return Objects.hash(idVendor, vendorName, email, phone, dailyRate);
  }

  @ManyToOne
  @JoinColumn(name = "id_origin_manager", referencedColumnName = "id_vendor_mng")
  public VendorManagers getVendorManagersByIdOriginManager() {
    return vendorManagersByIdOriginManager;
  }

  public void setVendorManagersByIdOriginManager(VendorManagers vendorManagersByIdOriginManager) {
    this.vendorManagersByIdOriginManager = vendorManagersByIdOriginManager;
  }

  @ManyToOne
  @JoinColumn(name = "id_db_manager", referencedColumnName = "id_emp")
  public Employees getEmployeesByIdDbManager() {
    return employeesByIdDbManager;
  }

  public void setEmployeesByIdDbManager(Employees employeesByIdDbManager) {
    this.employeesByIdDbManager = employeesByIdDbManager;
  }
}
