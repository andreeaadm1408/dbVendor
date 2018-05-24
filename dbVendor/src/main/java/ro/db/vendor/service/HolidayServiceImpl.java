package ro.db.vendor.service;

import org.springframework.stereotype.Service;
import ro.db.vendor.domain.HolidayType;
import ro.db.vendor.domain.Holidays;
import ro.db.vendor.domain.VendorDaysOff;
import ro.db.vendor.model.OffDayModel;
import ro.db.vendor.repository.HolidayRepository;
import ro.db.vendor.repository.VendorDaysOffRepository;
import ro.db.vendor.repository.VendorRepository;

@Service
public class HolidayServiceImpl implements HolidayService {

  private HolidayRepository holidayRepository;
  private VendorRepository vendorRepository;
  private VendorDaysOffRepository vendorDaysOffRepository;

  public HolidayServiceImpl(HolidayRepository holidayRepository, VendorRepository vendorRepository,
      VendorDaysOffRepository vendorDaysOffRepository) {
    this.holidayRepository = holidayRepository;
    this.vendorRepository = vendorRepository;
    this.vendorDaysOffRepository = vendorDaysOffRepository;
  }

  @Override
  public boolean insertHoliday(OffDayModel offDayModel) {

    Holidays holiday = new Holidays();
    holiday.setStartDate(offDayModel.getStartDate());
    holiday.setEndDate(offDayModel.getEndDate());
    holiday.setVendorsByIdVendor(vendorRepository.findById(offDayModel.getVendorId()).get());
    holiday.setHolidayType(HolidayType.fromDBName(offDayModel.getHolidayType()));
    VendorDaysOff vendorDaysOff = vendorDaysOffRepository
        .findByVendorsByIdVendor(holiday.getVendorsByIdVendor());
    int vendorDaysOffLeft = vendorDaysOff.getCurentDaysOff();

    long requestedTimeInMilliseconds =
        holiday.getEndDate().getTime() - holiday.getStartDate().getTime();
    int daysRequested = (int) (requestedTimeInMilliseconds / (60 * 60 * 24 * 1000));


    if ((vendorDaysOffLeft >= (daysRequested) && holiday.getHolidayType() == HolidayType.DAY_OFF)) {
      vendorDaysOff.setCurentDaysOff(vendorDaysOffLeft - daysRequested);
      vendorDaysOffRepository.save(vendorDaysOff);
      holidayRepository.save(holiday);
      return true;
    }
    if(holiday.getHolidayType() != HolidayType.DAY_OFF){
      holidayRepository.save(holiday);
      return true;
    }
    return false;
  }

}
