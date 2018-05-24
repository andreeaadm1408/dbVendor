package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.db.vendor.domain.Vendors;
import java.util.List;
import ro.db.vendor.model.VendorsNumberModel;

@Repository
public interface VendorRepository extends JpaRepository<Vendors, Integer> {

  @Query("select v from Vendors v, Employees e, VendorAllocation va where v.employeesByIdDbManager=e.employeesByIdMng and v.idVendor = va.vendorsByIdVendor and (va.endDate > current_timestamp or va.endDate IS NULL) and e.idEmp=:id")
  List<Vendors> findVendorsByIdEmployee(@Param("id") int id);

  @Query("select v from Vendors v, Employees e, VendorAllocation va where v.employeesByIdDbManager=e.idEmp and e.employeesByIdMng is null and v.idVendor = va.vendorsByIdVendor and (va.endDate > current_timestamp or va.endDate IS NULL) and e.idEmp=:id")
  List<Vendors> findVendorsByIdManager(@Param("id") int id);

  @Query("SELECT v FROM Vendors v, Employees e, VendorAllocation va WHERE v.employeesByIdDbManager=e.idEmp and e.employeesByIdMng is null and v.idVendor = va.vendorsByIdVendor and (va.endDate > current_timestamp or va.endDate IS NULL) and v.idVendor=:id")
  Vendors findDetailofVendorByIdVendorForManager(@Param("id") int id);

  @Query("select v from Vendors v where v.idVendor=:id")
  Vendors findVendorByIdVendor(@Param("id") int id);

  @Query(
      "SELECT new ro.db.vendor.model.VendorsNumberModel(P.projectName, count(V.idVendor)) FROM Vendors V, VendorAllocation VA, Projects P\n"
          + "WHERE V.idVendor = VA.vendorsByIdVendor AND VA.projectsByIdProiect = P.idProject GROUP BY P.projectName ORDER BY count(V.idVendor) DESC")
  List<VendorsNumberModel> findNumberOfVendorsPerProject();
}
