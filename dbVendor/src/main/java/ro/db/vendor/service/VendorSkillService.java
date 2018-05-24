package ro.db.vendor.service;

import java.util.List;
import ro.db.vendor.domain.VendorSkills;

public interface VendorSkillService {
  List<VendorSkills> findAll();
}
