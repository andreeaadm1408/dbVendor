package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.db.vendor.domain.Overtime;

public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {

}
