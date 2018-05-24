package ro.db.vendor.service;

import ro.db.vendor.domain.VendorDaysOff;
import ro.db.vendor.domain.Vendors;

public interface VendorDaysOffService {
  VendorDaysOff updateDays(VendorDaysOff vendorDaysOff);

  VendorDaysOff findByVendorId(Vendors vendor);

  int vendorDaysOffRemaining(int vendorId);
}
