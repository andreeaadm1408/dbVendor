package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.db.vendor.domain.VendorDaysOff;
import ro.db.vendor.domain.Vendors;

public interface VendorDaysOffRepository extends JpaRepository<VendorDaysOff, Integer> {

  @Query("SELECT v FROM VendorDaysOff v WHERE v.vendorsByIdVendor=:vendors ")
  VendorDaysOff findByVendorsByIdVendor(@Param("vendors") Vendors vendors);

}
