package ro.db.vendor.model;

public class VendorModelForEditing {

  private int idVendor;
  private String name;
  private String email;
  private String phone;
  private Double dailyRate;
  private int idDBManager;
  private String dbManagerName;
  private int idExternalManager;

  public int getIdVendor() {
    return idVendor;
  }

  public void setIdVendor(int idVendor) {
    this.idVendor = idVendor;
  }

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

  public int getIdDBManager() {
    return idDBManager;
  }

  public void setIdDBManager(int idDBManager) {
    this.idDBManager = idDBManager;
  }

  public String getDbManagerName() {
    return dbManagerName;
  }

  public void setDbManagerName(String dbManagerName) {
    this.dbManagerName = dbManagerName;
  }

  public int getIdExternalManager() {
    return idExternalManager;
  }

  public void setIdExternalManager(int idExternalManager) {
    this.idExternalManager = idExternalManager;
  }
}
