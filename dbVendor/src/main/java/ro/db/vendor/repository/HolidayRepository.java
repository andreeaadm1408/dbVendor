package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.db.vendor.domain.Holidays;

public interface HolidayRepository extends JpaRepository<Holidays, Integer> {

}
