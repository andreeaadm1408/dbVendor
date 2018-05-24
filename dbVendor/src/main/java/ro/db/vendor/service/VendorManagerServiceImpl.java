package ro.db.vendor.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.db.vendor.domain.VendorManagers;
import ro.db.vendor.repository.VendorManagerRepository;

@Service
@Transactional(readOnly = true)
public class VendorManagerServiceImpl implements VendorManagerService {

  private final VendorManagerRepository vmRepo;

  @Inject
  public VendorManagerServiceImpl(VendorManagerRepository vmRepo) {
    this.vmRepo = vmRepo;
  }

  @Override
  public List<VendorManagers> findAll() {
    Stream<VendorManagers> stream = vmRepo.findAll().stream();
    return stream.collect(Collectors.toList());
  }
}
