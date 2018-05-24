package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.db.vendor.domain.Departments;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {

}
