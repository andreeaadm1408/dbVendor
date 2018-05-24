package ro.db.vendor.service;

import ro.db.vendor.model.OffDayModel;

public interface HolidayService {

  boolean insertHoliday(OffDayModel offDayModel);
}
