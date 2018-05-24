package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.db.vendor.domain.Skills;

public interface SkillRepository extends JpaRepository<Skills, Integer> {

}
