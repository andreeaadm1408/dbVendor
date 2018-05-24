package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.db.vendor.domain.VendorManagers;

public interface VendorManagerRepository extends JpaRepository<VendorManagers, Integer> {

}

