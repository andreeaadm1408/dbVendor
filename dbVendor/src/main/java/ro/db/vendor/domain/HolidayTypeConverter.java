package ro.db.vendor.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class HolidayTypeConverter implements AttributeConverter<HolidayType, String> {

  @Override
  public String convertToDatabaseColumn(HolidayType holidayType) {
    return holidayType.getType();
  }

  @Override
  public HolidayType convertToEntityAttribute(String s) {
    return HolidayType.fromDBName(s);
  }
}
