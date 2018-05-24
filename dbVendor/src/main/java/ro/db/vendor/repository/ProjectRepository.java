package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.db.vendor.domain.Projects;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Integer> {

}
