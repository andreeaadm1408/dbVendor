package ro.db.vendor.service;

import org.springframework.stereotype.Service;
import ro.db.vendor.domain.Overtime;
import ro.db.vendor.model.OvertimeModel;
import ro.db.vendor.repository.OvertimeRepository;
import ro.db.vendor.repository.VendorRepository;

@Service
public class OvertimeServiceImpl implements OvertimeService{

  private OvertimeRepository overtimeRepository;
  private VendorRepository vendorRepository;

  public OvertimeServiceImpl(OvertimeRepository overtimeRepository,
      VendorRepository vendorRepository) {
    this.overtimeRepository = overtimeRepository;
    this.vendorRepository = vendorRepository;
  }

  @Override
  public Overtime newOvertime(OvertimeModel overtime) {
    Overtime overtime1 = new Overtime();
    overtime1.setType(overtime.getType());
    overtime1.setDate(overtime.getDate());
    overtime1.setNrOfHours(overtime.getNrOfHours());
    overtime1.setVendorsByIdVendor(vendorRepository.findById(overtime.getVendorId()).get());
    return overtimeRepository.save(overtime1);
  }
}
