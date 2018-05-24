package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.db.vendor.domain.Companies;

@Repository
public interface CompanyRepository extends JpaRepository<Companies, Integer> {
}
