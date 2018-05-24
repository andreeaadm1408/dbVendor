package ro.db.vendor.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ro.db.vendor.domain.VendorDaysOff;
import ro.db.vendor.domain.Vendors;
import ro.db.vendor.repository.VendorDaysOffRepository;
import ro.db.vendor.repository.VendorRepository;

@Service
public class VendorDaysOffServiceImpl implements VendorDaysOffService{

  private VendorDaysOffRepository vendorDaysOffRepository;
  private VendorRepository vendorRepository;

  public VendorDaysOffServiceImpl(
      VendorDaysOffRepository vendorDaysOffRepository,
      VendorRepository vendorRepository) {
    this.vendorDaysOffRepository = vendorDaysOffRepository;
    this.vendorRepository = vendorRepository;
  }

  @Override
  public VendorDaysOff updateDays(VendorDaysOff vendorDaysOff) {
    return vendorDaysOffRepository.save(vendorDaysOff);
  }

  @Override
  public VendorDaysOff findByVendorId(Vendors vendor) {
    return vendorDaysOffRepository.findByVendorsByIdVendor(vendor);
  }

  @Override
  public int vendorDaysOffRemaining(int vendorId) {
    Optional<Vendors> vendor = vendorRepository.findById(vendorId);
    VendorDaysOff vendorDaysOff = vendorDaysOffRepository.findByVendorsByIdVendor(vendor.get());
    return vendorDaysOff.getCurentDaysOff();
  }


}
