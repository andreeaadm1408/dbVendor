package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.db.vendor.domain.VendorAllocation;
import ro.db.vendor.domain.Vendors;

@Repository
public interface VendorAllocationRepository extends JpaRepository<VendorAllocation, Integer> {

    @Query("SELECT va from VendorAllocation va where va.vendorsByIdVendor=:vendor")
    VendorAllocation findVendorAllocationByVendor (@Param("vendor") Vendors vendor);
}
