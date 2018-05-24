package ro.db.vendor.domain;

public enum HolidayType {
  DAY_OFF("day_off"),
  MEDICAL_LEAVE("medical_leave"),
  OVERTIME_COMPENSATION("overtime_compensation"),
  COMPANY_HOLIDAY("company_holiday");

  private final String type;

  HolidayType(String type) {
    this.type = type;
  }

  public static HolidayType fromDBName(String dbHolidayType) {
    switch (dbHolidayType) {
      case "day_off":
        return HolidayType.DAY_OFF;
      case "medical_leave":
        return HolidayType.MEDICAL_LEAVE;
      case "overtime_compensation":
        return HolidayType.OVERTIME_COMPENSATION;
      case "company_holiday":
        return HolidayType.COMPANY_HOLIDAY;
      default:
        throw new IllegalArgumentException("This holidayType not supported");
    }
  }

  public String getType() {
    return this.type;
  }

}
