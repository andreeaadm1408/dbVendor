package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.db.vendor.domain.VendorSkills;

public interface VendorSkillRepository extends JpaRepository<VendorSkills, Integer> {

}
