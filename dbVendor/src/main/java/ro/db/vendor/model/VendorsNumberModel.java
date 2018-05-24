package ro.db.vendor.model;

public class VendorsNumberModel {

  private String projectName;
  private long vendorsNumber;

  public VendorsNumberModel(String projectName, long vendorsNumber) {
    this.projectName = projectName;
    this.vendorsNumber = vendorsNumber;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public long getVendorsNumber() {
    return vendorsNumber;
  }

  public void setVendorsNumber(long vendorsNumber) {
    this.vendorsNumber = vendorsNumber;
  }
}
