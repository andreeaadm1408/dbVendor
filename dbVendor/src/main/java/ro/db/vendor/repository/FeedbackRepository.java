package ro.db.vendor.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.db.vendor.domain.Employees;
import ro.db.vendor.domain.Feedback;
import ro.db.vendor.domain.Vendors;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

  @Query(value = "select f from Employees e, Feedback f, Vendors v where f.vendorsByIdVendor=v.idVendor and f.employeesByIdEmp=e.idEmp and v.idVendor=:vendorId")
  List<Feedback> findByVendorId(@Param("vendorId") Integer vendorId, Pageable pageable);

  @Query("SELECT F.employeesByIdEmp FROM Feedback F WHERE F.employeesByIdEmp=:employee")
  Employees findEmployeesFromFeedbackTable(@Param("employee") Employees employee);

  @Query("SELECT F.employeesByIdEmp FROM Feedback F WHERE F.vendorsByIdVendor=:vendor")
  List<Employees> findEmployeesByVendorFromFeedback(@Param("vendor") Vendors vendor);
}
