package ro.db.vendor.service;

import java.util.List;
import ro.db.vendor.domain.VendorManagers;

public interface VendorManagerService {

  List<VendorManagers> findAll();
}
