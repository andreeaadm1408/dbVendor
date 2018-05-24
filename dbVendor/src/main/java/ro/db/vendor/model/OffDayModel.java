package ro.db.vendor.model;

import java.sql.Timestamp;

public class OffDayModel {

  private Timestamp startDate;
  private Timestamp endDate;
  private String holidayType;
  private int idHoliday;
  private int vendorId;

  public Timestamp getStartDate() {
    return startDate;
  }

  public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
  }

  public Timestamp getEndDate() {
    return endDate;
  }

  public void setEndDate(Timestamp endDate) {
    this.endDate = endDate;
  }

  public String getHolidayType() {
    return holidayType;
  }

  public void setHolidayType(String holidayType) {
    this.holidayType = holidayType;
  }

  public int getIdHoliday() {
    return idHoliday;
  }

  public void setIdHoliday(int idHoliday) {
    this.idHoliday = idHoliday;
  }

  public int getVendorId() {
    return vendorId;
  }

  public void setVendorId(int vendorId) {
    this.vendorId = vendorId;
  }
}
