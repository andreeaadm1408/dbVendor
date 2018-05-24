package ro.db.vendor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
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
public class Holidays {

  private Timestamp startDate;
  private Timestamp endDate;
  private HolidayType holidayType;
  private int idHoliday;
  private Vendors vendorsByIdVendor;

  @Basic
  @Column(name = "start_date", nullable = false)
  @JsonFormat(pattern = "dd.MM.yyyy", timezone = "EET")
  public Timestamp getStartDate() {
    return startDate;
  }

  public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
  }

  @Basic
  @Column(name = "end_date", nullable = false)
  @JsonFormat(pattern = "dd.MM.yyyy", timezone = "EET")
  public Timestamp getEndDate() {
    return endDate;
  }

  public void setEndDate(Timestamp endDate) {
    this.endDate = endDate;
  }

  @Basic
  @Column(name = "holiday_type", nullable = true, length = -1)
  public HolidayType getHolidayType() {
    return holidayType;
  }

  public void setHolidayType(HolidayType holidayType) {
    this.holidayType = holidayType;
  }

  @Id
  @Column(name = "id_holiday", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getIdHoliday() {
    return idHoliday;
  }

  public void setIdHoliday(int idHoliday) {
    this.idHoliday = idHoliday;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Holidays holidays = (Holidays) o;
    return idHoliday == holidays.idHoliday &&
        Objects.equals(startDate, holidays.startDate) &&
        Objects.equals(endDate, holidays.endDate) &&
        Objects.equals(holidayType, holidays.holidayType);
  }

  @Override
  public int hashCode() {

    return Objects.hash(startDate, endDate, holidayType, idHoliday);
  }

  @ManyToOne
  @JoinColumn(name = "id_vendor", referencedColumnName = "id_vendor", nullable = false)
  public Vendors getVendorsByIdVendor() {
    return vendorsByIdVendor;
  }

  public void setVendorsByIdVendor(Vendors vendorsByIdVendor) {
    this.vendorsByIdVendor = vendorsByIdVendor;
  }
}
