package ro.db.vendor.model;

public class VendorModelForAdd {

  private String vendorName;
  private String email;
  private String phone;
  private Double dailyRate;
  private int id_mng;
  private int id_orgMng;

  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Double getDailyRate() {
    return dailyRate;
  }

  public void setDailyRate(Double dailyRate) {
    this.dailyRate = dailyRate;
  }

  public int getId_mng() {
    return id_mng;
  }

  public void setId_mng(int id_mng) {
    this.id_mng = id_mng;
  }

  public int getId_orgMng() {
    return id_orgMng;
  }

  public void setId_orgMng(int id_orgMng) {
    this.id_orgMng = id_orgMng;
  }

  @Override
  public String toString() {
    return "VendorModelForAdd{" +
        "name='" + vendorName + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", dailyRate=" + dailyRate +
        ", id_mng=" + id_mng +
        ", id_orgMng=" + id_orgMng +
        '}';
  }
}
