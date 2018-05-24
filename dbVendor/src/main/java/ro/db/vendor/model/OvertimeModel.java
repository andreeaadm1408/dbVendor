package ro.db.vendor.model;

import java.sql.Timestamp;

public class OvertimeModel {

  private Timestamp date;
  private int nrOfHours;
  private String type;
  private int vendorId;

  public Timestamp getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }

  public int getNrOfHours() {
    return nrOfHours;
  }

  public void setNrOfHours(int nrOfHours) {
    this.nrOfHours = nrOfHours;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getVendorId() {
    return vendorId;
  }

  public void setVendorId(int vendorId) {
    this.vendorId = vendorId;
  }
}
