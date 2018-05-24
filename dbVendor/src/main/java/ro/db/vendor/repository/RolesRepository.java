package ro.db.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.db.vendor.domain.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

  @Query("SELECT r FROM Roles r WHERE r.idRole=:idRole")
  Roles findRoleById(@Param("idRole") int id);
}
