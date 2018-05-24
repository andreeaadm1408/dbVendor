package ro.db.vendor.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.db.vendor.domain.Employees;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

  Optional<Employees> findByEmail(String email);

  @Query("SELECT E FROM Employees E WHERE E.idEmp=:id")
  Employees findManager(@Param("id") int id);

  @Query("SELECT E FROM Employees E WHERE E.employeesByIdMng=:manager")
  List<Employees> findEmployeesByIdManager(@Param("manager") Employees manager);

  @Query("SELECT E FROM Employees E WHERE E.mng=true ")
  List<Employees> findAllManagers();

  @Query("SELECT MAX(E.idEmp) FROM Employees E")
  int getMaxOfIdEmployee();

  @Query("SELECT E FROM Employees E WHERE E.email=:email")
  Employees getManagerByEmail (@Param("email") String email);
}
