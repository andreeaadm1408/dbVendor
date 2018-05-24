package ro.db.vendor.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.db.vendor.domain.VendorSkills;
import ro.db.vendor.repository.VendorSkillRepository;

@Service
@Transactional(readOnly = true)
public class VendorSkillServiceImpl implements VendorSkillService {

  private final VendorSkillRepository vendorSkillRepository;

  @Inject
  public VendorSkillServiceImpl(VendorSkillRepository vendorSkillRepository) {
    this.vendorSkillRepository = vendorSkillRepository;
  }


  @Override
  public List<VendorSkills> findAll() {
    Stream<VendorSkills> stream = vendorSkillRepository.findAll().stream();
    return stream.collect(Collectors.toList());
  }
}
